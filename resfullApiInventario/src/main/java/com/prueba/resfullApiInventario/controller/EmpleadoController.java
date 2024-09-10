package com.prueba.resfullApiInventario.controller;

import com.prueba.resfullApiInventario.entity.Empleado;
import com.prueba.resfullApiInventario.error.EmployeeNotFoundException;
import com.prueba.resfullApiInventario.service.EmpleadoService;
import com.prueba.resfullApiInventario.service.EmpleadoServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmpleadoController {
    @Autowired
    EmpleadoService empleadoService;

    @GetMapping("/findAllEmployees")
    public List<Empleado> findAllEmployees(){
        return empleadoService.findAllEmployees();
    }

    @GetMapping("/findEmployeeByNameWithJPQL/{name}")
    public Optional<Empleado> findEmployeeByNameWithJPQL(@PathVariable String name){
        return empleadoService.findEmployeeByNameWithJPQL(name);
    }

    @GetMapping("/findEmployeeById/{id}")
    public Empleado findById(@PathVariable Long id) throws EmployeeNotFoundException {
        return empleadoService.findEmployeeById(id);
    }

    @GetMapping("/findByName/{name}")
    public Optional<Empleado> findByName(@PathVariable String name){
        return empleadoService.findByName(name);
    }

    @GetMapping("/findByNameIgnoreCase/{name}")
    public Optional<Empleado> findByNameIgnoreCase(@PathVariable String name){
        return empleadoService.findByNameIgnoreCase(name);
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
