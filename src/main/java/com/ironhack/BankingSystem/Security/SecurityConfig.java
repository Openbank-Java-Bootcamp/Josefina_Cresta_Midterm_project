package com.ironhack.BankingSystem.Security;

import com.ironhack.BankingSystem.Filter.CustomAuthenticationFilter;
import com.ironhack.BankingSystem.Filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/bank/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers("/bank/login/**").permitAll();
        http.authorizeRequests().antMatchers(POST, "/bank/accounts/**").
                hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(PATCH, "/bank/accounts/**").
                hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(GET, "/bank/accountholders/**").
                hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(GET, "/bank/accountholders/balance").
                hasAnyAuthority("ROLE_ACCOUNT_HOLDER");
        http.authorizeRequests().antMatchers(POST, "/bank/accountholders/**").
                hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(PUT, "/bank/accountholders/**").
                hasAnyAuthority("ROLE_ADMIN", "ROLE_ACCOUNT_HOLDER");
        http.authorizeRequests().antMatchers(DELETE, "/bank/accountholders/**").
                hasAnyAuthority("ROLE_ADMIN", "ROLE_ACCOUNT_HOLDER");
        http.authorizeRequests().antMatchers(PATCH, "/bank/accountholders/transactions").
                hasAnyAuthority( "ROLE_ACCOUNT_HOLDER");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}