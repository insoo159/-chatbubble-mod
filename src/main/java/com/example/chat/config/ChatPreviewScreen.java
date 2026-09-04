package com.example.chat.config;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class ChatPreviewScreen extends Screen {
    private final Screen parent;

    public ChatPreviewScreen(Screen parent) {
        super(Text.literal("聊天框預覽"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        // 新增返回按鈕
        this.addDrawableChild(ButtonWidget.builder(Text.literal("返回設定"), button -> {
            if (this.client != null) {
                this.client.setScreen(this.parent);
            }
        }).dimensions(this.width / 2 - 50, this.height - 40, 100, 20).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);

        int centerX = this.width / 2;
        int centerY = this.height / 2;

        context.drawCenteredTextWithShadow(this.textRenderer, "=== 聊天框實時預覽 ===", centerX, centerY - 60, 0xFFFFFF);

        // 模擬聊天框外框與訊息
        context.fill(centerX - 100, centerY - 25, centerX + 100, centerY + 25, 0x80000000);
        context.drawCenteredTextWithShadow(this.textRenderer, "玩家: 這是一條預覽聊天訊息！", centerX, centerY - 4, 0x55FF55);

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public void close() {
        if (this.client != null) {
            this.client.setScreen(this.parent);
        }
    }
}
