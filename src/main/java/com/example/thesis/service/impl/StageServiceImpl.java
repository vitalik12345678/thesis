package com.example.thesis.service.impl;

import com.example.thesis.entity.Document;
import com.example.thesis.entity.Stage;
import com.example.thesis.exception.UnAvailableDeleteException;
import com.example.thesis.repository.StageRepository;
import com.example.thesis.service.StageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StageServiceImpl extends CRUDServiceImpl<Stage,Long> implements StageService {

    private final StageRepository stageRepository;

    @Override
    protected JpaRepository<Stage, Long> getRepository () {
        return stageRepository;
    }

    @Override
    @Transactional
    public Stage delete (Long id) {

        Stage stage = findById(id);

        if (isNotExistAnyDocumentByStageId(stage.getStageId())) {
            throw new UnAvailableDeleteException("Stage cannot be deleted because there are active documents ");
        }

        return super.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isNotExistAnyDocumentByStageId (Long stageId) {
        return stageRepository.existStageInDocument(stageId);
    }

    @Override
    @Transactional
    public Stage changeStageByDocument (Document document,Long stageId) {
        Stage stage = findById(stageId);
        document.setStage(stage);
        return stage;
    }

    @Override
    @Transactional
    public Stage findFirstOrderStage () {
        return stageRepository.findMinOrder();
    }
}
