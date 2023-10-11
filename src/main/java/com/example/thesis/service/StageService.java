package com.example.thesis.service;

import com.example.thesis.entity.Document;
import com.example.thesis.entity.Stage;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StageService extends CRUDService<Stage,Long> {
    @Transactional(readOnly = true)
    boolean isNotExistAnyDocumentByStageId (Long stageId);

    Stage changeStageByDocument (Document document, Long stageId);

    Stage findFirstOrderStage ();

}
