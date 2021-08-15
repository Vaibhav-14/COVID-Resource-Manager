package com.mycompany.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
 
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
	
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
            .antMatchers("/user/login").permitAll()
            .antMatchers("/user/profile").permitAll()
            .antMatchers("/vaccination").permitAll()
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
                        
                        if (error.contains("disabled")) {
                        	logger.error(error);
                        	redirectURL = request.getContextPath() + "/user/login?disabled";
                        }
                        else if(error.contains("Could not")) {
                        	logger.error(error);
                        	redirectURL = request.getContextPath() + "/user/login?notfound";
                        }
                        
                        super.setDefaultFailureUrl(redirectURL);
                        super.onAuthenticationFailure(request, response, exception);
     
                    }
                })
            	.permitAll()
            	.successHandler(new SavedRequestAwareAuthenticationSuccessHandler() {
            		@Override
            		public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, 
            				Authentication authentication) throws IOException, ServletException {
            			
            			logger.info("User : " + authentication.getName() + " logged in successfully.");
            			super.setDefaultTargetUrl("/");
            			super.onAuthenticationSuccess(request, response, authentication);
            		}
            	})
            .and()
            .logout()
            	.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
            	.addLogoutHandler(new LogoutHandler() {
					
					@Override
					public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
						logger.info("User : " + authentication.getName() + " logged out successfully.");
						
					}
				})
            	.permitAll()
            .and()
            .exceptionHandling().accessDeniedPage("/403");
    }
}
