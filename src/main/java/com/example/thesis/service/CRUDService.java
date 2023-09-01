package com.example.thesis.service;

import com.example.thesis.entity.EntityWithId;
import jakarta.persistence.Id;

public interface CRUDService <ENTITY extends EntityWithId<ID>, ID > {

    ENTITY save(ENTITY entity);

    ENTITY delete(ID id);

    ENTITY findById(ID id);

    ENTITY update(ENTITY entity);

}
