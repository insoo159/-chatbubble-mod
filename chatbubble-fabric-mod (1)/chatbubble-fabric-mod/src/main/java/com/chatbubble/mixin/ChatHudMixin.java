package com.chatbubble.mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.ChatHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 攔截原版聊天紀錄的繪製方法，直接取消它，
 * 讓 ChatBubbleClient 裡的 HudRenderCallback 全權負責畫面呈現。
 * 輸入聊天訊息的畫面 (ChatScreen) 不受影響，打字、送出訊息都照常運作。
 */
@Mixin(ChatHud.class)
public class ChatHudMixin {

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void chatbubble$cancelVanillaChatRender(DrawContext context, int currentTick, int mouseX, int mouseY, boolean focused, CallbackInfo ci) {
        ci.cancel();
    }
}
