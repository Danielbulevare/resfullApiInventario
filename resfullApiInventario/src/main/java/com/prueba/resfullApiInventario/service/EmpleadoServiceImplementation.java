package com.prueba.resfullApiInventario.service;

import com.prueba.resfullApiInventario.entity.Empleado;
import com.prueba.resfullApiInventario.error.EmployeeNotFoundException;
import com.prueba.resfullApiInventario.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmpleadoServiceImplementation implements EmpleadoService{
    //Inject the repository
    @Autowired
    EmpleadoRepository empleadoRepository;

    @Override
    public List<Empleado> findAllEmployees() {
        return empleadoRepository.findAll();
    }

    @Override
    public Empleado saveEmployee(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    @Override
    public Empleado updateEmployee(Long id, Empleado empleado) {
        Empleado empleadoDb = empleadoRepository.findById(id).get();

        if (Objects.nonNull(empleado.getMail()) && !"".equalsIgnoreCase(empleado.getMail())){
            empleadoDb.setMail(empleado.getMail());
        }

        if (Objects.nonNull(empleado.getName()) && !"".equalsIgnoreCase(empleado.getName())){
            empleadoDb.setName(empleado.getName());
        }

        if (Objects.nonNull(empleado.getPassword()) && !"".equalsIgnoreCase(empleado.getPassword())){
            empleadoDb.setPassword(empleado.getPassword());
        }

        return empleadoRepository.save(empleadoDb);
    }

    @Override
    public void deleteEmployee(Long id) {
        empleadoRepository.deleteById(id);
    }

    @Override
    public Optional<Empleado> findEmployeeByNameWithJPQL(String name) {
        return empleadoRepository.findEmployeeByNameWithJPQL(name);
    }

    @Override
    public Optional<Empleado> findByName(String name) {
        return empleadoRepository.findByName(name);
    }

    @Override
    public Optional<Empleado> findByNameIgnoreCase(String name) {
        return empleadoRepository.findByNameIgnoreCase(name);
    }

    @Override
    public Empleado findEmployeeById(Long id) throws EmployeeNotFoundException {
        Optional<Empleado> empleado = empleadoRepository.findById(id);

        //Verifica si devuelve un valor nulo
        if (!empleado.isPresent()){
            throw new EmployeeNotFoundException("El empleado no existe");
        }
        return empleado.get();
    }
}
