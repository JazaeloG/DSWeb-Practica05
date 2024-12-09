package org.uv.dswebpractica05.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.uv.dswebpractica05.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    Optional<Cliente> findByCorreo(String correo);
}
