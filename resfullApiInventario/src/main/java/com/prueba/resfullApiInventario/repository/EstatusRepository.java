package com.prueba.resfullApiInventario.repository;

import com.prueba.resfullApiInventario.entity.Estatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstatusRepository extends JpaRepository<Estatus, Long> {
}
