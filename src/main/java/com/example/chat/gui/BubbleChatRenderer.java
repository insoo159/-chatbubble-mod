package com.example.chat.gui;

import com.example.chat.CustomChatMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.OrderedText;
import net.minecraft.util.Identifier;

public class BubbleChatRenderer {
    private static final Identifier BUBBLE_TEXTURE = Identifier.of("customchat", "textures/gui/speech_bubble.png");

    public static void renderBubble(DrawContext context, OrderedText text, int x, int y, int alpha) {
        MinecraftClient client = MinecraftClient.getInstance();
        TextRenderer textRenderer = client.textRenderer;

        float textScale = CustomChatMod.CONFIG.textScale;
        float bubbleScale = CustomChatMod.CONFIG.bubbleScale;
        int padding = CustomChatMod.CONFIG.padding;

        int textWidth = textRenderer.getWidth(text);
        int textHeight = textRenderer.fontHeight;

        int bubbleWidth = (int) ((textWidth + padding * 2) * bubbleScale);
        int bubbleHeight = (int) ((textHeight + padding * 2) * bubbleScale);

        context.getMatrices().push();
        context.getMatrices().translate(x, y, 0);
        
        // 修正 1.21.1 繪製貼圖語法
        context.drawTexture(BUBBLE_TEXTURE, 0, 0, 0.0F, 0.0F, bubbleWidth, bubbleHeight, 32, 32);

        context.getMatrices().push();
        context.getMatrices().translate(padding * bubbleScale, padding * bubbleScale, 0);
        context.getMatrices().scale(textScale, textScale, 1.0f);

        int color = 0x000000 | (alpha << 24);
        context.drawText(textRenderer, text, 0, 0, color, false);

        context.getMatrices().pop();
        context.getMatrices().pop();
    }
}
