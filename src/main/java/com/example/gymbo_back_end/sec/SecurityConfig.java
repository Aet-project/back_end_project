package com.example.gymbo_back_end.sec;


import com.example.gymbo_back_end.jwt.JwtAuthenticationFilter;
import com.example.gymbo_back_end.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/v1/**").permitAll()
                .antMatchers("/v1/auth/**").permitAll()
                .antMatchers("/v1/payments/**").hasRole("USER")
                .antMatchers("/v1/email/**").permitAll()
                .antMatchers("/v1/members/**").permitAll()
                .antMatchers("/v1/orders/**").permitAll()
                .antMatchers("/v1/gyms/**").permitAll()
                .antMatchers("/v1/reservation/**").permitAll()
                .antMatchers("/v1/ticket/**").permitAll()
                .antMatchers("/v1/order/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}

