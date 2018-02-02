package com.mdc.web.configuration;

import com.mdc.autentification.UserAuthenticationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@PropertySource("classpath:security.properties")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);
    private final Environment env;
    private final UserAuthenticationProvider userAuthenticationProvider;

    @Autowired
    protected SecurityConfig(Environment env,UserAuthenticationProvider userAuthenticationProvider) {
        this.env = env;
        this.userAuthenticationProvider = userAuthenticationProvider;
    }

    @Override
    public void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(userAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers(env.getProperty("location.resources")).permitAll()
                .antMatchers(env.getProperty("location.login")).access("hasRole('ANONYMOUS')")
                .antMatchers(env.getProperty("location.logout")).access("hasRole('USER')")
                .anyRequest().authenticated()

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
                .invalidateHttpSession(true)

                .and().httpBasic()
                .and().csrf().disable();


    }




    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring().antMatchers(env.getProperty("location.resources"));
    }


}
