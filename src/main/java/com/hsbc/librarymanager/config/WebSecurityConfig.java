package com.hsbc.librarymanager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private static final String[] AUTH_WHITELIST = {
      "/swagger-resources/**", "/swagger-ui.html", "/v1/**"
  };

  @Override
  @SuppressWarnings("PMD.SignatureDeclareThrowsException")
  protected void configure(HttpSecurity http) throws Exception {
    http.headers()
        .disable()
        .csrf()
        .disable()
        .authorizeRequests()
        .antMatchers(AUTH_WHITELIST)
        .permitAll()
        .and()
        .logout()
        .permitAll();
  }
}
