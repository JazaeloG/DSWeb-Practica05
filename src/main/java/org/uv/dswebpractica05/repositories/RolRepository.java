package org.uv.dswebpractica05.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.uv.dswebpractica05.models.Rol;

public interface RolRepository extends JpaRepository<Rol, Long>{
    Optional<Rol> findByNombre(String nombre);
}
