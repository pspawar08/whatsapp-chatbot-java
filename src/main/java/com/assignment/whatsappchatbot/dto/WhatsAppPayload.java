package com.assignment.whatsappchatbot.dto;

import java.util.List;

public class WhatsAppPayload {
    private List<Entry> entry;

    public List<Entry> getEntry() { return entry; }
    public void setEntry(List<Entry> entry) { this.entry = entry; }

    public static class Entry {
        private List<Change> changes;

        public List<Change> getChanges() { return changes; }
        public void setChanges(List<Change> changes) { this.changes = changes; }
    }

    public static class Change {
        private Value value;

        public Value getValue() { return value; }
        public void setValue(Value value) { this.value = value; }
    }

    public static class Value {
        private List<Message> messages;

        public List<Message> getMessages() { return messages; }
        public void setMessages(List<Message> messages) { this.messages = messages; }
    }

    public static class Message {
        private String from;
        private Text text;

        public String getFrom() { return from; }
        public void setFrom(String from) { this.from = from; }

        public Text getText() { return text; }
        public void setText(Text text) { this.text = text; }
    }

    public static class Text {
        private String body;

        public String getBody() { return body; }
        public void setBody(String body) { this.body = body; }
    }
}