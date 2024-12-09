package org.uv.dswebpractica05.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductoDTO {
    public ProductoDTO(String nombre, String descripcion, Double precio, Integer stock) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }

    @NotBlank(message = "El nombre del producto es obligatorio.")
    @Size(min = 2, max = 50, message = "El nombre del producto debe tener entre 2 y 50 caracteres.")
    private String nombre;

    @NotBlank(message = "La descripción del producto es obligatoria.")
    @Size(min = 10, max = 200, message = "La descripción debe tener entre 10 y 200 caracteres.")
    private String descripcion;

    @NotNull(message = "El precio del producto es obligatorio.")
    private Double precio;

    @NotNull(message = "El stock del producto es obligatorio.")
    private Integer stock;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}