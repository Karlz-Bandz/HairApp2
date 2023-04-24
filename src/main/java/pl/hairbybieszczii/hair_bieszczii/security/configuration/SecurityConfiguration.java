package pl.hairbybieszczii.hair_bieszczii.security.configuration;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import pl.hairbybieszczii.hair_bieszczii.security.models.EntityUser;
import pl.hairbybieszczii.hair_bieszczii.security.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration {

    private CustomUserDetailsService customUserDetailsService;



    @Bean
    public SecurityFilterChain config(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/image/**", "/price/**", "/treatments/**", "/api/auth/login").permitAll()
                .antMatchers(HttpMethod.POST, "/api/auth/login", "/mail/**", "/api/auth/**").permitAll()
                .antMatchers("/api/client/delete/**").hasAuthority("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
