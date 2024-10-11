package com.prueba.resfullApiInventario.repository;

import com.prueba.resfullApiInventario.entity.Empleado;
import com.prueba.resfullApiInventario.projection.interfacebased.closed.EmployeeDataClosedView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    //Consulta JPQL
    @Query("SELECT e FROM Empleado e WHERE e.name = :name")
    Optional<EmployeeDataClosedView> findEmployeeByNameWithJPQL(String name);

    List<EmployeeDataClosedView> findBy();
    //Consulta con Inversion de Control
    Optional<EmployeeDataClosedView> findByName(String name);
    Optional<EmployeeDataClosedView> findByNameIgnoreCase(String name);
    Optional<EmployeeDataClosedView> findByMailAndPassword(String mail, String password);

}
