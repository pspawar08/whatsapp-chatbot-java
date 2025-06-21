package com.assignment.whatsappchatbot.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Configuration
public class FirebaseConfig {
    @PostConstruct
    public void init() {
        try {
            String firebaseCreds = System.getenv("FIREBASE_KEY");

            if (firebaseCreds == null || firebaseCreds.isEmpty()) {
                throw new IllegalStateException("FIREBASE_KEY environment variable is not set!");
            }

            InputStream serviceAccount = new ByteArrayInputStream(firebaseCreds.getBytes(StandardCharsets.UTF_8));

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://whatsapp-chatbot-b7269-default-rtdb.asia-southeast1.firebasedatabase.app/")
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
