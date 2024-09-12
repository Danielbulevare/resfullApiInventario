package com.prueba.resfullApiInventario.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "tbl_roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol", nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private Long id;

    @NotBlank(message = "Por favor agrega un nombre al rol.")
    @Length(min = 1, max = 20)
    @Column(name = "nombre_rol", length = 20, nullable = false, unique = true)
    private String role;
}
