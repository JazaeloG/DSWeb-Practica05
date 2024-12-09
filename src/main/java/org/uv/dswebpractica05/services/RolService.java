package org.uv.dswebpractica05.services;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uv.dswebpractica05.models.Rol;
import org.uv.dswebpractica05.repositories.RolRepository;

@Service
public class RolService {
    @Autowired
    private RolRepository rolRepository;
    
    @Transactional
    public Rol registrarRol(String rolNombre) {
        if (rolRepository.findByNombre(rolNombre).isPresent()) {
            throw new IllegalArgumentException("El rol '" + rolNombre + "' ya está registrado.");
        }

        
        Rol nuevoRol = new Rol();
        nuevoRol.setNombre(rolNombre);

        
        return rolRepository.save(nuevoRol);
    }
}
