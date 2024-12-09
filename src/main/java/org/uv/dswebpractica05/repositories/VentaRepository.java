package org.uv.dswebpractica05.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.uv.dswebpractica05.models.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long>{
    
}
