package com.example.chat.mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.ChatHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatHud.class)
public class ChatHudMixin {

    @Inject(
        method = "render", 
        at = @At("HEAD"), 
        cancellable = true
    )
    private void onRender(DrawContext context, int currentTick, int mouseX, int mouseY, boolean focused, boolean chatLineFocused, CallbackInfo ci) {
        // 自訂渲染邏輯
    }
}
