package com.prueba.resfullApiInventario.repository;

import com.prueba.resfullApiInventario.entity.TransaccionInventario;
import com.prueba.resfullApiInventario.projection.interfacebased.closed.HistoricalInventoryTransactionClosedView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransaccionInventarioRepository extends JpaRepository<TransaccionInventario, Long> {
    List<HistoricalInventoryTransactionClosedView> findBy();

    @Query(value = "select p.id_producto, p.nombre_producto, t.movimiento, t.cantidad, t.fecha_sin_hora, t.fecha_con_hora, e.nombre_empleado from tbl_transacciones_inventario t" +
            " inner join tbl_productos p on p.id_producto = t.fk_id_producto" +
            " inner join tbl_empleados e on e.id_empleado =t.fk_id_realizado_por_empledo" +
            " where t.movimiento = :tipoMovimiento", nativeQuery = true)
    List<Object[]> findProductsByMoveType(String tipoMovimiento);
}
