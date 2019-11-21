package xyz.vladkozlov.epam.springmvc.configurations.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
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

    @Configuration
    @Order(2)
    public static class RestApiConfigurationAdapter extends WebSecurityConfigurerAdapter {
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/rest**").permitAll();
//            http.antMatcher("/h2-console/**").headers().frameOptions().sameOrigin();
//            http.csrf().ignoringAntMatchers("/h2-console/**").disable();
        }
    }


    /**
     * Web-security config.
     * Follows after h2-config.
     */
    @Configuration
    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        private CustomUserDetailService customUserDetailService;

        public FormLoginWebSecurityConfigurerAdapter(CustomUserDetailService customUserDetailService) {
            this.customUserDetailService = customUserDetailService;
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                        .antMatchers("/login*").permitAll()
                        .antMatchers("/users/**").access("hasAuthority('REGISTERED_USER') and hasAuthority('BOOKING_MANAGER')")
                        .antMatchers("/me**").access("hasAuthority('REGISTERED_USER')")
                        .anyRequest().hasAuthority("REGISTERED_USER")
                    .and()
                        .formLogin()
                            .loginPage("/login").permitAll()
                            .loginProcessingUrl("/login")
                            .defaultSuccessUrl("/me", true)
                            .failureUrl("/login?error")
                            .permitAll(true)
                    .and()
                        .logout()
                        .logoutSuccessUrl("/login?logout")
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                    .and()
                    .rememberMe()
                        .rememberMeParameter("remember-me-plz")
                        .key("secretSpringAdvancedKey")
                        .userDetailsService(customUserDetailService);
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