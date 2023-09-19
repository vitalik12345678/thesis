package com.example.thesis.service.impl;

import com.example.thesis.entity.EntityWithId;
import com.example.thesis.exception.NotExistObjectException;
import com.example.thesis.exception.NullObjectException;
import com.example.thesis.service.CRUDService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

public abstract class CRUDServiceImpl<ENTITY extends EntityWithId<ID>, ID> implements CRUDService<ENTITY,ID> {

    protected abstract JpaRepository<ENTITY,ID> getRepository();

    @Override
    @Transactional
    public ENTITY save (ENTITY entity) {
        if (Objects.isNull(entity)) throw new NullObjectException();
        return getRepository().save(entity);
    }

    @Override
    @Transactional
    public ENTITY delete (ID id) {
        ENTITY entity = findById(id);
        getRepository().delete(entity);
        return entity;
    }

    @Override
    @Transactional(readOnly = true)
    public ENTITY findById (ID id) {
        return getRepository().findById(id).orElseThrow(NotExistObjectException::new);
    }

    @Override
    @Transactional
    public ENTITY update (ENTITY entity) {
        if (Objects.isNull(entity)) throw new NullObjectException();
        ENTITY oldEntity = findById(entity.getId());
        return getRepository().save(entity);
    }

    @Override
    public List<ENTITY> findAll () {
        return getRepository().findAll();
    }
}
