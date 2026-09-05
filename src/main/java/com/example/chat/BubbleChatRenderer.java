package com.example.chat;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;

import java.util.List;

public class BubbleChatRenderer {

    public static void render(DrawContext context, RenderTickCounter tickCounter) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client.player == null || client.world == null || client.options.hudHidden) {
            return;
        }

        List<CustomChatManager.ChatEntry> messages = CustomChatManager.getMessages();
        if (messages.isEmpty()) return;

        int x = 10;
        int bottomY = context.getScaledWindowHeight() - 40; 
        int lineHeight = 12;
        int padding = 6;
        
        long now = System.currentTimeMillis();
        boolean isChatOpen = client.currentScreen != null;

        // 計算顯示訊息
        int visibleCount = 0;
        for (CustomChatManager.ChatEntry entry : messages) {
            // 開啟聊天框時顯示全部，平時訊息留存 10 秒
            if (isChatOpen || (now - entry.timestamp < 10000)) {
                visibleCount++;
            }
        }

        if (visibleCount == 0) return;

        int totalHeight = (visibleCount * lineHeight) + (padding * 2);
        int topY = bottomY - totalHeight;
        int maxWidth = 220;

        // 繪製現代感半透明黑色背景
        context.fill(x, topY, x + maxWidth, bottomY, 0x90000000);

        // 逐行繪製文字
        int currentY = topY + padding;
        int rendered = 0;
        for (CustomChatManager.ChatEntry entry : messages) {
            if (isChatOpen || (now - entry.timestamp < 10000)) {
                context.drawText(
                    client.textRenderer,
                    entry.text,
                    x + padding,
                    currentY,
                    0xFFFFFFFF,
                    true
                );
                currentY += lineHeight;
                rendered++;
                if (rendered >= visibleCount) break;
            }
        }
    }
}
