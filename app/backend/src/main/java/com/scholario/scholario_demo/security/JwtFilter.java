package com.scholario.scholario_demo.security;

import com.scholario.scholario_demo.service.CustomUserDetailsService;
import com.scholario.scholario_demo.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * The type Jwt filter.
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

  private final TokenService tokenService;
  private final CustomUserDetailsService customUserDetailsService;

  /**
   * Instantiates a new Jwt filter.
   *
   * @param tokenService             the token service
   * @param customUserDetailsService the custom user details service
   */
  @Autowired
  public JwtFilter(TokenService tokenService, CustomUserDetailsService customUserDetailsService) {
    this.tokenService = tokenService;
    this.customUserDetailsService = customUserDetailsService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    Optional<String> token = extractToken(request);

    if (token.isPresent()) {
      String subject = tokenService.validateToken(token.get());

      UserDetails userDetails = customUserDetailsService.loadUserByUsername(subject);

      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
          userDetails, null, userDetails.getAuthorities()
      );
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    filterChain.doFilter(request, response);



  }

  private Optional<String> extractToken(HttpServletRequest request) {
    String authHeader = request.getHeader("Authorization");

    if (authHeader == null) {
      return Optional.empty();
    }

    return Optional.of(
        authHeader.replace("Bearer ", "")
    );
  }




}
