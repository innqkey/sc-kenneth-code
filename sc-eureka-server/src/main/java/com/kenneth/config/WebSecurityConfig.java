package com.kenneth.config;

/** 
 * Spring Cloud Finchley及更高版本，必须添加如下代码，部分关闭掉Spring Security的CSRF保护功能，否则应用无法正常注册！
 * ref: http://cloud.spring.io/spring-cloud-netflix/single/spring-cloud-netflix.html#_securing_the_eureka_server
 * @author: qin kai
 * @Date: 2019年3月29日 下午3:43:36
 */
/*@EnableWebSecurity 
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().ignoringAntMatchers("/eureka/**");
		super.configure(http);
	}
}*/
