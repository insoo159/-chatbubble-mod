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

        // 文字大小調整
        general.addEntry(entryBuilder.startFloatSlider(Text.of("文字大小 (Text Scale)"), CustomChatMod.CONFIG.textScale, 0.5f, 2.0f)
                .setDefaultValue(1.0f)
                .setSaveConsumer(newValue -> CustomChatMod.CONFIG.textScale = newValue)
                .build());

        // 框框大小調整
        general.addEntry(entryBuilder.startFloatSlider(Text.of("框框大小 (Bubble Scale)"), CustomChatMod.CONFIG.bubbleScale, 0.5f, 2.0f)
                .setDefaultValue(1.0f)
                .setSaveConsumer(newValue -> CustomChatMod.CONFIG.bubbleScale = newValue)
                .build());

        return builder.build();
    }
}
