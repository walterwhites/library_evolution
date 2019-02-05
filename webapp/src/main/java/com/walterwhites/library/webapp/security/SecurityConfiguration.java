package com.walterwhites.library.webapp.security;

import com.walterwhites.library.business.utils.RoleEnum;
import com.walterwhites.library.webservice.services.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"com.walterwhites.library"})
@EnableAutoConfiguration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final String adminRole = RoleEnum.ADMINISTRATOR.name();

    private final MyUserService userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    public SecurityConfiguration(MyUserService myUserService) {
        this.userDetailsService = myUserService;
    }

    @Bean(name = "passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin**").hasAuthority(adminRole)
                .anyRequest().permitAll()
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/dashboard").failureUrl("/login?error=true")
                .usernameParameter("username").passwordParameter("password")
                .and()
                .logout().invalidateHttpSession(true)
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .and()
                .csrf()
                .and()
                .sessionManagement().maximumSessions(1).expiredUrl("/login");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public AuthenticationProvider getProvider() {
        AppAuthProvider provider = new AppAuthProvider();
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
}