package com.example.skills.extractor.skills_Extractor.service;

import com.example.skills.extractor.skills_Extractor.controller.ChatController;
import com.example.skills.extractor.skills_Extractor.request.ChatRequest;
import com.example.skills.extractor.skills_Extractor.response.ChatReponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.apache.http.client.methods.HttpPost;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class ChatService {

    private static final Logger log = LoggerFactory.getLogger(ChatController.class);

    @Value("${OPEN_AI_URL}")
    private String OPEN_AI_URL;

    @Value("${OPEN_AI_KEY}")
    private String OPEN_AI_KEY;

    public String getResponse(String query) {

        log.info("Request enter into service: " + query);

        ChatRequest chatRequest = new ChatRequest();
        chatRequest.setPrompt(query);

        HttpPost post = new HttpPost(OPEN_AI_URL);
        post.addHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Bearer " + OPEN_AI_KEY);

        Gson gson = new Gson();
        String body = gson.toJson(chatRequest);

        log.info("Request body: " + body);

        try {
            final StringEntity entity;
            try {
                entity = new StringEntity(body);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            post.setEntity(entity);

            try (CloseableHttpClient httpClient = HttpClients.custom().build();
                 CloseableHttpResponse response = httpClient.execute(post)) {

                String responseBody = EntityUtils.toString(response.getEntity());

                log.info("responseBody: " + responseBody);

                ChatReponse chatResponse = gson.fromJson(responseBody, ChatReponse.class);
                return chatResponse.getChoices().get(0).getText();

            } catch (Exception e) {
                return "API Error";
            }

        } catch (Exception e) {
            return "Fetch Failed";

        }


    }

}
