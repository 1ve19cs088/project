package com.votingservice.voterdata.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import javax.servlet.http.HttpServletResponse;


@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtConfig jwtConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
                 http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/registervoter/","/nomination","/adminmanagement").permitAll()
                         .antMatchers(HttpMethod.GET, "/v2/api-docs",
                                 "/configuration/ui",
                                 "/swagger-resources/**",
                                 "/configuration/security",
                                 "/swagger-ui.html",
                                 "/webjars/**").permitAll()
                         .antMatchers(HttpMethod.GET, "/registervoter").hasRole("ADMIN")
                         .antMatchers(HttpMethod.POST ,"/election").hasRole("ADMIN")
                         .antMatchers(HttpMethod.GET, "/getelectiontoken/").hasRole("VOTER")
                        .anyRequest().authenticated()
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager(),jwtConfig()))
                        .addFilter(new JwtAuthorizationFilter(authenticationManager(),jwtConfig()))

                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        .and()
                .exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED));
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public JwtConfig jwtConfig() {
        return new JwtConfig();
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}