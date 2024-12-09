package org.uv.dswebpractica05.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.uv.dswebpractica05.models.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{
    Optional<Producto> findByNombre(String nombre);
}
