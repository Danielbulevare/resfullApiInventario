package com.prueba.resfullApiInventario.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "tbl_empleados")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Para que el Id sea auto incremental
    private Long id;
    @NotBlank(message = "Por favor agregar un nombre")
    @Length(min = 10, max = 20) //Esta anotación modifica la longitud de la columna en la bd
    private String name;
    @NotBlank(message = "Por favor agregar un correo")
    private String mail;
    @NotBlank(message = "Por favor agregar una contraseña")
    private String password;
}
