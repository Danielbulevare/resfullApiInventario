package com.prueba.resfullApiInventario.repository;

import com.prueba.resfullApiInventario.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    //Consulta JPQL
    @Query("SELECT e FROM Empleado e WHERE e.name = :name")
    Optional<Empleado> findEmployeeByNameWithJPQL(String name);

    //Consulta con Inversion de Control
    Optional<Empleado> findByName(String name);
    Optional<Empleado> findByNameIgnoreCase(String name);
    Optional<Empleado> findByMailAndPassword(String mail, String password);
}
