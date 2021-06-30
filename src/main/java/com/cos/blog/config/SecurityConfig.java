package com.cos.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//빈 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것
// 시큐리티가 모든 요청을 가로챈다.
@Configuration //빈등록 (IoC관리)
@EnableWebSecurity // 시큐리티 필터가 등록이 된다.  그 설정을 아래 클래스에서 하면된다.
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근하면 권한 및 인증을 미리체크하겠다는 뜻
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean // IoC가 된다.  리턴되는 새로운 객체를 스프링이 관리.
	public BCryptPasswordEncoder encodePWD() {

		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()  //csrf 토큰 비활성화 (테스트시 걸어두는 것이 좋음)
			.authorizeRequests()
				.antMatchers("/","/auth/**","/js/**","/css/**","/image/**") //auth쪽으로 들어오는건 모두 허용하겠다.
				.permitAll()
				.anyRequest() //그 외의것은
				.authenticated() //인증이필요함.
			.and()
				.formLogin()
				.loginPage("/auth/loginForm");
	}

}
