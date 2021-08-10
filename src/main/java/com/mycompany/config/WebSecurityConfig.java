package com.mycompany.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
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
        authProvider.setHideUserNotFoundExceptions(false) ;

        return authProvider;
    }
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.csrf().disable();
        http.authorizeRequests()
        	.regexMatchers("\\/tags\\?term=([^\\s]+)").permitAll()
        	.antMatchers("/js/**","/css/**").permitAll()
        	.antMatchers("/autocomplete").permitAll()
        	.antMatchers("/user/search").permitAll()
        	.antMatchers("/tag/search").permitAll()
        	.antMatchers("/post/searchresult").permitAll()
        	.antMatchers("/user/register").permitAll()
            .antMatchers("/home").permitAll()
            .antMatchers("/post/create").hasAuthority("USER")
            .antMatchers("/user/profile").permitAll()
            .antMatchers("/user/login").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            	.defaultSuccessUrl("/")
            	.loginPage("/user/login")
            	.loginProcessingUrl("/authenticateTheUser")
            	.failureHandler(new SimpleUrlAuthenticationFailureHandler() {
            		 
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                            AuthenticationException exception) throws IOException, ServletException {
                        String error = exception.getMessage();
                        String redirectURL = request.getContextPath() + "/user/login?error";
                        
                        if (error.contains("disabled"))
                        	redirectURL = request.getContextPath() + "/user/login?disabled";
                        
                        super.setDefaultFailureUrl(redirectURL);
                        super.onAuthenticationFailure(request, response, exception);
     
                    }
                })
            	.permitAll()
            .and()
            .logout()
            	.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
            	.permitAll();
    }
}
