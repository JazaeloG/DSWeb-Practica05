package org.uv.dswebpractica05.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.uv.dswebpractica05.models.Rol;

@Component
public class JwtUtils {
    private final String jwtSecret = "clavessecretammuysseguraparadesarrollarensecreto";
    private int expiracionJWT = 86400000;
    
    public String generateJwtToken(String username, Set<Rol> roles) {
        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles.stream().map(Rol::getNombre).collect(Collectors.toList()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + expiracionJWT))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public String getUsernameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("JWT no válido o expirado");
        }
        return false;
    }
    
    public Collection<GrantedAuthority> getAuthorities(String token) {
    Claims claims = Jwts.parser()
                        .setSigningKey(jwtSecret)
                        .parseClaimsJws(token)
                        .getBody();
    
    List<String> roles = (List<String>) claims.get("roles");
    return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

}
