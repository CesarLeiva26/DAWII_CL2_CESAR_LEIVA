package edu.pe.cibertec.DAWII_CL2_CESAR_LEIVA.Configuration;

import edu.pe.cibertec.DAWII_CL2_CESAR_LEIVA.Service.DetalleUsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final DetalleUsuarioService detalleUsuarioService;

    @Bean
    public SecurityFilterChain config (HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(
                        auth ->
                                auth.requestMatchers("/auth/login",
                                        "/auth/registrar",
                                        "/auth/guardarUsuario",
                                        "/resources/**",
                                        "/static/**",
                                        "/styles/**",
                                        "/scripts/**")
                                        .permitAll()
                                        .anyRequest()
                                        .authenticated()
                ).formLogin(
                        login ->
                                login.loginPage("/auth/login")
                                        .defaultSuccessUrl("/auth/login-usuario")
                                        .usernameParameter("nomusuario")
                                        .passwordParameter("password")
                ).authenticationProvider(authenticationProvider());
        return http.build();
    }



    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(detalleUsuarioService);
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return authenticationProvider;
    }

}
