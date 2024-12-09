package org.uv.dswebpractica05.controllers;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.uv.dswebpractica05.dtos.ProductoDTO;
import org.uv.dswebpractica05.models.Producto;
import org.uv.dswebpractica05.services.ProductoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody @Valid ProductoDTO productoDTO) {
        return new ResponseEntity<>(productoService.crearProducto(productoDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos() {
        return ResponseEntity.ok(productoService.listarProductos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarProductoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.buscarProductoPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(
            @PathVariable Long id,
            @RequestBody @Valid ProductoDTO productoDTO) {
        return ResponseEntity.ok(productoService.actualizarProducto(id, productoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}