package com.example.estramipymes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Configura el AuthenticationManager para gestionar la autenticación
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Configura el codificador de contraseñas (BCrypt es una buena opción)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Define un UserDetailsService personalizado para cargar los detalles de los usuarios
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            // Aquí deberías implementar la lógica para cargar el usuario desde tu base de datos
            // Este es un ejemplo básico con un usuario "admin" con rol "USER"
            if ("admin".equals(username)) {
                return User.withUsername("admin")
                        .password(passwordEncoder().encode("password")) // Contraseña encriptada
                        .roles("USER") // Asigna roles al usuario
                        .build();
            }
            throw new UsernameNotFoundException("User not found");
        };
    }

    // Configura el SecurityFilterChain para gestionar las solicitudes HTTP
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Deshabilita CSRF (puedes habilitarlo si lo necesitas)
            .authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/api/auth/**").permitAll() // Permite el acceso sin autenticación a estas rutas
                .anyRequest().authenticated() // Requiere autenticación para cualquier otra ruta
            )
            .httpBasic(); // Usa autenticación básica (puedes configurar JWT si es necesario)

        return http.build();
    }
}