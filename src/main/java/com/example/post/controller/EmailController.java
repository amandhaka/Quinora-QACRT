package com.example.post.controller;

import com.example.post.dto.MailRequestDto;
import com.example.post.model.User;
import com.example.post.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @Autowired
    private MailService mailService;

    @Autowired
    private User user;

    @PostMapping ("send-mail")
    public String send(@RequestBody MailRequestDto mailRequestDto) {
//        user.setEmailAddress("akshay.natraj@gmail.com");

        try {
            mailService.sendEmail(mailRequestDto);
        } catch (MailException mailException) {
            System.out.println(mailException);
        }
        return "Mail sent!!";
    }
}
