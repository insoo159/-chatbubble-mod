package com.example.chat;

import com.example.chat.config.ModConfig;
import net.fabricmc.api.ClientModInitializer;

public class CustomChatMod implements ClientModInitializer {
    public static ModConfig CONFIG = new ModConfig();

    @Override
    public void onInitializeClient() {
        // 已移除 KeyBinding 註冊，避免 1.21.11 的方法不匹配問題
        // 玩家可透過 Mod Menu 或設定菜單開啟 ConfigScreen
    }
}
