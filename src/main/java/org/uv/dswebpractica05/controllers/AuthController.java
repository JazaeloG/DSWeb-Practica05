package org.uv.dswebpractica05.controllers;

import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.uv.dswebpractica05.dtos.LoginDTO;
import org.uv.dswebpractica05.dtos.UsuarioRegisterDTO;
import org.uv.dswebpractica05.models.Usuario;
import org.uv.dswebpractica05.services.AuthService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    
    @PostMapping("/registrar")
    public ResponseEntity<?> registro(@Valid @RequestBody UsuarioRegisterDTO usuarioDTO) {
        try {
            Usuario usuario = authService.registerUsuario(usuarioDTO);
            return new ResponseEntity<>(usuario, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO loginDTO) {
        try {
            String token = authService.loginUsuario(loginDTO);

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("message", "Login exitoso");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

}
