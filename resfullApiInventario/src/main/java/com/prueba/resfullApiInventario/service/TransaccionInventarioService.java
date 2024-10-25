package com.prueba.resfullApiInventario.service;

import com.prueba.resfullApiInventario.entity.TransaccionInventario;
import com.prueba.resfullApiInventario.error.InvalidDateException;
import com.prueba.resfullApiInventario.projection.classbased.TransactionInventoryDTO;
import com.prueba.resfullApiInventario.projection.interfacebased.closed.HistoricalInventoryTransactionClosedView;

import java.util.List;

public interface TransaccionInventarioService {
    TransaccionInventario saveInventoryTransaction(TransaccionInventario transaccionInventario) throws InvalidDateException;
    List<HistoricalInventoryTransactionClosedView> findBy();
    List<TransactionInventoryDTO> findProductsByMoveType(String tipoMovimiento);
}
