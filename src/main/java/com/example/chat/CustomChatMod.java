package com.example.chat;

import com.example.chat.config.ModConfig;
import com.example.chat.config.ConfigScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class CustomChatMod implements ClientModInitializer {
    public static ModConfig CONFIG = new ModConfig();
    private static KeyBinding configKey;

    @Override
    public void onInitializeClient() {
        // 使用字串作為 Category 名稱，確保完美相容 Fabric API
        configKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.customchat.open_config",     // 快捷鍵名稱的翻譯 Key
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_K,                  // 預設按鍵：K
                "category.customchat.general"     // 分類名稱直接使用字串
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (configKey.wasPressed()) {
                client.setScreen(ConfigScreen.create(client.currentScreen));
            }
        });
    }
}
