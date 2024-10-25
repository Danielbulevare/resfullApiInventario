package com.prueba.resfullApiInventario.projection.classbased;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionInventoryDTO {
    private Long idProduct;
    private String nameProduct;
    private String move;
    private int quantity;
    private Date dateWithoutTime;
    private Timestamp dateWithTime;
    private String employeeName;
}
