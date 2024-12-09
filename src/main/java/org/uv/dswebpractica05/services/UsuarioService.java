package org.uv.dswebpractica05.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.uv.dswebpractica05.dtos.UsuarioRegisterDTO;
import org.uv.dswebpractica05.models.Rol;
import org.uv.dswebpractica05.models.Usuario;
import org.uv.dswebpractica05.repositories.RolRepository;
import org.uv.dswebpractica05.repositories.UsuarioRepository;
import org.uv.dswebpractica05.utils.ResourceNotFoundException;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
        
    @Transactional
    public List<Usuario> obtenerUsuarios(){
        return usuarioRepository.findAll();
    }
    
    @Transactional
    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con el ID: " + id));
    }

    @Transactional
    public Usuario actualizarUsuario(Long id, UsuarioRegisterDTO usuarioDTO) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con el ID: " + id));

        if (!usuarioExistente.getUsername().equals(usuarioDTO.getUsername()) &&
            usuarioRepository.findByUsername(usuarioDTO.getUsername()).isPresent()) {
            throw new ValidationException("El nombre de usuario ya está en uso.");
        }

        Set<Rol> roles = usuarioDTO.getRoles().stream()
            .map(roleNombre -> rolRepository.findByNombre(roleNombre)
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado: " + roleNombre)))
            .collect(Collectors.toSet());

        usuarioExistente.setUsername(usuarioDTO.getUsername());
        usuarioExistente.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        usuarioExistente.setRoles(roles);

        return usuarioRepository.save(usuarioExistente);
    }

    @Transactional
    public void eliminarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuario no encontrado con el ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }
}
