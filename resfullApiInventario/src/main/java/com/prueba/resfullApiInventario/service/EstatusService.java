package com.prueba.resfullApiInventario.service;

import com.prueba.resfullApiInventario.entity.Estatus;
import com.prueba.resfullApiInventario.error.StatusAlreadyExistsException;

import java.util.List;

public interface EstatusService {
    List<Estatus> findAllStatus();
    Estatus saveStatus(Estatus estatus) throws StatusAlreadyExistsException;

}
