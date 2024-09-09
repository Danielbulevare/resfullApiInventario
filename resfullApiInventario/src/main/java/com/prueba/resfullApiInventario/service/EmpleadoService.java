package com.prueba.resfullApiInventario.service;

import com.prueba.resfullApiInventario.entity.Empleado;

import java.util.List;
import java.util.Optional;

public interface EmpleadoService {
    List<Empleado> findAllEmployees();
    Empleado saveEmployee(Empleado empleado);
    Empleado updateEmployee(Long id, Empleado empleado);
    void deleteEmployee(Long id);
}
