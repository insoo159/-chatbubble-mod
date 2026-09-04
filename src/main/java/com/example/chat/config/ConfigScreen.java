package com.example.chat.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ConfigScreen {

    public static Screen create(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.literal("聊天框設定"));

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        ConfigCategory general = builder.getOrCreateCategory(Text.literal("一般設定"));

        // ===== 開啟預覽畫面的說明與操作 =====
        general.addEntry(entryBuilder.startTextDescription(Text.literal("§a[ 點擊進入聊天框預覽畫面 ]"))
                .setTooltip(Text.literal("開啟獨立的預覽視窗，即時查看聊天氣泡效果"))
                .build());

        return builder.build();
    }
}
