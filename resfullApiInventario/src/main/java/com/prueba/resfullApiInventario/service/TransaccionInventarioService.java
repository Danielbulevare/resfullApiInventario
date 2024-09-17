package com.prueba.resfullApiInventario.service;

import com.prueba.resfullApiInventario.entity.TransaccionInventario;
import com.prueba.resfullApiInventario.error.InvalidDateException;

public interface TransaccionInventarioService {
    TransaccionInventario saveInventoryTransaction(TransaccionInventario transaccionInventario) throws InvalidDateException;
}
