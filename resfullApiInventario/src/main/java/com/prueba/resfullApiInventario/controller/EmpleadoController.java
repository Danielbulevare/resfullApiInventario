package com.prueba.resfullApiInventario.controller;

import com.prueba.resfullApiInventario.entity.Empleado;
import com.prueba.resfullApiInventario.service.EmpleadoService;
import com.prueba.resfullApiInventario.service.EmpleadoServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmpleadoController {
    @Autowired
    EmpleadoService empleadoService;

    @GetMapping("/findAllEmployees")
    public List<Empleado> findAllEmployees(){
        return empleadoService.findAllEmployees();
    }

    @PostMapping("/saveEmployee")
    public Empleado saveEmployee(@RequestBody Empleado empleado){
        return empleadoService.saveEmployee(empleado);
    }

    @PutMapping("/updateEmployee/{id}")
    public Empleado updateEmployee(@PathVariable Long id, @RequestBody Empleado empleado){
        return empleadoService.updateEmployee(id, empleado);
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable Long id){
        empleadoService.deleteEmployee(id);
        return "Su registro ha sido eliminado";
    }
}
