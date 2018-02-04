package com.core.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class Telegram {

    public static final String URL = "https://api.telegram.org/bot537154707:AAHFRhdoGvfYDX_Yr-45Q7CYlGjgBRoe7yc";

    @Value("${core.config.telegram.chatid}")
    private String defaultChatId;

    public void sendMessage(String text, String chatId) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("chat_id", chatId == null ? defaultChatId : chatId);
        map.add("text", text);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity( URL + "/sendMessage", request , String.class);

    }

}
