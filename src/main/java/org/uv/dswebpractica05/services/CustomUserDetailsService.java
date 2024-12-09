
package org.uv.dswebpractica05.services;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.uv.dswebpractica05.models.Usuario;
import org.uv.dswebpractica05.repositories.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        return User.builder()
        .username(usuario.getUsername())
        .password(usuario.getPassword())
        .authorities(usuario.getRoles().stream()
                  .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getNombre()))
                  .collect(Collectors.toList()))
        .build();
    }
}
