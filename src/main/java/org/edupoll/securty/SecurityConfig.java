package org.edupoll.securty;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		// csrf 설정
		httpSecurity.csrf(t->t.disable());
		
		// authorizeHttpRequests()
		httpSecurity.authorizeHttpRequests(t -> t.
				requestMatchers("/", "/user/**", "/moims").permitAll()
				.anyRequest().authenticated()
		);

		// httpSecurity.formLogin(Customizer.withDefaults());
		// 실제 로그인 처리 해 주는 곳
		httpSecurity.formLogin(t-> t.loginPage("/user/login")
				.usernameParameter("loginId").passwordParameter("loginPass")
			);
		
		return httpSecurity.build();
	}
}
