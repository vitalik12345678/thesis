package com.example.thesis.repository;

import com.example.thesis.entity.Document;
import com.example.thesis.entity.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StageRepository extends JpaRepository<Stage,Long> {
    @Query(value = " SELECT EXISTS( SELECT 1 FROM document where document.stage = ?1)",nativeQuery = true)
    Boolean existStageInDocument(Long stageId);

    @Query(value = "SELECT min(stage.serial_order) FROM stage ",nativeQuery = true)
    Stage findMinOrder ();

}
