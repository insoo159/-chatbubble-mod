package com.example.chat.mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.ChatHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatHud.class)
public class ChatHudMixin {

    // 針對 1.21.11 使用標準 Mojang / Yarn 映射方法名稱 "render"
    @Inject(
        method = "render", 
        at = @At("HEAD")
    )
    private void onRender(DrawContext context, int currentTick, int mouseX, int mouseY, boolean focused, CallbackInfo ci) {
        // 在這裡加入或呼叫你的聊天氣泡渲染邏輯
    }
}
