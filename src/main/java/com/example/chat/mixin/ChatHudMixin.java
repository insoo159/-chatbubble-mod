package com.example.chat.mixin;

import com.example.chat.gui.BubbleChatRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.ChatHudLine;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ChatHud.class)
public class ChatHudMixin {
    @Shadow @Final private List<ChatHudLine.Visible> visibleMessages;

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void renderCustomBubbleChat(DrawContext context, int currentTick, int mouseX, int mouseY, boolean focused, CallbackInfo ci) {
        if (this.visibleMessages.isEmpty()) return;

        // 攔截原本的聊天室渲染，改用氣泡樣式繪製
        int yOffset = context.getScaledWindowHeight() - 40;
        int maxMessages = Math.min(this.visibleMessages.size(), 5);

        for (int i = 0; i < maxMessages; i++) {
            ChatHudLine.Visible line = this.visibleMessages.get(i);
            int alpha = (int) (255 * getLineOpacity(currentTick, line));
            if (alpha <= 0) continue;

            // 繪製單條對話氣泡
            BubbleChatRenderer.renderBubble(context, line.content(), 10, yOffset, alpha);
            yOffset -= 30; // 氣泡垂直間距
        }

        // 取消原生聊天室繪製
        ci.cancel();
    }

    private float getLineOpacity(int currentTick, ChatHudLine.Visible line) {
        int age = currentTick - line.creationTick();
        if (age > 200) return 0.0f;
        if (age > 180) return (200 - age) / 20.0f;
        return 1.0f;
    }
}
