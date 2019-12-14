package com.userinfo.security;

import com.userinfo.security.jwt.JwtTokenFilterConfigurer;
import com.userinfo.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
            .antMatchers(HttpMethod.POST, "/users").permitAll()
            .antMatchers(HttpMethod.POST, "/login").permitAll()
            .anyRequest().authenticated();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
