package br.edu.ufersa.cc.lpoo.scale_flow.config;

import java.io.IOException;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.edu.ufersa.cc.lpoo.scale_flow.services.auth.TokenService;
import br.edu.ufersa.cc.lpoo.scale_flow.services.users.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private UserService userService;

    @Override
    protected void doFilterInternal(final @NonNull HttpServletRequest request,
            final @NonNull HttpServletResponse response, final @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        recoverToken(request)
                .ifPresent(token -> {
                    final var email = tokenService.validateToken(token);
                    final var user = userService.findByEmailWithPassword(email);

                    final var authentication = new UsernamePasswordAuthenticationToken(user, null,
                            user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                });

        filterChain.doFilter(request, response);
    }

    private Optional<String> recoverToken(final HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader("Authorization"))
                .map(authorization -> authorization.replace("Bearer ", ""));
    }

}
