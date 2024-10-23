package com.prueba.resfullApiInventario.controller;

import com.prueba.resfullApiInventario.projection.classbased.ProductDataDTO;
import com.prueba.resfullApiInventario.projection.classbased.ProductoWithQuantityDTO;
import com.prueba.resfullApiInventario.projection.classbased.ProductExistenceDTO;
import com.prueba.resfullApiInventario.entity.Producto;
import com.prueba.resfullApiInventario.error.NameAlreadyExistsException;
import com.prueba.resfullApiInventario.error.ProductNotFoundException;
import com.prueba.resfullApiInventario.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/productos")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoController {
    @Autowired
    ProductoService productoService;

    @GetMapping("/findProductById/{id}")
    public ProductDataDTO findProductById(@PathVariable Long id) throws ProductNotFoundException {
        return productoService.findProductById(id);
    }

    @GetMapping("/findProductsWithQuantityFilteredByStatus/{idEstatus}")
    public List<ProductoWithQuantityDTO> findProductsWithQuantityFilteredByStatus(@PathVariable Long idEstatus){
        return productoService.findProductsWithQuantityFilteredByStatus(idEstatus);
    }

    @GetMapping("/findProductStock/{id}")
    public ProductExistenceDTO findProductStock(@PathVariable Long id) throws ProductNotFoundException {
        return productoService.findProductStock(id);
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
