package com.projects.proj2021.security;


import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/index.html","/css/**","/icon/**","/images/**","/js/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .oauth2Login();
    }
}
