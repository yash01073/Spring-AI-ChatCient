package com.promptai.openaichat.controller;

import com.promptai.openaichat.dto.bookDetails;
import com.promptai.openaichat.svc.OpenAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class OpenAIController {

    @Autowired
    OpenAIService openAIService;

    @GetMapping(value = "/getJoke")
    public String getJoke(@RequestParam String topic){
        return openAIService.getJoke(topic);
    }

    @GetMapping("/booksInJSON")
    public bookDetails getBooksInJSON(@RequestParam String category, @RequestParam String year){
        return openAIService.getBooksInJson(category, year);
    }
}
