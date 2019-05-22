package com.sourcecode.malls.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.sourcecode.malls.properties.SuperAdminProperties;
import com.sourcecode.malls.web.security.entrypoint.AppEntryPoint;
import com.sourcecode.malls.web.security.filter.ErrorHandlerFilter;
import com.sourcecode.malls.web.security.filter.LoggingFilter;
import com.sourcecode.malls.web.security.filter.UserSessionFilter;
import com.sourcecode.malls.web.security.handler.AppAuthenticationFailureHandler;
import com.sourcecode.malls.web.security.handler.AppAuthenticationSuccessHandler;
import com.sourcecode.malls.web.security.source.metadata.AuthorityFilterSecurityMetadataSource;
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
	protected AppAuthenticationSuccessHandler successHandler;
	@Autowired
	protected AppAuthenticationFailureHandler failureHandler;
	@Autowired
	private AppEntryPoint entryPoint;

	@Value("${access.control.allow.origin}")
	private String origin;

	private CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList(origin));
		configuration.setAllowedHeaders(getAllowHeaders());
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS"));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	protected List<String> getAllowHeaders() {
		return Arrays.asList("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Content-Type");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		before(http);
		successHandler.setRedirectStrategy(loginSuccessfulStrategy);
		http.cors().configurationSource(corsConfigurationSource());
		http.csrf().disable();
		http.httpBasic().disable();
		http.exceptionHandling().authenticationEntryPoint(entryPoint);
		http.logout().addLogoutHandler((request, response, authentication) -> {
			try {
				response.getWriter().close();
			} catch (IOException e) {
			}
		});
		http.rememberMe().alwaysRemember(true).userDetailsService(getUserDetailsService());
		http.userDetailsService(getUserDetailsService());
		http.authorizeRequests().antMatchers("/actuator/health").permitAll();
		// http.authorizeRequests().antMatchers("/index.html").permitAll();
		// http.authorizeRequests().antMatchers("/css/**").permitAll();
		// http.authorizeRequests().antMatchers("/js/**").permitAll();
		// http.authorizeRequests().antMatchers("/fonts/**").permitAll();
		// http.authorizeRequests().antMatchers("/img/**").permitAll();
		// http.authorizeRequests().antMatchers("/favicon.ico").permitAll();
		http.authorizeRequests().antMatchers("/**/register/**").permitAll();
		http.authorizeRequests().antMatchers("/**/forgetPassword/**").permitAll();
		http.authorizeRequests().antMatchers("/actuator/**").hasAuthority(adminProperties.getAuthority());
		processAuthorizations(http);
		http.addFilterBefore(errorHandlerFilter, ChannelProcessingFilter.class);
		http.addFilterAfter(loggingFilter, ChannelProcessingFilter.class);
		// http.addFilterAfter(openEntityManagerInViewFilter, ErrorHandlerFilter.class);
		after(http);
	}

	protected void before(HttpSecurity http) throws Exception {

	}

	protected AuthenticationDetailsSource<HttpServletRequest, ?> getDetailsSource() {
		return new WebAuthenticationDetailsSource();
	}

	protected UserDetailsService getUserDetailsService() {
		return userService;
	}

	protected void processAuthorizations(HttpSecurity http) throws Exception {
		http.formLogin().permitAll().authenticationDetailsSource(getDetailsSource()).successHandler(successHandler).failureHandler(failureHandler);
		http.authorizeRequests().accessDecisionManager(new AffirmativeBased(Arrays.asList(authorityVoter, new WebExpressionVoter())))
				.withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {

					@Override
					public <O extends FilterSecurityInterceptor> O postProcess(O interceptor) {
						filterSecurityMetadataSource.setOriginSource(interceptor.getSecurityMetadataSource());
						interceptor.setSecurityMetadataSource(filterSecurityMetadataSource);
						return interceptor;
					}
				});
		http.authorizeRequests().antMatchers("/user/current/**").authenticated();
		http.authorizeRequests().antMatchers("/message/count").authenticated();
		http.authorizeRequests().anyRequest().hasAuthority(adminProperties.getAuthority());
	}

	protected void after(HttpSecurity http) throws Exception {
		http.addFilterBefore(userSessionFilter, FilterSecurityInterceptor.class);
	}

}
