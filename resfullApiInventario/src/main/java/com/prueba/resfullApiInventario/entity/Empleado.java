package com.prueba.resfullApiInventario.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tbl_empleados")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Empleado implements UserDetails {
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
    @Length(min = 8, max = 200)
    @Column(name = "contrasenia", nullable = false, length = 200)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_id_estatus", referencedColumnName = "id_estatus", nullable = false)
    private Estatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_id_rol", referencedColumnName = "id_rol", nullable = false)
    private Rol role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //Este método regresa una colección de los roles que tiene el empleado

        //Regresa una lista del nombre del rol que tiene el empleado
        return List.of(new SimpleGrantedAuthority(role.getRole()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        /*
        Este método regresa el nombre del empleado que en ese casi es el mail. Recuerda que, para
        este caso utilizaré el mail como el usuario de nuestra aplicación, pero podemos utilizar
        cualquier otro atributo.
         */
        return mail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
