package com.team5.fandom.config.security;

import com.team5.fandom.security.FanUserDetails;
import com.team5.fandom.service.UserService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

@EnableMethodSecurity(prePostEnabled = true,securedEnabled = true)
@EnableWebSecurity
@Configuration

public class SecurityConfig {

    private final CorsConfigurationSource corsConfigurationSource;

    public SecurityConfig(CorsConfigurationSource corsConfigurationSource) {
        this.corsConfigurationSource = corsConfigurationSource;
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {

        security // CSRF 설정: API 경로만 비활성화
                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"))
                //CORS 설정 활성화
                .cors(cors -> cors.configurationSource(corsConfigurationSource)); // CORS 설정 적용
        security.authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                .requestMatchers("/u/details/**").hasRole("USER")
                                .requestMatchers("/f/admin/**").hasRole("ADMIN")
                                .requestMatchers("/assets/**").permitAll() // Allow static assets
                                .requestMatchers("/login","/signup", "/register", "/", "/f/c", "/f", "/f/a", "/f/a/{artist_id}").permitAll()
                                .anyRequest().authenticated()
        );
        security.formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("email") // 사용자 이름 필드를 이메일로 설정
                .passwordParameter("password") // 비밀번호 필드 설정
                .defaultSuccessUrl("/f/c"));

        security.logout(logout -> logout.logoutSuccessUrl("/f/c"));
        //세션
        security.sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer
                .invalidSessionUrl("/login")
                .sessionFixation().migrateSession() //로그인후 세션 변경
                .maximumSessions(1)//한개만 유지
                .expiredUrl("/login"));//만료시 이동

        return security.build();

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
