package com.prueba.resfullApiInventario.service;

import com.prueba.resfullApiInventario.entity.Rol;
import com.prueba.resfullApiInventario.error.RoleAlreadyExistsException;
import com.prueba.resfullApiInventario.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImplementation implements RolService{
    @Autowired
    RolRepository rolRepository;

    @Override
    public Rol saveRole(Rol rol) throws RoleAlreadyExistsException {
        try {
            return rolRepository.save(rol);
        }catch (DataIntegrityViolationException e){
            throw new RoleAlreadyExistsException("El rol ya está registrado.");
        }
    }
}
