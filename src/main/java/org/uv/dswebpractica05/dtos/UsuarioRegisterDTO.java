package org.uv.dswebpractica05.dtos;

import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class UsuarioRegisterDTO {
    
    public UsuarioRegisterDTO(String username, String password, Set<String> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    @NotBlank(message = "El nombre de usuario es obligatorio.")
    @Size(min = 4, max = 20, message = "El nombre de usuario debe tener entre 4 y 20 caracteres.")
    private String username;

    @NotBlank(message = "La contraseña es obligatoria.")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres.")
    private String password;

    @NotNull(message = "Los roles no pueden ser nulos.")
    @Size(min = 1, message = "Debe tener al menos un rol asignado.")
    private Set<String> roles;

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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        if (roles != null && !roles.stream().allMatch(role -> role.matches("USUARIO|ADMIN"))) {
            throw new IllegalArgumentException("Roles inválidos.");
        }
        this.roles = roles;
    }

}
