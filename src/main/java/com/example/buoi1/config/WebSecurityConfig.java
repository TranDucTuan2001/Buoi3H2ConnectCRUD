package com.example.buoi1.config;
import com.example.buoi1.security.UserDetailsServiceIml;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
        @Bean
    UserDetailsService userDetailsService() {
        return new UserDetailsServiceIml();
    }
    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(authenticationProvider);
    }


    //    @Bean
//    protected UserDetailsService userDetailsService() {
//        UserDetails user = User.builder()
//                .username("user")
//                .password(passwordEncoder().encode("password"))
//                .roles("USER", "ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }
    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(
//                        request -> request
//                                .requestMatchers("/").permitAll()
//
////                                .requestMatchers("/register").permitAll()
//                                .requestMatchers("/addUser").hasAnyRole("ADMIN")
//                                .anyRequest().authenticated()
//                )
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .permitAll())
//                .logout(config -> config
//                        .logoutSuccessUrl("/login"))
//                .build();
//    }
@Bean
protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
            .csrf(AbstractHttpConfigurer::disable) // Tắt CSRF để hỗ trợ H2 Console
            .headers(headers -> headers.frameOptions(frame -> frame.disable())) // Tắt frameOptions theo API mới
            .authorizeHttpRequests(request -> request
                    .requestMatchers("/h2-console/**").permitAll() // Cho phép truy cập H2 Console
                    .requestMatchers("/", "/auth/register").permitAll()
                    .requestMatchers("/addUser").hasAnyRole("ADMIN")
                    .anyRequest().authenticated()
            )
            .formLogin(form -> form
                    .loginPage("/login")
                    .permitAll())
            .logout(config -> config
                    .logoutSuccessUrl("/login"))
            .build();
}




//    @Bean
//    protected UserDetailsServices userDetailsService() {
//        UserDetails user = User.builder()
//                .username("user")
//                .password(passwordEncoder().encode("123456"))
//                .roles("USER")
//                .build();
//
//
//
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("123456"))
//                .roles("ADMIN")  // Đặt vai trò ADMIN cho tài khoản này
//                .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }

}