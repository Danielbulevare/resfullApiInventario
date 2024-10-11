package com.prueba.resfullApiInventario.projection.classbased;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDataDTO {
    Long id;
    String name;
    Long idStatus;
    String status;
}
