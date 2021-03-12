package com.example.post.service.QuestionServiceImpl;

import com.example.post.client.MailClient;
import com.example.post.dto.MailRequestDto;
import com.example.post.model.User;
import com.example.post.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MailServiceImpl implements MailService {
    private JavaMailSender javaMailSender;

    @Autowired
    public MailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Autowired
    private MailClient mailClient;

    public ArrayList<String> getEmailByFeignClient(String category) {
        ArrayList<String> emailList = mailClient.getEmails(category);
        return  emailList;
    }

    @Override
    public void sendEmail(MailRequestDto mailRequestDto) {
        SimpleMailMessage mail = new SimpleMailMessage();

        ArrayList<String> emailList = getEmailByFeignClient(mailRequestDto.getCategory());
        String emailArray[] = new String[emailList.size()];
        for(int i=0; i<emailList.size(); i++) {
            emailArray[i]=emailList.get(i);
        }
//        String array[] = new String[arrlist.size()];
//        for(int j =0;j<arrlist.size();j++){
//            array[j] = arrlist.get(j);
//        }
//        String emailArray[] = new String[1];
//        emailArray[0] = "akarshan.n1999@gmail.com";

        String newline = System.getProperty("line.separator");

        mail.setTo(emailArray);
        mail.setSubject(mailRequestDto.getQuestionTitle());
        mail.setText(mailRequestDto.getQuestionText() + newline + "Please share your opinion about it :)");

        javaMailSender.send(mail);
    }
}
