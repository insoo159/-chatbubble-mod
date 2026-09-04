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

        int yOffset = context.getScaledWindowHeight() - 40;
        int maxMessages = Math.min(this.visibleMessages.size(), 5);

        for (int i = 0; i < maxMessages; i++) {
            ChatHudLine.Visible line = this.visibleMessages.get(i);
            int alpha = (int) (255 * getLineOpacity(currentTick, line));
            if (alpha <= 0) continue;

            BubbleChatRenderer.renderBubble(context, line.content(), 10, yOffset, alpha);
            yOffset -= 30;
        }

        ci.cancel();
    }

    private float getLineOpacity(int currentTick, ChatHudLine.Visible line) {
        // 修正：1.21.1 取得聊天訊息生成時間的方法名稱為 addedTime()
        int age = currentTick - line.addedTime();
        if (age > 200) return 0.0f;
        if (age > 180) return (200 - age) / 20.0f;
        return 1.0f;
    }
}
