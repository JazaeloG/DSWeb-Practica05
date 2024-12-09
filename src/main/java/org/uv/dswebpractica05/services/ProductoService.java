package org.uv.dswebpractica05.services;

import java.util.List;
import javax.transaction.Transactional;
import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uv.dswebpractica05.dtos.ProductoDTO;
import org.uv.dswebpractica05.models.Producto;
import org.uv.dswebpractica05.repositories.ProductoRepository;
import org.uv.dswebpractica05.utils.ResourceNotFoundException;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    @Transactional
    public Producto crearProducto(ProductoDTO productoDTO) {
        if (productoRepository.findByNombre(productoDTO.getNombre()).isPresent()) {
            throw new ValidationException("El producto ya existe.");
        }

        Producto producto = new Producto();
        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setStock(productoDTO.getStock());

        return productoRepository.save(producto);
    }

    @Transactional
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    @Transactional
    public Producto buscarProductoPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con el ID: " + id));
    }

    @Transactional
    public Producto actualizarProducto(Long id, ProductoDTO productoDTO) {
        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con el ID: " + id));

        if (!productoExistente.getNombre().equals(productoDTO.getNombre()) &&
            productoRepository.findByNombre(productoDTO.getNombre()).isPresent()) {
            throw new ValidationException("El nombre del producto ya está en uso.");
        }

        productoExistente.setNombre(productoDTO.getNombre());
        productoExistente.setDescripcion(productoDTO.getDescripcion());
        productoExistente.setPrecio(productoDTO.getPrecio());
        productoExistente.setStock(productoDTO.getStock());

        return productoRepository.save(productoExistente);
    }

    @Transactional
    public void eliminarProducto(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Producto no encontrado con el ID: " + id);
        }
        productoRepository.deleteById(id);
    }
}