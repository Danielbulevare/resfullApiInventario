package com.prueba.resfullApiInventario.service;

import com.prueba.resfullApiInventario.entity.Producto;
import com.prueba.resfullApiInventario.error.NameAlreadyExistsException;
import com.prueba.resfullApiInventario.error.ProductNotFoundException;

public interface ProductoService {
    Producto saveProduct(Producto producto) throws NameAlreadyExistsException;
    Producto findProductById(Long id) throws ProductNotFoundException;
    Producto updateProduct(Long id, Producto producto) throws NameAlreadyExistsException, ProductNotFoundException;
}
