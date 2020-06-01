package main.security;

import main.security.jwt.JwtSecurityConfigurer;
import main.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .csrf().disable()
                .formLogin().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/bt/auth/signin").permitAll()
                .antMatchers(HttpMethod.GET, "/bt/marks/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/bt/people/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/bt/subjects/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/bt/groups/{id}").permitAll()
//                .antMatchers(HttpMethod.GET, "/bt/marks/{id}").hasRole ("ADMIN")
//                .antMatchers(HttpMethod.GET, "/bt/people/{id}").hasRole ("ADMIN")
//                .antMatchers(HttpMethod.GET, "/bt/subjects/{id}").hasRole ("ADMIN")
//                .antMatchers(HttpMethod.GET, "/bt/groups/{id}").hasRole ("ADMIN")
                .antMatchers(HttpMethod.POST, "/bt/addMarks").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/bt/addPeople").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/bt/addSubjects").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/bt/addGroups").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/bt/deleteMarks/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/bt/deletePeople/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/bt/deleteSubjects/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/bt/deleteGroups/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/bt/marks").permitAll()
                .antMatchers(HttpMethod.GET, "/bt/people").permitAll()
                .antMatchers(HttpMethod.GET, "/bt/subjects").permitAll()
                .antMatchers(HttpMethod.GET, "/bt/groups").permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(new JwtSecurityConfigurer(jwtTokenProvider));

    }

}
