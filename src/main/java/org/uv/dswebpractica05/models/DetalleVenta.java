package org.uv.dswebpractica05.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "detalle_venta")
public class DetalleVenta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleVenta;

    @ManyToOne
    @JoinColumn(name = "idVenta", nullable = false)
    @JsonBackReference
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "idProducto", nullable = false)
    private Producto producto;

    @Column(name = "precioProducto")
    private Double precioProducto;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "subtotal")
    private Double subtotal;

    public Long getIdDetalleVenta() {
        return idDetalleVenta;
    }

    public void setIdDetalleVenta(Long idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(Double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public void calcularSubtotal() {
        this.subtotal = this.precioProducto * this.cantidad;
    }
}
