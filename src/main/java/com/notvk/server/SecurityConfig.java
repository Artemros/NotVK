package com.notvk.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user1 = User.withUsername("user1")
                .password(passwordEncoder().encode("123"))
                .roles("USER")
                .build();
        UserDetails user2 = User.withUsername("user2")
                .password(passwordEncoder().encode("user2Pass"))
                .roles("USER")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder().encode("adminPass"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user1, user2, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf()
//                .disable()
//                .authorizeHttpRequests()
//                .requestMatchers(new MvcRequestMatcher(new HandlerMappingIntrospector(),"/admin/**"))
//                .hasRole("ADMIN")
//                .requestMatchers(new MvcRequestMatcher(new HandlerMappingIntrospector(),"/anonymous*"))
//                .anonymous()
//                .requestMatchers(new MvcRequestMatcher(new HandlerMappingIntrospector(),"/login*"))
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin()
////                    .loginPage("/login")
//////                    .loginProcessingUrl("/login/a");
//                .defaultSuccessUrl("/id1", true);
////                    .failureUrl("/login.html?error=true")
////                    .failureHandler(authenticationFailureHandler());
////                .and()
////                .logout()
////                    .logoutUrl("/perform_logout")
////                    .deleteCookies("JSESSIONID")
////                    .logoutSuccessHandler(logoutSuccessHandler());
//        return http.build();
//        // ...
//    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }


   /* @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new MySimpleUrlAuthenticationSuccessHandler();
    }
*/
}
