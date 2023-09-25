package com.example.thesis.mapper;

import com.example.thesis.dto.StageDTO;
import com.example.thesis.entity.Stage;

import java.util.List;

public interface StageMapper {

    StageDTO toStageDTO (Stage stage);

    List<StageDTO> toStageDTOList(List<Stage> stageList);
}
