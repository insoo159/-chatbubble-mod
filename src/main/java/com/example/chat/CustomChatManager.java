package com.example.chat;

import net.minecraft.text.Text;
import java.util.ArrayList;
import java.util.List;

public class CustomChatManager {
    public static class ChatEntry {
        public final Text text;
        public final long timestamp;

        public ChatEntry(Text text) {
            this.text = text;
            this.timestamp = System.currentTimeMillis();
        }
    }

    private static final List<ChatEntry> messages = new ArrayList<>();
    private static final int MAX_MESSAGES = 20;

    public static synchronized void addMessage(Text text) {
        messages.add(new ChatEntry(text));
        if (messages.size() > MAX_MESSAGES) {
            messages.remove(0);
        }
    }

    public static synchronized List<ChatEntry> getMessages() {
        return new ArrayList<>(messages);
    }
}
