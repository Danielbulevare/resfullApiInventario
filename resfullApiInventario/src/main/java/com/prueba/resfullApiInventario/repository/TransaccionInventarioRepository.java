package com.prueba.resfullApiInventario.repository;

import com.prueba.resfullApiInventario.entity.TransaccionInventario;
import com.prueba.resfullApiInventario.projection.interfacebased.closed.HistoricalInventoryTransactionClosedView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransaccionInventarioRepository extends JpaRepository<TransaccionInventario, Long> {
    List<HistoricalInventoryTransactionClosedView> findBy();
}
