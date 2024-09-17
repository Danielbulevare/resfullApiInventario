package com.prueba.resfullApiInventario.service;

import com.prueba.resfullApiInventario.entity.TransaccionInventario;
import com.prueba.resfullApiInventario.error.InvalidDateException;
import com.prueba.resfullApiInventario.repository.TransaccionInventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.format.DateTimeParseException;

@Service
public class TransaccionInventarioServiceImplementation implements TransaccionInventarioService{
    @Autowired
    TransaccionInventarioRepository transaccionInventarioRepository;

    @Override
    public TransaccionInventario saveInventoryTransaction(TransaccionInventario transaccionInventario) throws InvalidDateException {
        try {
            return transaccionInventarioRepository.save(transaccionInventario);
        }catch (Exception e){
            throw new InvalidDateException("Debes especificar una fecha válida.");
        }
    }
}
