package com.example.springsecuritypractice.config;

import java.util.List;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


	//	/**
//	 *	Filter 연습
//	 *	여기는 전부다 허용
//	 *	AnotherSecurityConfig와 반대되는 모습
//	 */
//	@Bean
//	//우선순위가 더 낮다 (AnotherSecurityConfig보다)
//	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
//		httpSecurity.authorizeRequests()
//				.anyRequest().permitAll()
//				.and().formLogin()
//				.and().httpBasic();
//
//		return (httpSecurity.build());
//	}
//

	public AccessDecisionManager accessDecisionManager() {
		RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
		roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");

		DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
		handler.setRoleHierarchy(roleHierarchy);

		WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
		webExpressionVoter.setExpressionHandler(handler);

		List<AccessDecisionVoter<?>> voters = List.of(webExpressionVoter);
		return (new AffirmativeBased(voters));
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
//		return web -> web.ignoring().mvcMatchers("무시할 path");
		return (web -> web
				.ignoring()
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations()));
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
		// chaining을 사용하지 않고 따로 명세해주어도 상관없다.
		httpSecurity.authorizeRequests()
				.mvcMatchers("/", "/info", "/account/**", "/signup").permitAll()
				.mvcMatchers("/admin").hasRole("ADMIN")
				.mvcMatchers("/user").hasRole("USER")
				.anyRequest().authenticated()
				.accessDecisionManager(accessDecisionManager());

		//logingPage
		//DefaultloginPageGenerateFilter등록이 안되고
		// logout도 없어진다..
		// 이 loginPage를 쓰는 순간 커스터마이징한것을 쓴다고 판단하기 때문
		httpSecurity.formLogin()
				.loginPage("/login")
				.permitAll();

		httpSecurity.logout()
				.logoutUrl("/logout") // 우리가 사용할 로그아웃 페이지
				.logoutSuccessUrl("/") // 로그아웃이 성공하면 redirect할 페이지
//				.addLogoutHandler() // 부가적인 작업을 위해 핸들러를 추가할 수 있다
//				.logoutSuccessHandler() // 성공했을 때 실행될 핸들러
				.invalidateHttpSession(true) // 디폴트가 true, 보통 커스터마이징 하지 않는다
//				.deleteCookies() // 쿠키기반의 로그인을 구현하고 있다 라고하면 여기에 쿠키이름을 주면 된다
				;
		httpSecurity.httpBasic();


		//스레드에서 생성하는 하위스레드에서는 공유가 되게!
		SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);

		return (httpSecurity.build());
	}




	//기존 방식
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//				.withUser("seokjin").password("{noop}123").roles("USER")
//				.and()
//				.withUser("admin").password("{noop}!@#").roles("ADMIN");
//	}

	//바뀐 방식
//	@Bean
//	public InMemoryUserDetailsManager userDetailsService() {
//		List<UserDetails> userDetailsList = new ArrayList<>();
//		userDetailsList.add(User.builder()
//				.username("seokjin")
//				.password("{noop}123")
//				.roles("USER")
//				.build());
//		userDetailsList.add(User.builder()
//				.username("admin")
//				.password("{noop}!@#")
//				.roles("ADMIN")
//				.build());
//
//		return (new InMemoryUserDetailsManager(userDetailsList));
//	}


	// 데이터베이스 사용해서 사용자 정보 등록


}
