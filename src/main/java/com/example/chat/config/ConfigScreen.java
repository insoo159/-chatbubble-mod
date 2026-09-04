package com.example.chat.config;

import com.example.chat.CustomChatMod;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ConfigScreen {
    public static Screen create(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.of("對話框模組設定"));

        ConfigCategory general = builder.getOrCreateCategory(Text.of("一般設定"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        // 修正：使用 startFloatField 建立浮點數設定項
        general.addEntry(entryBuilder.startFloatField(Text.of("文字大小 (Text Scale)"), CustomChatMod.CONFIG.textScale)
                .setDefaultValue(1.0f)
                .setMin(0.5f)
                .setMax(2.0f)
                .setSaveConsumer(newValue -> CustomChatMod.CONFIG.textScale = newValue)
                .build());

        general.addEntry(entryBuilder.startFloatField(Text.of("框框大小 (Bubble Scale)"), CustomChatMod.CONFIG.bubbleScale)
                .setDefaultValue(1.0f)
                .setMin(0.5f)
                .setMax(2.0f)
                .setSaveConsumer(newValue -> CustomChatMod.CONFIG.bubbleScale = newValue)
                .build());

        return builder.build();
    }
}
