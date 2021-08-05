package com.mycompany.config;

import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
 
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }
     
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
     
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        	.regexMatchers("\\/tags\\?term=([^\\s]+)").permitAll()
        	.antMatchers("/js/**","/css/**").permitAll()
        	.antMatchers("/tag/search").permitAll()
        	.antMatchers("/post/searchresult").permitAll()
        	.antMatchers("/user/register").permitAll()
            .antMatchers("/home").permitAll()
            .antMatchers("/createpost").hasRole("USER")
            .antMatchers("/user/profile").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            	.defaultSuccessUrl("/")
            	.loginPage("/user/login")
            	.loginProcessingUrl("/authenticateTheUser")
            	.permitAll()
            .and()
            .logout()
            	.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
            	.logoutSuccessUrl("/")
            	.permitAll();
    }
}
