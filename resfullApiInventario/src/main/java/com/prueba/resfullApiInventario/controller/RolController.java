package com.prueba.resfullApiInventario.controller;

import com.prueba.resfullApiInventario.entity.Rol;
import com.prueba.resfullApiInventario.error.RoleAlreadyExistsException;
import com.prueba.resfullApiInventario.service.RolService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/roles")
public class RolController {
    @Autowired
    RolService rolService;

    @PostMapping("/saveRole")
    public Rol saveRole(@Valid @RequestBody Rol rol) throws RoleAlreadyExistsException {
        return rolService.saveRole(rol);
    }
}
