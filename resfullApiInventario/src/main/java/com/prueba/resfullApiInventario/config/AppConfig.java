package com.prueba.resfullApiInventario.config;

import com.prueba.resfullApiInventario.repository.EmpleadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AppConfig {
    private final EmpleadoRepository empleadoRepository;

    @Bean //Bean, para usarlo en el contexto de nuestra aplicación
    public UserDetailsService userDetailsService(){
        return username -> empleadoRepository.findEmployeeByMail(username)
                .orElseThrow(() -> new UsernameNotFoundException("El empleado no ha sido encontrado"));
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        //Serializa la contraseña del empleado al guardarla en la BD
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        /*
        Es como una clase que nos permite gestionar la autenticación mediante las credenciales
        que le enviemos como request para poder autenticarnos, es decir, le enviamos el usuario
        y password y nos autenticamos
         */
        return config.getAuthenticationManager();
    }
}
