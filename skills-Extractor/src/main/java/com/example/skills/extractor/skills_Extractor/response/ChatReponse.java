package com.example.skills.extractor.skills_Extractor.response;

import com.example.skills.extractor.skills_Extractor.Model.ChatChoice;
import lombok.Data;

import java.util.List;

@Data
public class ChatReponse {

    private List<ChatChoice> choices;
}
