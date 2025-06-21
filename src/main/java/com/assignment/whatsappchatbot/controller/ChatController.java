package com.assignment.whatsappchatbot.controller;

import com.assignment.whatsappchatbot.dto.WhatsAppPayload;
import com.assignment.whatsappchatbot.model.ChatMessage;
import com.assignment.whatsappchatbot.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @PostMapping
    public String receiveMessage(@RequestBody ChatMessage message) {
        chatService.saveMessage(message);
        return "Message received!";
    }

    @PostMapping("/webhook")
    public ResponseEntity<String> simulateWhatsAppWebhook(@RequestBody WhatsAppPayload payload) {
        try {
            WhatsAppPayload.Message message = payload
                    .getEntry().get(0)
                    .getChanges().get(0)
                    .getValue()
                    .getMessages().get(0);

            String userId = message.getFrom();
            String text = message.getText().getBody();

            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setUserId(userId);
            chatMessage.setMessage(text);

            chatService.saveMessage(chatMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok("Webhook processed");
    }

}