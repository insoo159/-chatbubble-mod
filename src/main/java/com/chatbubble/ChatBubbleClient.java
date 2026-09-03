package com.chatbubble;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import java.awt.Color;
import java.util.Random;

public class ChatBubbleClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        ClientReceiveMessageEvents.CHAT.register((message, signedMessage, sender, params, receptionTimestamp) -> {
            String senderName = sender != null ? sender.getName() : "???";
            BubbleChatManager.add(message, Text.literal(senderName), nameColor(senderName));
        });

        ClientReceiveMessageEvents.GAME.register((message, overlay) -> {
            if (overlay) {
                return;
            }
            BubbleChatManager.add(message, Text.literal("系統"), 0xAAAAAA);
        });

        HudRenderCallback.EVENT.register((drawContext, tickCounter) -> {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.player == null || client.options.hudHidden) {
                return;
            }
            var entries = BubbleChatManager.visibleEntries();
            if (entries.isEmpty()) {
                return;
            }
            BubbleRenderer.render(
                    drawContext,
                    client.textRenderer,
                    entries,
                    client.getWindow().getScaledWidth(),
                    client.getWindow().getScaledHeight()
            );
        });
    }

    private static int nameColor(String name) {
        Random random = new Random(name.hashCode());
        float hue = random.nextFloat();
        int rgb = Color.HSBtoRGB(hue, 0.55f, 0.95f);
        return 0xFF000000 | (rgb & 0xFFFFFF);
    }
}
