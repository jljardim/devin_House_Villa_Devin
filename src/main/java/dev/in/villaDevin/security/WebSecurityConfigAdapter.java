package dev.in.villaDevin.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import dev.in.villaDevin.security.filters.JWTAuthenticationFilter;
import dev.in.villaDevin.security.filters.JWTAuthorizationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfigAdapter extends WebSecurityConfigurerAdapter {

    private static final String[] PUBLIC_MATCHERS_POST = { "/login/**" };
    private final JWTTokenUtils jwtTokenUtils;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfigAdapter(JWTTokenUtils jwtUtil, UserDetailsServiceImpl userDetailsService, PasswordEncoder passwordEncoder){
        this.jwtTokenUtils = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .cors()
            .and()
            .csrf()
            .disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST) // Todos post para as rotas contidas em public_matchers_post
            .permitAll() // permita.
            .anyRequest() // Qualquer outra req
            .authenticated(); // precisa estar autenticada

        http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtTokenUtils)); //sobrescrever rota de autenticação padrão e outras coisas
        http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtTokenUtils, userDetailsService));

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
    
    
}
