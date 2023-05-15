package com.votingservice.voterdata.auth;

import com.votingservice.voterdata.model.UserApplication;
import com.votingservice.voterdata.repository.UserDetailsManagementRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDetailsManagementRepository udmrp;

    private static final Logger LOGGER = LogManager.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LOGGER.info("Username is " + username);
        try {
            UserApplication UserDetails = udmrp.findByUsername(username);
            LOGGER.info("Username is " + UserDetails.getUsername());
            if (UserDetails == null) {
                throw new UsernameNotFoundException(username);
            }
            List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                    .commaSeparatedStringToAuthorityList("ROLE_" + UserDetails.getRole());

            return new User(UserDetails.getUsername(), UserDetails.getPassword(), grantedAuthorities);

        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException(username);
        }

    }
}
