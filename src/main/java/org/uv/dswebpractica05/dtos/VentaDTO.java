package org.uv.dswebpractica05.dtos;

import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;

public class VentaDTO {

    @NotNull(message = "El ID del cliente es obligatorio.")
    private Long idCliente;

    @NotNull(message = "La fecha de venta es obligatoria.")
    private Date fechaVenta;

    private Double totalVenta;

    @NotNull(message = "La lista de detalles de venta no puede ser nula.")
    private List<DetalleVentaDTO> detallesVenta;

    public VentaDTO() {
    }

    public VentaDTO(Long idCliente, Date fechaVenta, Double totalVenta, List<DetalleVentaDTO> detallesVenta) {
        this.idCliente = idCliente;
        this.fechaVenta = fechaVenta;
        this.totalVenta = totalVenta;
        this.detallesVenta = detallesVenta;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(Double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public List<DetalleVentaDTO> getDetallesVenta() {
        return detallesVenta;
    }

    public void setDetallesVenta(List<DetalleVentaDTO> detallesVenta) {
        this.detallesVenta = detallesVenta;
    }
}