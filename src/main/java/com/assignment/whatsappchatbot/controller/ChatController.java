package com.assignment.whatsappchatbot.controller;

import com.assignment.whatsappchatbot.dto.WhatsAppPayload;
import com.assignment.whatsappchatbot.model.ChatMessage;
import com.assignment.whatsappchatbot.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @GetMapping
    public String home() {
        return  """
            <html>
                <body style='font-family: sans-serif; padding: 20px;'>
                    <h2>WhatsApp Chatbot Backend is Live on Render</h2>
                    <p>This Spring Boot backend accepts and stores WhatsApp messages in Firebase Realtime DB.</p>
                    <p>You can test the API using the Swagger documentation below:</p>
                    <a href='/swagger-ui.html' target='_blank' style='
                        display: inline-block;
                        margin-top: 10px;
                        padding: 10px 15px;
                        background-color: #4CAF50;
                        color: white;
                        text-decoration: none;
                        border-radius: 4px;'>Open Swagger UI</a>
                </body>
            </html>
        """;
    }
    @PostMapping("/api/chat")
    public String receiveMessage(@RequestBody ChatMessage message) {
        chatService.saveMessage(message);
        return "Message received!";
    }

    @PostMapping("/api/chat/webhook")
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