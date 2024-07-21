package com.example.skills.extractor.skills_Extractor.controller;


import com.example.skills.extractor.skills_Extractor.service.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class ChatController {

    private static final Logger log = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    private ChatService chatGPTService;

    @PostMapping("/search")
    public String search(@RequestBody SearchRequest searchRequest){
        return chatGPTService.getResponse(searchRequest.getQuery());
    }
}
