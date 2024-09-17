package com.prueba.resfullApiInventario.entity;

import com.prueba.resfullApiInventario.enumeration.Movimiento;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_transacciones_inventario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransaccionInventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaccion_inventario", nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private Long id;

    @Min(value = 1, message = "La entrada o salida mínima es de una unidad.")
    @Max(value = 1000, message = "La entrada o salida máxima permitida es de mil unidades.")
    @Column(name = "cantidad", nullable = false)
    private int quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "movimiento", nullable = false, length = 7)
    private Movimiento move;

    @Column(name = "fecha_sin_hora", nullable = false)
    private LocalDate dateWithoutTime;

    @Column(name = "fecha_con_hora", nullable = false)
    private LocalDateTime dateWithTime;
}
