package org.uv.dswebpractica05.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uv.dswebpractica05.dtos.DetalleVentaDTO;
import org.uv.dswebpractica05.dtos.VentaDTO;
import org.uv.dswebpractica05.models.Cliente;
import org.uv.dswebpractica05.models.DetalleVenta;
import org.uv.dswebpractica05.models.Producto;
import org.uv.dswebpractica05.models.Venta;
import org.uv.dswebpractica05.repositories.ClienteRepository;
import org.uv.dswebpractica05.repositories.ProductoRepository;
import org.uv.dswebpractica05.repositories.VentaRepository;
import org.uv.dswebpractica05.utils.ResourceNotFoundException;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;
    
    @Transactional
    public Venta crearVenta(VentaDTO ventaDTO) {
        Cliente cliente = clienteRepository.findById(ventaDTO.getIdCliente())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con el ID: " + ventaDTO.getIdCliente()));

        Venta venta = new Venta();
        venta.setCliente(cliente);
        venta.setFechaVenta(new Date());

        List<DetalleVenta> detallesVenta = new ArrayList<>();
        for (DetalleVentaDTO detalleDTO : ventaDTO.getDetallesVenta()) {
            Producto producto = productoRepository.findById(detalleDTO.getIdProducto())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con el ID: " + detalleDTO.getIdProducto()));

            DetalleVenta detalleVenta = new DetalleVenta();
            detalleVenta.setVenta(venta);
            detalleVenta.setProducto(producto);
            detalleVenta.setCantidad(detalleDTO.getCantidad());
            detalleVenta.setPrecioProducto(producto.getPrecio());
            detalleVenta.calcularSubtotal();

            detallesVenta.add(detalleVenta);
        }

        venta.setDetallesVenta(detallesVenta);
        venta.calcularTotalVenta();

        return ventaRepository.save(venta);
    }

    @Transactional
    public List<Venta> obtenerVentas() {
        return ventaRepository.findAll();
    }

    @Transactional
    public Venta obtenerVentaPorId(Long idVenta) {
        return ventaRepository.findById(idVenta)
                .orElseThrow(() -> new ResourceNotFoundException("Venta no encontrada con el ID: " + idVenta));
    }

    @Transactional
    public void eliminarVenta(Long idVenta) {
        if (!ventaRepository.existsById(idVenta)) {
            throw new ResourceNotFoundException("Venta no encontrada con el ID: " + idVenta);
        }
        ventaRepository.deleteById(idVenta);
    }
}