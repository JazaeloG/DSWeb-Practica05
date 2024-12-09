package org.uv.dswebpractica05.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.uv.dswebpractica05.dtos.VentaDTO;
import org.uv.dswebpractica05.models.Venta;
import org.uv.dswebpractica05.services.EmailService;
import org.uv.dswebpractica05.services.VentaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;
    
    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<Venta> crearVenta(@RequestBody VentaDTO ventaDTO) {
        Venta ventaCreada = ventaService.crearVenta(ventaDTO);
        return new ResponseEntity<>(ventaCreada, HttpStatus.CREATED);
    }
    
    @PostMapping("/enviar-reporte/{idVenta}")
    public ResponseEntity<String> enviarReportePorIdVenta(@PathVariable Long idVenta) {
        try {
            System.out.println("Recibiendo ID de venta: " + idVenta);

            Venta venta = ventaService.obtenerVentaPorId(idVenta);
            System.out.println("Venta obtenida: " + venta);

            if (venta == null) {
                return new ResponseEntity<>("Venta no encontrada", HttpStatus.NOT_FOUND);
            }

            String correoCliente = venta.getCliente().getCorreo();
            System.out.println("Correo del cliente: " + correoCliente);

            emailService.enviarReportePorIdVenta(correoCliente, idVenta);

            return new ResponseEntity<>("Reporte enviado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error al enviar el reporte: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Venta>> obtenerVentas() {
        List<Venta> ventas = ventaService.obtenerVentas();
        return new ResponseEntity<>(ventas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> obtenerVentaPorId(@PathVariable Long id) {
        Venta venta = ventaService.obtenerVentaPorId(id);
        return new ResponseEntity<>(venta, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable Long id) {
        ventaService.eliminarVenta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}