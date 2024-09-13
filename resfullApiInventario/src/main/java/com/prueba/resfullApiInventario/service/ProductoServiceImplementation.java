package com.prueba.resfullApiInventario.service;

import com.prueba.resfullApiInventario.entity.Producto;
import com.prueba.resfullApiInventario.error.NameAlreadyExistsException;
import com.prueba.resfullApiInventario.error.ProductNotFoundException;
import com.prueba.resfullApiInventario.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class ProductoServiceImplementation implements ProductoService{
    @Autowired
    ProductoRepository productoRepository;

    @Override
    public Producto saveProduct(Producto producto) throws NameAlreadyExistsException {
        try {
            return productoRepository.save(producto);
        }catch (DataIntegrityViolationException e){
            throw new NameAlreadyExistsException("El producto ya esta registrado.");
        }
    }

    @Override
    public Producto findProductById(Long id) throws ProductNotFoundException{

        Optional<Producto> producto = productoRepository.findById(id);

        if(!producto.isPresent()){
            throw new ProductNotFoundException("El producto no existe.");
        }
        return producto.get();
    }

    @Override
    public Producto updateProduct(Long id, Producto producto) throws NameAlreadyExistsException, ProductNotFoundException{
        Optional<Producto> productoDB = productoRepository.findById(id);

        if(!productoDB.isPresent()){
           throw new ProductNotFoundException("El producto no existe.");
        }

        if(Objects.nonNull(producto.getName()) && !"".equalsIgnoreCase(producto.getName())){
            productoDB.get().setName(producto.getName());
        }

        try{
            return productoRepository.save(productoDB.get());
        } catch (DataIntegrityViolationException e) {
            throw new NameAlreadyExistsException("El producto ya esta registrado");
        }
    }
}
