package com.prueba.resfullApiInventario.service;

import com.prueba.resfullApiInventario.entity.Estatus;
import com.prueba.resfullApiInventario.error.StatusAlreadyExistsException;
import com.prueba.resfullApiInventario.repository.EstatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstatusServiceImplementation implements EstatusService{
    @Autowired
    EstatusRepository estatusRepository;

    @Override
    public List<Estatus> findAllStatus() {
        return estatusRepository.findAll();
    }

    @Override
    public Estatus saveStatus(Estatus estatus) throws StatusAlreadyExistsException {
        try {
            return estatusRepository.save(estatus);
        }catch (DataIntegrityViolationException e){
            throw new StatusAlreadyExistsException("El estatus ya esta registrado.");
        }
    }
}
