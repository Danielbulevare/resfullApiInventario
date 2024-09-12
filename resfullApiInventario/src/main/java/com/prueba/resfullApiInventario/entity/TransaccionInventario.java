package com.prueba.resfullApiInventario.entity;

import com.prueba.resfullApiInventario.enumeration.Movimiento;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "Por favor agrega la cantidad.")
    @Min(value = 1, message = "La entrada o salida mínima es de una unidad.")
    @Max(value = 1000, message = "La entrada o salida máxima permitida es de mil unidades.")
    @Column(name = "cantidad", nullable = false)
    private int quantity;

    @NotBlank(message = "Por favor agrega el tipo de movimiento.")
    @Enumerated(EnumType.STRING)
    @Length(max = 7, message = "Elige ENTRADA o SALIDA")
    @Column(name = "movimiento", nullable = false, length = 7)
    private Movimiento move;

    @NotBlank(message = "Por favor específica la fecha sin hora.")
    @Column(name = "fecha_sin_hora", nullable = false)
    private LocalDate dateWithoutTime;

    @NotBlank(message = "Por favor especiica la fecha con hora")
    @Column(name = "fecha_con_hora", nullable = false)
    private LocalDateTime dateWithTime;
}
