package com.example.thesis.repository;

import com.example.thesis.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document,Long> {

    Optional<Document> findByStudentIdAndApproved(Long studentId,Boolean approved);

    List<Document> findAllByStudentId (Long studentId);
}
