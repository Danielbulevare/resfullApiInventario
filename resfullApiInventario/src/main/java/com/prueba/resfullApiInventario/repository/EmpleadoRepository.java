package com.prueba.resfullApiInventario.repository;

import com.prueba.resfullApiInventario.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
}
