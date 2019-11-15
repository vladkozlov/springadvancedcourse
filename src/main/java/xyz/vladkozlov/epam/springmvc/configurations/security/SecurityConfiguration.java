package xyz.vladkozlov.epam.springmvc.configurations.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration {

    /**
     * H2-Console config.
     * It has a first order.
     */
    @Configuration
    @Order(1)
    public static class H2ConsoleConfigurationAdapter extends WebSecurityConfigurerAdapter {
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().antMatchers("/h2-console/**").permitAll();
            http.antMatcher("/h2-console/**").headers().frameOptions().sameOrigin();
            http.csrf().ignoringAntMatchers("/h2-console/**").disable();
        }
    }


    /**
     * Web-security config.
     * Follows after h2-config.
     */
    @Configuration
    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Autowired
        private CustomUserDetailService customUserDetailService;

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/users/**").access("hasAuthority('REGISTERED_USER') and hasAuthority('BOOKING_MANAGER')")
                    .anyRequest().hasAuthority("REGISTERED_USER")
                    .and()
                    .formLogin(Customizer.withDefaults());
        }

        @Bean
        public DaoAuthenticationProvider authProvider() {
            DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
            authProvider.setUserDetailsService(customUserDetailService);
            authProvider.setPasswordEncoder(passwordEncoder());
            return authProvider;
        }

    }


}