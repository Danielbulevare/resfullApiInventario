package com.prueba.resfullApiInventario.repository;

import com.prueba.resfullApiInventario.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    @Query(value = "SELECT p.id_producto, p.nombre_producto, SUM(ti.cantidad) AS existencia, e.nombre_estatus FROM tbl_productos p" +
            " LEFT JOIN tbl_transacciones_inventario ti ON ti.fk_id_producto = p.id_producto" +
            " LEFT JOIN tbl_estatus e ON e.id_estatus = p.fk_id_estatus" +
            " WHERE p.fk_id_estatus = :idEstatus" +
            " GROUP BY p.id_producto" +
            " HAVING existencia > 0", nativeQuery = true)
    List<Object[]> findProductsWithQuantityFilteredByStatus(Long idEstatus);

    //Este metodo solo va a regresar un unico objeto, pero se tuvo que poner que devolviera
    //un List<>, ya que si se pone Object u Object[] al hacer pruebas salia un error porque
    //no podia hacer una conversion en la implementacion de este metodo, y solo se pudo hacer la conversion
    //devolviendo un List<>
    @Query(value = "SELECT p.nombre_producto, SUM(ti.cantidad) AS existencia from tbl_productos p" +
            " INNER JOIN tbl_transacciones_inventario ti ON ti.fk_id_producto = p.id_producto" +
            " WHERE p.id_producto = :id" +
            " GROUP BY p.id_producto", nativeQuery = true)
    List<Object[]> findProductStock(Long id);
}
