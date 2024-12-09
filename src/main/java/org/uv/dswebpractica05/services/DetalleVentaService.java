package org.uv.dswebpractica05.services;

import javax.transaction.Transactional;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uv.dswebpractica05.dtos.DetalleVentaDTO;
import org.uv.dswebpractica05.models.DetalleVenta;
import org.uv.dswebpractica05.models.Producto;
import org.uv.dswebpractica05.models.Venta;
import org.uv.dswebpractica05.repositories.DetalleVentaRepository;
import org.uv.dswebpractica05.repositories.ProductoRepository;
import org.uv.dswebpractica05.repositories.VentaRepository;
import org.uv.dswebpractica05.utils.ResourceNotFoundException;

@Service
public class DetalleVentaService {
    
    @Autowired
    private DetalleVentaRepository detalleVentaRepository;
    
    @Autowired
    private VentaRepository ventaRepository;
    
    @Autowired
    private ProductoRepository productoRepository;

    @Transactional
    public DetalleVenta crearDetalleVenta(DetalleVentaDTO detalleVentaDTO) {
        Venta venta = ventaRepository.findById(detalleVentaDTO.getIdVenta())
                .orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada con el ID: " + detalleVentaDTO.getIdVenta()));

        Producto producto = productoRepository.findById(detalleVentaDTO.getIdProducto())
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con el ID: " + detalleVentaDTO.getIdProducto()));

        DetalleVenta detalleVenta = new DetalleVenta();
        detalleVenta.setVenta(venta);
        detalleVenta.setProducto(producto);
        detalleVenta.setPrecioProducto(producto.getPrecio());
        detalleVenta.setCantidad(detalleVentaDTO.getCantidad());

        if (detalleVenta.getPrecioProducto() <= 0 || detalleVenta.getCantidad() <= 0) {
            throw new ValidationException("El precio y la cantidad deben ser mayores a cero.");
        }
        detalleVenta.calcularSubtotal();

        return detalleVentaRepository.save(detalleVenta);
    }
}