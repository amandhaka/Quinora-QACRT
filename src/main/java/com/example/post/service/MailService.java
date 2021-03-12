package com.example.post.service;

import com.example.post.dto.MailRequestDto;

public interface MailService {
    public void sendEmail(MailRequestDto mailRequestDto);
}
