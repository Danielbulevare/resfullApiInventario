package com.prueba.resfullApiInventario.controller;

import com.prueba.resfullApiInventario.entity.TransaccionInventario;
import com.prueba.resfullApiInventario.error.InvalidDateException;
import com.prueba.resfullApiInventario.projection.interfacebased.closed.HistoricalInventoryTransactionClosedView;
import com.prueba.resfullApiInventario.service.TransaccionInventarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/transacciones-inventario")
public class TransaccionInventarioController {
    @Autowired
    TransaccionInventarioService transaccionInventarioService;

    @GetMapping("/findAllTransactions")
    public List<HistoricalInventoryTransactionClosedView> findAllTransactions(){
        return transaccionInventarioService.findBy();
    }

    @PostMapping("/transaccionInventarioRepository")
    public TransaccionInventario transaccionInventarioRepository(@Valid @RequestBody TransaccionInventario transaccionInventario) throws InvalidDateException {
        return transaccionInventarioService.saveInventoryTransaction(transaccionInventario);
    }
}
