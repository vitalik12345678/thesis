package com.example.thesis.service;

import com.example.thesis.entity.EntityWithId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;

public interface CRUDService <ENTITY extends EntityWithId<ID>, ID > {

    ENTITY save(ENTITY entity);

    ENTITY delete(ID id);

    List<ENTITY> deleteAll(List<ENTITY> entities);

    ENTITY findById(ID id);

    ENTITY update(ENTITY entity);

    List<ENTITY> findAll();
}
