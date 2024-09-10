package com.prueba.resfullApiInventario.service;

import com.prueba.resfullApiInventario.entity.Empleado;
import com.prueba.resfullApiInventario.error.EmployeeNotFoundException;

import java.util.List;
import java.util.Optional;

public interface EmpleadoService {
    List<Empleado> findAllEmployees();
    Empleado saveEmployee(Empleado empleado);
    Empleado updateEmployee(Long id, Empleado empleado);
    void deleteEmployee(Long id);
    Optional<Empleado> findEmployeeByNameWithJPQL(String name);
    Optional<Empleado> findByName(String name);
    Optional<Empleado> findByNameIgnoreCase(String name);
    Empleado findEmployeeById(Long id) throws EmployeeNotFoundException;
}
