package com.prueba.resfullApiInventario.projection.classbased;

import com.prueba.resfullApiInventario.entity.Estatus;
import com.prueba.resfullApiInventario.entity.Rol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDataDTO {
    Long id;
    String name;
    Long idStatus;
    String status;
    Long idRole;
    String role;
}
