package com.mdc.web.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //Move this properties
    private static final String RESOURCES_LOCATION = "/resources/**";
    private static final String LOGIN_LOCATION = "/login/form/**";
    private static final String LOGOUT_LOCATION = "/logout/**";
    private static final String GLOBAL_LOCATION = "/**";
    private static final String ACCESS_DENITE_LOCATION = "/errors/403";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.authorizeRequests()
                .antMatchers(RESOURCES_LOCATION).permitAll()
                .antMatchers(LOGIN_LOCATION).access("hasRole('ANONYMOUS')")
                .antMatchers(LOGOUT_LOCATION).access("hasRole('USER')")
                .antMatchers(GLOBAL_LOCATION).access("hasRole('USER')")
                .and().exceptionHandling().accessDeniedPage(ACCESS_DENITE_LOCATION)

                .and().formLogin()
                .loginPage("/login/form")
                .loginProcessingUrl("/login")
                .failureUrl("/login/form?error")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/dashboard")

                .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .and().httpBasic().and().csrf().disable();

    }

    @Override
    public void configure(final AuthenticationManagerBuilder auth) throws Exception {
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
