package com.prueba.resfullApiInventario.service;

import com.prueba.resfullApiInventario.entity.Rol;
import com.prueba.resfullApiInventario.error.RoleAlreadyExistsException;

public interface RolService {
    Rol saveRole(Rol rol) throws RoleAlreadyExistsException;
}
