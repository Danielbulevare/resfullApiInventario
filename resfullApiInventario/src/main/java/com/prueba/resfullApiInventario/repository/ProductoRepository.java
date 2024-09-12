package com.prueba.resfullApiInventario.repository;

import com.prueba.resfullApiInventario.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
