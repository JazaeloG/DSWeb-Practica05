package org.uv.dswebpractica05.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.uv.dswebpractica05.models.Rol;
import org.uv.dswebpractica05.services.RolService;
import org.uv.dswebpractica05.utils.ErrorResponse;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/admin/roles")
public class RolController {
    @Autowired
    private RolService rolService;
    
    @PostMapping("/registrar")
    public ResponseEntity<?> registrarRol(@RequestBody Rol nuevoRol) {
        try {
            Rol rolRegistrado = rolService.registrarRol(nuevoRol.getNombre());
            return new ResponseEntity<>(rolRegistrado, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
