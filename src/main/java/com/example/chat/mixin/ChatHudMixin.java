package com.example.chat.mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.ChatHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatHud.class)
public class ChatHudMixin {

    // 使用模糊匹配或正確的 render 方法名稱，避免寫死舊版的 method_1805
    @Inject(
        method = "render", 
        at = @At("HEAD"), 
        cancellable = true
    )
    private void onRender(DrawContext context, int currentTick, int mouseX, int mouseY, boolean focused, CallbackInfo ci) {
        // 自訂渲染邏輯
    }
}
