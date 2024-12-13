package com.team5.fandom.config;

import com.team5.fandom.security.FanUserDetails;
import com.team5.fandom.service.UserService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@EnableMethodSecurity(prePostEnabled = true,securedEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {

        security
                .csrf(csrf -> csrf.disable());
                //.cors(cors -> cors.disable());
        security.authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                .requestMatchers("/u/details/**").hasRole("USER")
                                .requestMatchers("/f/admin/**").hasRole("ADMIN")
                                .requestMatchers("/login", "/register", "/", "/f/c", "/f", "/f/a", "/f/a/{artist_id}")
                                .permitAll()
                                .anyRequest().authenticated()
        );
        security.formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/f/c"));

        security.logout(logout -> logout.logoutSuccessUrl("/f/c"));
        //세션
        security.sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer
                .invalidSessionUrl("/login")
                .sessionFixation().migrateSession() //로그인후 세션 변경
                .maximumSessions(1)//한개만 유지
                .maxSessionsPreventsLogin(false)
                .sessionRegistry(sessionRegistry())
                .expiredUrl("/login"));//만료시 이동

        return security.build();

    }
    @Bean
    ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
    }
    @Bean
    SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public UserDetailsService userDetailsService(UserService userService) {
        return email -> userService.searchUserByEmail(email).map(FanUserDetails::toFUDto)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }



    @Bean
    public PasswordEncoder passwordEncoder() {

        //return NoOpPasswordEncoder.getInstance();


        return new BCryptPasswordEncoder();
    }
}
