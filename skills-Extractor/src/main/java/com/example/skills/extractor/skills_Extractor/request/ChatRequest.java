package com.example.skills.extractor.skills_Extractor.request;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ChatRequest {
    private String model="davinci-002";
    private String prompt;
    private int temperature=1;

    @SerializedName(value = "max_tokens")
    private int maxTokens=256;


}
