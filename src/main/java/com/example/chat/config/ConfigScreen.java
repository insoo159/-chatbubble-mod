package com.example.chat.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ConfigScreen {

    public static Screen create(Screen parent) {
        ModConfig config = com.example.chat.CustomChatMod.CONFIG;
        
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.literal("聊天框設定"));

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        ConfigCategory general = builder.getOrCreateCategory(Text.literal("一般設定"));

        // ===== 聊天框預覽按鈕條目 =====
        general.addEntry(entryBuilder.startTextDescription(Text.literal("§e[ 點擊此處開啟聊天框預覽 ]"))
                .setTooltip(Text.literal("點擊開啟即時預覽畫面"))
                .build());

        // 可以在這裡加入你的其他設定項
        general.addEntry(entryBuilder.startIntField(Text.literal("氣泡停留時間 (秒)"), config.bubbleDuration)
                .setDefaultValue(5)
                .setSaveConsumer(newValue -> config.bubbleDuration = newValue)
                .build());

        builder.setSavingRunnable(() -> {
            // 儲存設定邏輯
        });

        return builder.build();
    }
}
