package br.net.alexdev.dashboard.security.jwt;

import br.net.alexdev.dashboard.security.UserDetailsImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.Set;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${security.config.key}")
    private String jwtSecret;

    @Value("${security.config.expiration.token}")
    private int jwtExpirationMs;
    @Value("${security.config.expiration.refreshtoken}")
    private int refreshTokenExpirationMs;



    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .verifyWith(key()).build()
                .parseSignedClaims(token).getPayload().getSubject();
    }

    public String generateJwtToken(Authentication authentication) {
        return generateToken(authentication, jwtExpirationMs);
    }


    public String generateRefreshToken(Authentication authentication) {
        return generateToken(authentication, refreshTokenExpirationMs);
    }

    private SecretKey key() {
        Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
        byte[] secretKeyBytes = key.getEncoded();
        return new SecretKeySpec(secretKeyBytes, "HmacSHA256");
    }

    public boolean validateJwtToken(String authToken) {
        try {
            String iss= Jwts.parser().verifyWith(key()).build().parseSignedClaims(authToken).getPayload().getIssuer();
            if (!"https://alexdev.net.br".equals(iss)) {
                logger.error("Reivindicação 'iss' inválida");
                return false;
            }
            Set<String> aud =Jwts.parser().verifyWith(key()).build().parseSignedClaims(authToken).getPayload().getAudience();
            if (!aud.contains("dashboard-api")) {
                logger.error("Reivindicação 'aud' inválida");
                return false;
            }

            return true;
        } catch (MalformedJwtException e) {
            logger.error("Token JWT inválido: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("Token JWT expirado: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("Token JWT não compatível: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("A string de declarações JWT está vazia: {}", e.getMessage());
        }

        return false;
    }

    private String generateToken(Authentication authentication, int expirationMs) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .issuer("https://alexdev.net.br")
                .audience().add("dashboard-api").and()
                .subject(userPrincipal.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + expirationMs))
                .signWith(key())
                .compact();
    }
}
