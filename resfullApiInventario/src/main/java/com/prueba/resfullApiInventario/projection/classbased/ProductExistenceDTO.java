package com.prueba.resfullApiInventario.projection.classbased;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductExistenceDTO {
    private String nameProduct;
    private int existence;
}