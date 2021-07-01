package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.config.auth.PrincipalDetailService;

//빈 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것
// 시큐리티가 모든 요청을 가로챈다.
@Configuration //빈등록 (IoC관리)
@EnableWebSecurity // 시큐리티 필터가 등록이 된다.  그 설정을 아래 클래스에서 하면된다.
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근하면 권한 및 인증을 미리체크하겠다는 뜻
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean // IoC가 된다.  리턴되는 새로운 객체를 스프링이 관리.
	public BCryptPasswordEncoder encodePWD() {

		return new BCryptPasswordEncoder();
	}
	
	//시큐리티가 대신 로그인을 해주는데 password를 가로채는데,
	// 해당 password가 뭘로 해쉬가돼서 회원가입이 되었는지 알아야
	// 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교를 할 수 있다.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD()); 
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()  //csrf 토큰 비활성화 (테스트시 걸어두는 것이 좋음)
			.authorizeRequests()
				.antMatchers("/","/auth/**","/js/**","/css/**","/image/**","/dummy/**") //auth쪽으로 들어오는건 모두 허용하겠다.
				.permitAll()
				.anyRequest() //그 외의것은
				.authenticated() //인증이필요함.
			.and()
				.formLogin()
				.loginPage("/auth/loginForm")
				.loginProcessingUrl("/auth/loginProc") //스프링 시큐리티가 해당 주소로 오는 로그인을 가로채서 대신 로그인해줌
				.defaultSuccessUrl("/");
		//user detail타입의 오브젝트를 만들어야함.
		//why? 타입이 user detail이아니면 등록이 안됨. 그러므로 extends해서 클래스하나만들어야함.
	}

}
