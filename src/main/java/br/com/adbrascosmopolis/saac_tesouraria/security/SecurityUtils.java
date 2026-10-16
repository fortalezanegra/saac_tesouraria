package br.com.adbrascosmopolis.saac_tesouraria.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    private SecurityUtils() {
    }

    public static UserAuthenticated getCurrentUser() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !(auth.getPrincipal() instanceof UserAuthenticated user)) {
            throw new IllegalStateException("Usuário não autenticado");
        }
        return user;
    }

    public static Long currentUserId() {
        return getCurrentUser().getUserId();
    }

    public static Long currentUnityId() {
        return getCurrentUser().getUnityId();
    }

    public static String currentEmail() {
        return getCurrentUser().getUsername();
    }

    public static boolean hasAuthority(String authority) {
        return getCurrentUser().getAuthorities().stream()
                .map(org.springframework.security.core.GrantedAuthority::getAuthority)
                .anyMatch(authority::equals);
    }
}
