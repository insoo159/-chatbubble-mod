package com.chatbubble;

import net.minecraft.text.Text;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public final class BubbleChatManager {

    private static final int MAX_ENTRIES = 50;
    private static final long VISIBLE_MILLIS = 10_000L;
    private static final long FADE_MILLIS = 2_000L;

    private static final Deque<Entry> ENTRIES = new ArrayDeque<>();

    private BubbleChatManager() {
    }

    public static synchronized void add(Text text, Text name, int nameColor) {
        ENTRIES.addLast(new Entry(text, name, nameColor, System.currentTimeMillis()));
        while (ENTRIES.size() > MAX_ENTRIES) {
            ENTRIES.removeFirst();
        }
    }

    public static synchronized List<Entry> visibleEntries() {
        List<Entry> list = new ArrayList<>();
        for (Entry e : ENTRIES) {
            if (e.opacity() > 0f) {
                list.add(e);
            }
        }
        return list;
    }

    public record Entry(Text text, Text name, int nameColor, long receivedAtMillis) {
        public float opacity() {
            long age = System.currentTimeMillis() - receivedAtMillis;
            if (age < VISIBLE_MILLIS) {
                return 1f;
            }
            if (age < VISIBLE_MILLIS + FADE_MILLIS) {
                return 1f - (float) (age - VISIBLE_MILLIS) / (float) FADE_MILLIS;
            }
            return 0f;
        }
    }
}
