package com.chatbubble.mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.ChatHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatHud.class)
public class ChatHudMixin {

    @Inject(method = "render", at = @At("HEAD"), cancellable = true, require = 0)
    private void cancelVanillaChatRender(
            DrawContext context, 
            int currentTick, 
            int mouseX, 
            int mouseY, 
            boolean focused, 
            boolean isChatFocused, 
            CallbackInfo ci) {
        
        // 保持原本裡面的程式碼（若原本無內容可留空）
    }
}
