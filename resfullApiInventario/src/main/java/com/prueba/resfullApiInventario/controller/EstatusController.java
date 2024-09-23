package com.prueba.resfullApiInventario.controller;

import com.prueba.resfullApiInventario.entity.Estatus;
import com.prueba.resfullApiInventario.error.StatusAlreadyExistsException;
import com.prueba.resfullApiInventario.service.EstatusService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/estatus")
public class EstatusController {
    @Autowired
    EstatusService estatusService;

    @GetMapping("/findAllStatus")
    public List<Estatus> findAllStatus(){
        return estatusService.findAllStatus();
    }

    @PostMapping("/saveStatus")
    public Estatus saveStatus(@Valid @RequestBody Estatus estatus) throws StatusAlreadyExistsException {
        return estatusService.saveStatus(estatus);
    }
}
