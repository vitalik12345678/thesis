package com.example.thesis.repository;

import com.example.thesis.entity.Document;
import org.springframework.content.commons.store.ContentStore;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentContentStore extends ContentStore<Document,String> {
}
