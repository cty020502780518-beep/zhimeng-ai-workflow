package com.zhimeng.ai.console.hub.config;

import com.zhimeng.ai.console.commons.config.MockUserFilter;
import com.zhimeng.ai.console.hub.config.security.RestfulAccessDeniedHandler;
import com.zhimeng.ai.console.hub.config.security.RestfulAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final MockUserFilter mockUserFilter;
    private final RestfulAuthenticationEntryPoint restfulAuthenticationEntryPoint;
    private final RestfulAccessDeniedHandler restfulAccessDeniedHandler;

    @Bean
    public SecurityFilterChain resourceServerFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest()
                        .permitAll() // 🔓 禁用 OAuth2 认证 - 允许所有请求通过（本地开发模式）
                )
                // ❌ OAuth2 Resource Server 已禁用
                // .oauth2ResourceServer(oauth2 -> oauth2
                // .jwt(Customizer.withDefaults()))
                // CSRF protection disabled - Safe because:
                // 1. Using OAuth2 Bearer token authentication (via Authorization header)
                // 2. Stateless session management (no cookies)
                // 3. CSRF attacks only affect cookie-based authentication
                // 4. Bearer tokens cannot be automatically sent by browsers
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint(restfulAuthenticationEntryPoint)
                        .accessDeniedHandler(restfulAccessDeniedHandler))
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                // Configure stateless session
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)

        ;

        // ✅ 添加 MockUserFilter 自动注入 admin 用户信息
        http.addFilterBefore(mockUserFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    // Configure CORS to allow your frontend application to access across domains
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Allow your frontend domain to access, e.g. "http://localhost:3000"
        // configuration.setAllowedOrigins(List.of("http://localhost:3000",
        // "https://your-frontend-domain.com"));
        configuration.setAllowedOriginPatterns(List.of("*"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        // Set to false for OAuth2 Bearer token authentication
        // Bearer tokens are sent via Authorization header, not cookies
        // allowCredentials is only needed for cookie-based authentication
        configuration.setAllowCredentials(false);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
