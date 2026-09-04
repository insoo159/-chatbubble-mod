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
        // 使用 1.21.11 新版的 KeyBinding.Category 物件
        configKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.customchat.open_config",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_K,
                KeyBinding.Category.MISC
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (configKey.wasPressed()) {
                client.setScreen(ConfigScreen.create(client.currentScreen));
            }
        });
    }
}
