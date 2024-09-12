package com.prueba.resfullApiInventario.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
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
    @Column(name = "id_empleado", nullable = false, columnDefinition = "BIGINT UNSIGNED")
    private Long id;

    @NotBlank(message = "Por favor agregar un nombre al empleado.")
    @Length(min = 1, max = 20) //Esta anotación modifica la longitud de la columna en la bd
    @Column(name = "nombre_empleado", nullable = false, length = 20)
    private String name;

    @NotBlank(message = "Por favor agregar un correo.")
    @Email(message = "El correo debe tener un formato válido.")
    @Length(max = 50)
    @Column(name = "correo_electronico", unique = true, nullable = false, length = 50)
    private String mail;

    @NotBlank(message = "Por favor agregar una contraseña.")
    @Length(min = 8, max = 50)
    @Column(name = "contrasenia", nullable = false, length = 50)
    private String password;
}
