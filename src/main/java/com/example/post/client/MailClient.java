package com.example.post.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@FeignClient(name = "mail-client", url = "localhost:8081/user")
public interface MailClient {
    @RequestMapping(method = RequestMethod.GET, path = "/emailaddress")
    public ArrayList<String> getEmails(@RequestParam(value = "category") String category);
}
