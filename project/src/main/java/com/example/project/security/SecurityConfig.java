package com.example.project.security;

import com.example.project.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.example.project.security.Permission.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailService userDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/projectOne","/css/*","/img/*").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/projectOne/newUser").permitAll()
                .antMatchers("/pokupka").permitAll()
                .antMatchers("/projectOne","/projectOne/noutTable").hasAuthority(WRITE.getPermissionInfo())
                .antMatchers("/projectOne/","projectOne/addNout").hasAuthority(WRITE.getPermissionInfo())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .defaultSuccessUrl("/projectOne", true)
                .and()
                .logout().logoutSuccessUrl("/projectOne")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        return daoAuthenticationProvider;
    }
}