package com.dhruv.PropertySearchLogin_webapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.dhruv.PropertySearchLogin_webapp.Service.UserDetailsServiceImpl;
//import com.dhruv.PropertySearchLogin_webapp.Service.UserDetailsServiceImpl;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;
	
	@Autowired
	private JwtAuthenticationFilter authenticationJwtTokenFilter;

	@Autowired
	private UserDetailsServiceImpl userdetailsServiceimpl;
	
	
    @Override
    @Bean
	public AuthenticationManager authenticationManagerBean() throws Exception
	{
		return super.authenticationManagerBean();
	}
	
	@Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
	
	
	//@Bean
	//public  BCryptPasswordEncoder passwordEncoder() {
	//	return new BCryptPasswordEncoder();

	//}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(this.userdetailsServiceimpl).passwordEncoder(passwordEncoder());
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		http.csrf()
		     .disable()
		     .cors()
		     .disable()
		     .authorizeRequests().antMatchers("/generate-token","/user/").permitAll()
		     .antMatchers(HttpMethod.OPTIONS).permitAll()
		     .anyRequest().authenticated().and()
		     .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
		     .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		//filtering the valid token 
		http.addFilterBefore(authenticationJwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
		 
	}
}