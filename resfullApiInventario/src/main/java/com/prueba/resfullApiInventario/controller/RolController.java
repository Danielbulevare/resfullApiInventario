package com.prueba.resfullApiInventario.controller;

import com.prueba.resfullApiInventario.entity.Rol;
import com.prueba.resfullApiInventario.error.RoleAlreadyExistsException;
import com.prueba.resfullApiInventario.service.RolService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/roles")
@CrossOrigin(origins = "http://localhost:4200")
public class RolController {
    @Autowired
    RolService rolService;

    @PostMapping("/saveRole")
    public Rol saveRole(@Valid @RequestBody Rol rol) throws RoleAlreadyExistsException {
        return rolService.saveRole(rol);
    }
}
