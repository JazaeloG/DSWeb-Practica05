package org.uv.dswebpractica05.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class DetalleVentaDTO {
    @NotNull(message = "La venta es obligatoria.")
    private Long idVenta;
    
    @NotNull(message = "El producto es obligatorio.")
    private Long idProducto;
    
    @NotNull(message = "La cantidad es obligatoria.")
    @Positive(message = "La cantidad debe ser mayor a cero.")
    private Integer cantidad;

    public DetalleVentaDTO(Long idVenta, Long idProducto, Integer cantidad) {
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}