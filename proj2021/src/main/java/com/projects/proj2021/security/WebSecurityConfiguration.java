package com.projects.proj2021.security;


import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/index.html","/css/**","/icon/**","/images/**","/js/**","/api/**","/swagger-ui","/v2/**")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/fetchByDuration").permitAll()
                .antMatchers(HttpMethod.POST).authenticated()
                .anyRequest()
                .authenticated()
                .and()
                .oauth2Login();
    }
}
