package com.prueba.resfullApiInventario.service;

import com.prueba.resfullApiInventario.controller.models.AuthResponse;
import com.prueba.resfullApiInventario.controller.models.AuthenticationRequest;
import com.prueba.resfullApiInventario.entity.Empleado;
import com.prueba.resfullApiInventario.error.EmailAlreadyExistsException;
import com.prueba.resfullApiInventario.error.EmployeeNotFoundException;
import com.prueba.resfullApiInventario.projection.classbased.EmployeeDataDTO;
import com.prueba.resfullApiInventario.projection.interfacebased.closed.EmployeeDataClosedView;

import java.util.List;
import java.util.Optional;

public interface EmpleadoService {
    List<EmployeeDataClosedView> findBy();
    Empleado saveEmployee(Empleado empleado) throws EmailAlreadyExistsException;
    Empleado updateEmployee(Long id, Empleado empleado);
    void deleteEmployee(Long id);
    Optional<EmployeeDataClosedView> findEmployeeByNameWithJPQL(String name);
    Optional<EmployeeDataClosedView> findByName(String name);
    Optional<EmployeeDataClosedView> findByNameIgnoreCase(String name);
    EmployeeDataDTO findEmployeeById(Long id) throws EmployeeNotFoundException;
    Optional<EmployeeDataClosedView> findByMailAndPassword(String mail, String password) throws EmployeeNotFoundException;
    AuthResponse authenticate(AuthenticationRequest request);
}
