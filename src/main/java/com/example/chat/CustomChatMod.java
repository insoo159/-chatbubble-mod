package com.example.chat;

import com.example.chat.config.ModConfig;
import com.example.chat.config.ConfigScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class CustomChatMod implements ClientModInitializer {
    public static ModConfig CONFIG = new ModConfig();
    private static KeyBinding configKey;

    @Override
    public void onInitializeClient() {
        // 使用 1.21.11 相容的 KeyBinding 寫法
        configKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.customchat.open_config", // 快捷鍵語言 Key
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_K,               // 預設按鍵：K
                KeyBinding.MISC_CATEGORY       // 放在預設的「雜項」按鍵分類
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (configKey.wasPressed()) {
                client.setScreen(ConfigScreen.create(client.currentScreen));
            }
        });
    }
}
