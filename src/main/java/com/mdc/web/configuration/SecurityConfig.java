package com.mdc.web.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@PropertySource("classpath:security.properties")
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final Environment env;

    @Autowired
    protected SecurityConfig(Environment env) {
        this.env = env;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(env.getProperty("location.resources")).permitAll()
                .antMatchers(env.getProperty("location.login")).access("hasRole('ANONYMOUS')")
                .antMatchers(env.getProperty("location.logout")).access("hasRole('USER')")
                .antMatchers(env.getProperty("location.global")).access("hasRole('USER')")
                .and().exceptionHandling().accessDeniedPage(env.getProperty("location.access_denied"))

                .and().formLogin()
                .loginPage(env.getProperty("login.url"))
                .loginProcessingUrl(env.getProperty("login.processing.url"))
                .failureUrl(env.getProperty("login.failure.url"))
                .usernameParameter(env.getProperty("login.username.parameter"))
                .passwordParameter(env.getProperty("login.password.parameter"))
                .defaultSuccessUrl(env.getProperty("default.success.url"))

                .and().logout()
                .logoutUrl(env.getProperty("logout.url"))
                .logoutSuccessUrl(env.getProperty("logout.success.url"))

                .and().httpBasic()
                .and().csrf().disable();

    }


    @Override
    public void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password(new BCryptPasswordEncoder().encode("user")).roles("USER");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring().antMatchers(env.getProperty("location.resources"));
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
