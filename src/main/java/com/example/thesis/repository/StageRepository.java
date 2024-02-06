package com.example.thesis.repository;

import com.example.thesis.entity.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StageRepository extends JpaRepository<Stage,Long> {
    @Query(value = " SELECT EXISTS( SELECT 1 FROM document where document.stage = ?1)",nativeQuery = true)
    Boolean existStageInDocument(Long stageId);

    @Query(value = "SELECT * FROM stage where serial_order = (SELECT (min(serial_order)) FROM stage) ",nativeQuery = true)
    Stage findMinOrder ();

}
