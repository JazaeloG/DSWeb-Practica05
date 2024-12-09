package org.uv.dswebpractica05.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ClienteRegisterDTO {
    public ClienteRegisterDTO(String nombre, String direccion, String correo){
        this.nombre = nombre;
        this.direccion = direccion;
        this.correo = correo;
    }
    
    @NotBlank(message = "El nombre del cliente es obligatorio.")
    @Size(min = 4, max = 20, message = "El nombre del cliente debe tener entre 4 y 20 caracteres.")
    private String nombre;
    
    @NotBlank(message = "La direccion es obligatoria.")
    @Size(min = 4, max = 20, message = "El nombre de usuario debe tener entre 4 y 50 caracteres.")
    private String direccion;
    
    @NotBlank(message = "El correo es obligatorio.")
    @Email
    private String correo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
}
