package com.prueba.resfullApiInventario.controller;

import com.prueba.resfullApiInventario.entity.Producto;
import com.prueba.resfullApiInventario.error.NameAlreadyExistsException;
import com.prueba.resfullApiInventario.error.ProductNotFoundException;
import com.prueba.resfullApiInventario.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductoController {
    @Autowired
    ProductoService productoService;

    @GetMapping("/findProductById/{id}")
    public Producto findProductById(@PathVariable Long id) throws ProductNotFoundException {
        return productoService.findProductById(id);
    }

    @PostMapping("/saveProduct")
    public Producto saveProduct(@Valid @RequestBody Producto producto) throws NameAlreadyExistsException {
        return productoService.saveProduct(producto);
    }

    @PutMapping("/updateProduct/{id}")
    public Producto updateProduct(@PathVariable Long id,@Valid @RequestBody Producto producto) throws NameAlreadyExistsException, ProductNotFoundException{
        return productoService.updateProduct(id, producto);
    }
}
