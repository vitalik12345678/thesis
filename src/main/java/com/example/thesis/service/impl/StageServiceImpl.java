package com.example.thesis.service.impl;

import com.example.thesis.entity.Stage;
import com.example.thesis.repository.StageRepository;
import com.example.thesis.service.StageService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class StageServiceImpl extends CRUDServiceImpl<Stage,Long> implements StageService {

    private final StageRepository stageRepository;

    public StageServiceImpl (StageRepository stageRepository) {
        this.stageRepository = stageRepository;
    }

    @Override
    protected JpaRepository<Stage, Long> getRepository () {
        return stageRepository;
    }
}
