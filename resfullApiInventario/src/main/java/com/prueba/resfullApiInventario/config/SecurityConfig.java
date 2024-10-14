package com.prueba.resfullApiInventario.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity //Permite definir y habilitar la seguridad a nivel de métodos dentro de nuestra aplicación
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {
    private final JwtFilter jwtFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.requestMatchers(publicEndPoints()).permitAll() //Endpoints sin autenticación
                        .anyRequest().authenticated()//Cualquier otra petición a un endpoint, se necesita estar auténticado
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtFilter, //Filtro que se ejecutará antes del proceso de autenticación
                UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    private RequestMatcher publicEndPoints(){
        return new OrRequestMatcher(
                new AntPathRequestMatcher("/greeting"),
                new AntPathRequestMatcher("/api/empleados/saveEmployee"),
                new AntPathRequestMatcher("/api/empleados/authenticate")
        );
    }
}
