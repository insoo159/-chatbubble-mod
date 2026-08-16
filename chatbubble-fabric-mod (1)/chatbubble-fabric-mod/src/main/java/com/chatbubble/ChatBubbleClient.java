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

        // 玩家聊天訊息（有署名的玩家發言）
        ClientReceiveMessageEvents.CHAT.register((message, signedMessage, sender, params, receptionTimestamp) -> {
            String senderName = sender != null ? sender.getName() : "???";
            BubbleChatManager.add(message, Text.literal(senderName), nameColor(senderName));
        });

        // 系統/伺服器訊息（成就、加入退出提示等），略過動作列(overlay)訊息避免重複
        ClientReceiveMessageEvents.GAME.register((message, overlay) -> {
            if (overlay) {
                return;
            }
            BubbleChatManager.add(message, Text.literal("系統"), 0xAAAAAA);
        });

        // 每一幀畫出目前還沒淡出的泡泡
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

    /**
     * 依名字產生一個固定但看起來隨機的顏色，讓每個玩家的名字顏色不同。
     */
    private static int nameColor(String name) {
        Random random = new Random(name.hashCode());
        float hue = random.nextFloat();
        int rgb = Color.HSBtoRGB(hue, 0.55f, 0.95f);
        return 0xFF000000 | (rgb & 0xFFFFFF);
    }
}
