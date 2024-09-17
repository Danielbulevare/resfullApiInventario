package com.prueba.resfullApiInventario.controller;

import com.prueba.resfullApiInventario.entity.TransaccionInventario;
import com.prueba.resfullApiInventario.error.InvalidDateException;
import com.prueba.resfullApiInventario.service.TransaccionInventarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransaccionInventarioController {
    @Autowired
    TransaccionInventarioService transaccionInventarioService;

    @PostMapping("/transaccionInventarioRepository")
    public TransaccionInventario transaccionInventarioRepository(@Valid @RequestBody TransaccionInventario transaccionInventario) throws InvalidDateException {
        return transaccionInventarioService.saveInventoryTransaction(transaccionInventario);
    }
}
