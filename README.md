# WhatsApp Chatbot Backend — Internshala Assignment

This Spring Boot project simulates a WhatsApp chatbot backend:

### Features
- Accepts simulated WhatsApp messages via REST endpoint `/api/chat`
- Stores messages in Firebase Realtime Database
- Swagger UI available for testing

### Technologies Used
- Java 17
- Spring Boot 3.5.3
- Firebase Admin SDK
- Render.com (Live deployment)
- Docker (for building)

---

## Deployed Backend
> [Click here to open the live backend](https://whatsapp-chatbot-java.onrender.com/)

Home page includes link to Swagger UI for easy testing.

---

## How to Run (Optional - for local use)

1. Make sure Java 17 and Maven are installed.
2. Clone or unzip this project.
3. Add your Firebase credentials:
   - Store `serviceAccountKey.json` securely.
   - Set it in environment variable `FIREBASE_KEY` before running.
4. Run with Docker:

```bash
docker build -t whatsapp-chatbot .
docker run -p 8080:8080 -e FIREBASE_KEY="...(your JSON content)..." whatsapp-chatbot
