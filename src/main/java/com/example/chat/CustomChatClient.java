package com.example.chat;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class CustomChatClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // 註冊 HUD 渲染事件
        HudRenderCallback.EVENT.register(BubbleChatRenderer::render);
    }
}
