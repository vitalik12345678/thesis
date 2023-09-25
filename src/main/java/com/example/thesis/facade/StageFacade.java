package com.example.thesis.facade;

import com.example.thesis.dto.StageDTO;
import com.example.thesis.factory.StageFactory;
import com.example.thesis.service.StageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StageFacade {

    private final StageService stageService;
    private final StageFactory stageFactory;

    public List<StageDTO> getStageDTOList() {
        return stageFactory.toStageDTOList(stageService.findAll());
    }

    public StageDTO delete (Long id) {
        return stageFactory.toStageDTO(stageService.delete(id));
    }
}
