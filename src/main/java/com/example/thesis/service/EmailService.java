package com.example.thesis.service;

import com.example.thesis.entity.EmailDetails;

import java.util.List;

public interface EmailService {
    void sendInvitation(List<EmailDetails> emailDetails);
}
