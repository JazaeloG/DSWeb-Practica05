package org.uv.dswebpractica05.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.uv.dswebpractica05.models.DetalleVenta;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long>{
    
}
