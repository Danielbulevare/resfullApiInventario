package com.prueba.resfullApiInventario.service;

import com.prueba.resfullApiInventario.projection.classbased.ProductDataDTO;
import com.prueba.resfullApiInventario.projection.classbased.ProductExistenceDTO;
import com.prueba.resfullApiInventario.projection.classbased.ProductoWithQuantityDTO;
import com.prueba.resfullApiInventario.entity.Producto;
import com.prueba.resfullApiInventario.error.NameAlreadyExistsException;
import com.prueba.resfullApiInventario.error.ProductNotFoundException;
import com.prueba.resfullApiInventario.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    public ProductDataDTO findProductById(Long id) throws ProductNotFoundException{

        Optional<Producto> producto = productoRepository.findById(id);

        if(!producto.isPresent()){
            throw new ProductNotFoundException("El producto no existe.");
        }

        ProductDataDTO productDataDTO = new ProductDataDTO(producto.get().getId(),
                producto.get().getName(),
                producto.get().getStatus().getId(),
                producto.get().getStatus().getStatus());

        return productDataDTO;
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

        if (Objects.nonNull(producto.getStatus())){
            productoDB.get().setStatus(producto.getStatus());
        }

        try{
            return productoRepository.save(productoDB.get());
        } catch (DataIntegrityViolationException e) {
            throw new NameAlreadyExistsException("El producto ya esta registrado");
        }
    }

    @Override
    public List<ProductoWithQuantityDTO> findProductsWithQuantityFilteredByStatus(Long idEstatus) {
        List<Object[]> queryResult = productoRepository.findProductsWithQuantityFilteredByStatus(idEstatus);
        List<ProductoWithQuantityDTO> productDTOMapping = new ArrayList<>();

        for (Object[] item: queryResult){
            //Se usa Number para que el Object lo convierta a tipo Long e int, no se puede hacer un casting
            //directamente con (Long) ni (int), ya que marca error, por eso se usa Number
            productDTOMapping.add(new ProductoWithQuantityDTO(((Number)item[0]).longValue(), (String)item[1], ((Number)item[2]).intValue(), (String)item[3]));
        }
        return productDTOMapping;
    }

    @Override
    public ProductExistenceDTO findProductStock(Long id) {
        List<Object[]> product = productoRepository.findProductStock(id);

        //Mapea el objeto Object para mepearlo con la clase ProductExistenceDTO
        ProductExistenceDTO productExistence = new ProductExistenceDTO((String) product.get(0)[0], ((Number) product.get(0)[1]).intValue());

        return productExistence;
    }
}
