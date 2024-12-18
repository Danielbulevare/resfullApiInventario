package com.prueba.resfullApiInventario.controller;

import com.prueba.resfullApiInventario.controller.models.AuthResponse;
import com.prueba.resfullApiInventario.controller.models.AuthenticationRequest;
import com.prueba.resfullApiInventario.entity.Empleado;
import com.prueba.resfullApiInventario.error.EmailAlreadyExistsException;
import com.prueba.resfullApiInventario.error.EmployeeNotFoundException;
import com.prueba.resfullApiInventario.projection.classbased.EmployeeDataDTO;
import com.prueba.resfullApiInventario.projection.interfacebased.closed.EmployeeDataClosedView;
import com.prueba.resfullApiInventario.service.EmpleadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/empleados")
@CrossOrigin(origins = "http://localhost:4200")
public class EmpleadoController {
    @Autowired
    EmpleadoService empleadoService;

    @GetMapping("/findAllEmployees")
    public List<EmployeeDataClosedView> findAllEmployees(){
        return empleadoService.findBy();
    }

    @GetMapping("/findEmployeeByNameWithJPQL/{name}")
    public Optional<EmployeeDataClosedView> findEmployeeByNameWithJPQL(@PathVariable String name){
        return empleadoService.findEmployeeByNameWithJPQL(name);
    }

    @GetMapping("/findEmployeeById/{id}")
    public EmployeeDataDTO findById(@PathVariable Long id) throws EmployeeNotFoundException {
        return empleadoService.findEmployeeById(id);
    }

    @GetMapping("/findByName/{name}")
    public Optional<EmployeeDataClosedView> findByName(@PathVariable String name){
        return empleadoService.findByName(name);
    }

    @GetMapping("/findByNameIgnoreCase/{name}")
    public Optional<EmployeeDataClosedView> findByNameIgnoreCase(@PathVariable String name){
        return empleadoService.findByNameIgnoreCase(name);
    }

    @GetMapping("/findByMailAndPassword/{mail}/{password}")
    public Optional<EmployeeDataClosedView> findByEmailAndPassword(@PathVariable String mail, @PathVariable String password) throws EmployeeNotFoundException{
        return empleadoService.findByMailAndPassword(mail, password);
    }

    @PostMapping("/saveEmployee")
    public Empleado saveEmployee(@Valid @RequestBody Empleado empleado) throws EmailAlreadyExistsException {
        return empleadoService.saveEmployee(empleado);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(empleadoService.authenticate(request));
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
