package org.uv.dswebpractica05.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.uv.dswebpractica05.dtos.LoginDTO;
import org.uv.dswebpractica05.dtos.UsuarioRegisterDTO;
import org.uv.dswebpractica05.models.Rol;
import org.uv.dswebpractica05.models.Usuario;
import org.uv.dswebpractica05.repositories.RolRepository;
import org.uv.dswebpractica05.repositories.UsuarioRepository;
import org.uv.dswebpractica05.security.JwtUtils;
import org.uv.dswebpractica05.utils.ResourceNotFoundException;

@Service
public class AuthService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;
    
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Transactional
    public Usuario registerUsuario(UsuarioRegisterDTO usuarioDTO) {
        if (usuarioRepository.findByUsername(usuarioDTO.getUsername()).isPresent()) {
            throw new ValidationException("El nombre de usuario ya está en uso.");
        }

        Set<Rol> roles = usuarioDTO.getRoles().stream()
            .map(roleNombre -> rolRepository.findByNombre(roleNombre)
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado: " + roleNombre)))
            .collect(Collectors.toSet());

        Usuario usuario = new Usuario();
        usuario.setUsername(usuarioDTO.getUsername());
        usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        usuario.setRoles(roles);

        return usuarioRepository.save(usuario);
    }
    
    @Transactional
    public String loginUsuario(LoginDTO loginDTO) {
        Usuario usuario = usuarioRepository.findByUsername(loginDTO.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario No Tiene Cuenta"));

        if (!passwordEncoder.matches(loginDTO.getPassword(), usuario.getPassword())) {
            throw new ValidationException("Contraseña incorrecta.");
        }

        Set<Rol> roles = usuario.getRoles();
        return jwtUtils.generateJwtToken(usuario.getUsername(), roles);
    }
}
