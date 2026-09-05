package com.example.chat.mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatHud.class)
public class ChatHudMixin {

    /**
     * 相容 1.21.11 的聊天欄渲染攔截
     */
    @Inject(
        method = "render", 
        at = @At("HEAD"), 
        cancellable = true
    )
    private void onRender(DrawContext context, int currentTick, int mouseX, int mouseY, boolean focused, CallbackInfo ci) {
        // 若要取消原生的聊天欄渲染，解開下一行註解：
        // ci.cancel();
    }

    /**
     * 攔截接收到的聊天訊息
     */
    @Inject(
        method = "addMessage(Lnet/minecraft/text/Text;)V", 
        at = @At("HEAD")
    )
    private void onAddMessage(Text message, CallbackInfo ci) {
        // 在此處處理自訂聊天邏輯
    }
}
