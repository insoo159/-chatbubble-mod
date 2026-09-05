package com.example.chat;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;

public class BubbleChatRenderer {
    public static void render(DrawContext context, RenderTickCounter tickCounter) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client.player == null || client.world == null || client.options.hudHidden) {
            return;
        }

        // 在此處撰寫你的氣泡繪製邏輯
    }
}
