package com.auth.auth_nutri.config.security;

import com.auth.auth_nutri.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
    @Autowired
    private SecurityFilter securityFilter;

    @Autowired
    private AuthService authService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> {
                    configurarPublicas(auth);
                    configurarAdmin(auth);
                    configurarPaciente(auth);
                    configurarMedico(auth);
                    auth.anyRequest().authenticated();
                })
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    private void  configurarPublicas(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry auth){
        auth
                .requestMatchers(HttpMethod.POST, "/email/**").permitAll()
            .requestMatchers(HttpMethod.POST, "/auth/**").permitAll()
            .requestMatchers(HttpMethod.GET, "/medico/pagination").permitAll()
            .requestMatchers(org.springframework.http.HttpMethod.OPTIONS, "/**").permitAll();
    }

    private void configurarAdmin(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry auth){
        auth
             .requestMatchers(HttpMethod.POST, "/medico/admin/**").hasRole("ADMIN")
             .requestMatchers(HttpMethod.POST, "/paciente/admin/**").hasRole("ADMIN")
             .requestMatchers("/admin/**").hasRole("ADMIN")
             .requestMatchers(HttpMethod.GET, "admin/**").hasRole("ADMIN");
    }

    private void configurarPaciente(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry auth) {
        auth
                //.requestMatchers("/paciente/**").hasRole("PACIENTE");
                .requestMatchers("/paciente/**").permitAll();
    }

    private void configurarMedico(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry auth) {
        auth
                .requestMatchers(HttpMethod.GET, "/medico/**").hasRole("MEDICO")
                .requestMatchers(HttpMethod.POST, "/medico/**").hasRole("MEDICO")
                .requestMatchers(HttpMethod.PATCH, "/medico/**").hasRole("MEDICO")
                .requestMatchers(HttpMethod.DELETE, "/medico/**").hasRole("MEDICO")
                .requestMatchers(HttpMethod.PUT, "/medico/**").hasRole("MEDICO");
    }




   @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
       return authenticationConfiguration.getAuthenticationManager();
    }

   @Bean
        public PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
   }

}
