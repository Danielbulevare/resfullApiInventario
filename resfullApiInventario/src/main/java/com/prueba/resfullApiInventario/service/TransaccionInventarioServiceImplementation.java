package com.prueba.resfullApiInventario.service;

import com.prueba.resfullApiInventario.entity.TransaccionInventario;
import com.prueba.resfullApiInventario.error.InvalidDateException;
import com.prueba.resfullApiInventario.projection.classbased.TransactionInventoryDTO;
import com.prueba.resfullApiInventario.projection.interfacebased.closed.HistoricalInventoryTransactionClosedView;
import com.prueba.resfullApiInventario.repository.TransaccionInventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<HistoricalInventoryTransactionClosedView> findBy() {
        return transaccionInventarioRepository.findBy();
    }

    @Override
    public List<TransactionInventoryDTO> findProductsByMoveType(String tipoMovimiento) {
        List<Object[]> queryResult = transaccionInventarioRepository.findProductsByMoveType(tipoMovimiento);
        List<TransactionInventoryDTO> transactionInventoryDTOMapping = new ArrayList<>();

        for(Object[] item:queryResult){
            //Se usa Number para que el Object lo convierta a tipo Long e int, no se puede hacer un casting
            //directamente con (Long) ni (int), ya que marca error, por eso se usa Number
            transactionInventoryDTOMapping.add(new TransactionInventoryDTO(((Number)item[0]).longValue(),(String)item[1],(String)item[2],((Number)item[3]).intValue(),(Date) item[4],(Timestamp) item[5],(String)item[6]));
        }

        return transactionInventoryDTOMapping;
    }
}
