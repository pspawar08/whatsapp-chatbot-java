package com.assignment.whatsappchatbot.service;

import com.assignment.whatsappchatbot.model.ChatMessage;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("chats");

    public void saveMessage(ChatMessage message) {
        dbRef.push().setValueAsync(message);
    }
}
