package com.prueba.resfullApiInventario.service;

import com.prueba.resfullApiInventario.projection.classbased.ProductoWithQuantityDTO;
import com.prueba.resfullApiInventario.projection.classbased.ProductExistenceDTO;
import com.prueba.resfullApiInventario.entity.Producto;
import com.prueba.resfullApiInventario.error.NameAlreadyExistsException;
import com.prueba.resfullApiInventario.error.ProductNotFoundException;

import java.util.List;

public interface ProductoService {
    Producto saveProduct(Producto producto) throws NameAlreadyExistsException;
    Producto findProductById(Long id) throws ProductNotFoundException;
    Producto updateProduct(Long id, Producto producto) throws NameAlreadyExistsException, ProductNotFoundException;

    //View class based
    List<ProductoWithQuantityDTO> findProductsWithQuantityFilteredByStatus(Long idEstatus);
    ProductExistenceDTO findProductStock(Long id);
}
