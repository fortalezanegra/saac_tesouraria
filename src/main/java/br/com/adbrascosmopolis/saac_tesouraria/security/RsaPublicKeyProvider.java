package br.com.adbrascosmopolis.saac_tesouraria.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
public class RsaPublicKeyProvider {

    @Value("${jwt.public-key-path}")
    private String publicKeyPath;

    private final ResourceLoader resourceLoader;

    private PublicKey publicKey;

    public RsaPublicKeyProvider(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @PostConstruct
    public void init() throws Exception {
        this.publicKey = loadPublicKey();
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    private PublicKey loadPublicKey() throws Exception {
        Resource resource = resourceLoader.getResource(publicKeyPath);

        String content;
        try (InputStream is = resource.getInputStream()) {
            content = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }

        String key = content
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "");

        byte[] keyBytes = Base64.getDecoder().decode(key);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance("RSA").generatePublic(spec);
    }
}

