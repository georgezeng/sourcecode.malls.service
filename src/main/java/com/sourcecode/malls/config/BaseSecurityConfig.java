package com.sourcecode.malls.config;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.sourcecode.malls.properties.SuperAdminProperties;
import com.sourcecode.malls.web.filter.ErrorHandlerFilter;
import com.sourcecode.malls.web.filter.LoggingFilter;
import com.sourcecode.malls.web.filter.UserSessionFilter;
import com.sourcecode.malls.web.security.entrypoint.AppEntryPoint;
import com.sourcecode.malls.web.security.handler.AppAuthenticationFailureHandler;
import com.sourcecode.malls.web.security.handler.AppAuthenticationSuccessHandler;
import com.sourcecode.malls.web.security.metadatasource.AuthorityFilterSecurityMetadataSource;
import com.sourcecode.malls.web.security.strategy.LoginSuccessfulStrategy;
import com.sourcecode.malls.web.security.voter.AuthorityVoter;

public abstract class BaseSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private SuperAdminProperties adminProperties;
	@Autowired
	private UserDetailsService userService;
	@Autowired
	private UserSessionFilter userSessionFilter;
	// @Autowired
	// private OpenEntityManagerInViewFilter openEntityManagerInViewFilter;
	@Autowired
	private ErrorHandlerFilter errorHandlerFilter;
	@Autowired
	private LoggingFilter loggingFilter;
	@Autowired
	private LoginSuccessfulStrategy loginSuccessfulStrategy;
	@Autowired
	private AuthorityFilterSecurityMetadataSource filterSecurityMetadataSource;
	@Autowired
	private AuthorityVoter authorityVoter;
	@Autowired
	private AppAuthenticationSuccessHandler successHandler;
	@Autowired
	private AppAuthenticationFailureHandler failureHandler;
	@Autowired
	private AppEntryPoint entryPoint;

	@Value("${access.control.allow.origin}")
	private String origin;

	private CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList(origin));
		configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Origin", "Content-Type"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS"));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		before(http);
		successHandler.setRedirectStrategy(loginSuccessfulStrategy);
		http.cors().configurationSource(corsConfigurationSource());
		http.formLogin().permitAll().successHandler(successHandler).failureHandler(failureHandler);
		http.csrf().disable();
		http.httpBasic().disable();
		http.exceptionHandling().authenticationEntryPoint(entryPoint);
		http.logout().addLogoutHandler((request, response, authentication) -> {
			try {
				response.getWriter().close();
			} catch (IOException e) {
			}
		});
		http.rememberMe().alwaysRemember(true).userDetailsService(userService);
		http.userDetailsService(userService);
		http.authorizeRequests().accessDecisionManager(new AffirmativeBased(Arrays.asList(authorityVoter, new WebExpressionVoter())))
				.withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {

					@Override
					public <O extends FilterSecurityInterceptor> O postProcess(O interceptor) {
						filterSecurityMetadataSource.setOriginSource(interceptor.getSecurityMetadataSource());
						interceptor.setSecurityMetadataSource(filterSecurityMetadataSource);
						return interceptor;
					}
				});
		http.authorizeRequests().antMatchers("/actuator/health").permitAll();
		http.authorizeRequests().antMatchers("/index.html").permitAll();
		http.authorizeRequests().antMatchers("/css/**").permitAll();
		http.authorizeRequests().antMatchers("/js/**").permitAll();
		http.authorizeRequests().antMatchers("/fonts/**").permitAll();
		http.authorizeRequests().antMatchers("/img/**").permitAll();
		http.authorizeRequests().antMatchers("/favicon.ico").permitAll();
		http.authorizeRequests().antMatchers("/**/register/**").permitAll();
		http.authorizeRequests().antMatchers("/**/forgetPassword/**").permitAll();
		http.authorizeRequests().antMatchers("/user/current/**").authenticated();
		http.authorizeRequests().antMatchers("/message/count").authenticated();
		http.authorizeRequests().anyRequest().hasAuthority(adminProperties.getAuthority());
		http.addFilterBefore(errorHandlerFilter, ChannelProcessingFilter.class);
		http.addFilterAfter(loggingFilter, ChannelProcessingFilter.class);
		http.addFilterBefore(userSessionFilter, FilterSecurityInterceptor.class);
		// http.addFilterAfter(openEntityManagerInViewFilter, ErrorHandlerFilter.class);
		after(http);
	}
	
	protected abstract void before(HttpSecurity http) throws Exception;
	protected abstract void after(HttpSecurity http) throws Exception;

}