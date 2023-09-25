package com.example.thesis.factory;

import com.example.thesis.dto.StageDTO;
import com.example.thesis.entity.Stage;
import com.example.thesis.mapper.EntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StageFactory {

    private final EntityMapper entityMapper;

    public List<StageDTO> toStageDTOList (List<Stage> all) {
        return entityMapper.toStageDTOList(all);
    }

    public StageDTO toStageDTO (Stage stage) {
        return entityMapper.toStageDTO(stage);
    }
}
