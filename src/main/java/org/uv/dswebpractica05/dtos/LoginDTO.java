package org.uv.dswebpractica05.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginDTO {
    
    public LoginDTO(String username, String password){
        this.username = username;
        this.password = password;
    }
    
    @NotBlank(message = "El nombre de usuario es obligatorio.")
    @Size(min = 4, max = 20, message = "El nombre de usuario debe tener entre 4 y 20 caracteres.")
    private String username;
    
    @NotBlank(message = "La contraseña es obligatoria.")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres.")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
