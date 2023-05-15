package com.votingservice.voterdata.utilities;

import com.votingservice.voterdata.execption.TokenExpiredExecption;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtilities implements Serializable {

        private static final long serialVersionUID = -2550185165626007488L;
        public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
        @Value("${jwt.secret}")
        private String secret;
        private static final Logger LOGGER = LogManager.getLogger(JwtUtilities.class);

    //retrieve username from jwt token
        public String getIDFromToken(String token) {
            return getClaimFromToken(token, Claims::getSubject);
        }
        //retrieve expiration date from jwt token
        public Date getExpirationDateFromToken(String token) {
            return getClaimFromToken(token, Claims::getExpiration);
        }
        public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
            final Claims claims = getAllClaimsFromToken(token);
            return claimsResolver.apply(claims);
        }
        //for retrieveing any information from token we will need the secret key
        private Claims getAllClaimsFromToken(String token) {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        }
        //check if the token has expired
        private Boolean isTokenExpired(String token) {
            final Date expiration = getExpirationDateFromToken(token);
            return expiration.before(new Date());
        }

    public String generateTokenForVote(int student_id){
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, String.valueOf(student_id));
    }

        private String doGenerateToken(Map<String, Object> claims, String subject) {
            return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                    .signWith(SignatureAlgorithm.HS512, secret).compact();
        }
        //validate token
        public Integer validateToken(String token) throws TokenExpiredExecption {
            final String id = getIDFromToken(token);
            LOGGER.info("ID is "+ isTokenExpired(token));
            if(isTokenExpired(token)){
                throw new TokenExpiredExecption();
            }else{
                return (Integer.parseInt(id));
            }
        }


}
