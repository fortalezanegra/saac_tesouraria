package br.com.adbrascosmopolis.saac_tesouraria.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Component
public class JwtService {

    private final RsaPublicKeyProvider rsaPublicKeyProvider;

    public JwtService(RsaPublicKeyProvider rsaPublicKeyProvider) {
        this.rsaPublicKeyProvider = rsaPublicKeyProvider;
    }

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Long extractUserId(String token) {
        Number n = extractClaim(token, c -> c.get("userId", Number.class));
        return n != null ? n.longValue() : null;
    }

    public Long extractUnityId(String token) {
        Number n = extractClaim(token, c -> c.get("unityId", Number.class));
        return n != null ? n.longValue() : null;
    }

    @SuppressWarnings("unchecked")
    public List<String> extractAuthorities(String token) {
        List<?> raw = extractClaim(token, c -> c.get("authorities", List.class));
        if (raw == null) {
            return List.of();
        }
        return raw.stream()
                .map(Object::toString)
                .toList();
    }

    public boolean validToken(String token, String email) {
        String extractedEmail = extractEmail(token);
        return extractedEmail != null && extractedEmail.equals(email) && !expiredToken(token);
    }

    private boolean expiredToken(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    private <T> T extractClaim(String token, Function<Claims, T> resolver) {
        return resolver.apply(extractAllClaims(token));
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(rsaPublicKeyProvider.getPublicKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
