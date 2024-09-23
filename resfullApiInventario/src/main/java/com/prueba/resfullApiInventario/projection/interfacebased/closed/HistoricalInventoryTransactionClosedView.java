package com.prueba.resfullApiInventario.projection.interfacebased.closed;

import com.prueba.resfullApiInventario.enumeration.Movimiento;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface HistoricalInventoryTransactionClosedView {
    ProductClosedView getProduct();
    Movimiento getMove();
    int getQuantity();
    LocalDate getDateWithoutTime();
    LocalDateTime getDateWithTime();
    EmployeeClosedView getEmployee();
}
