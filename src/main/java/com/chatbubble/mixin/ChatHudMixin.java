package com.example.chatbubble.mixin; // 請依據專案實際 package 調整

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.ChatHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatHud.class)
public class ChatHudMixin {

    // 加上 require = 0 可以防止因為版本差異找不到方法而直接暴斃
    @Inject(method = "render", at = @At("HEAD"), cancellable = true, require = 0)
    private void cancelVanillaChatRender(DrawContext context, int currentTick, int mouseX, int mouseY, boolean focused, CallbackInfo ci) {
        // 如果你需要取消原版聊天欄渲染，保持原本邏輯；
        // 若 1.21.11 參數名稱有異，請依據官方 Mappings 確認參數順序。
    }
}
