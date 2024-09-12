package com.prueba.resfullApiInventario.repository;

import com.prueba.resfullApiInventario.entity.TransaccionInventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransaccionInventarioRepository extends JpaRepository<TransaccionInventario, Long> {
}
