package com.chatbubble;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.OrderedText;

import java.util.List;

public final class BubbleRenderer {

    private static final int BORDER_RGB = 0x1B1620;
    private static final int PAPER_RGB = 0xF3EEDD;
    private static final int TEXT_RGB = 0x211C14;

    private static final int PADDING = 4;
    private static final int LINE_HEIGHT = 10;
    private static final int GAP_BETWEEN_BUBBLES = 6;
    private static final int TAIL_HEIGHT = 6;
    private static final int NAME_GAP = 3;
    private static final int BOTTOM_MARGIN = 34;
    private static final int LEFT_MARGIN = 6;

    private BubbleRenderer() {
    }

    public static void render(DrawContext ctx, TextRenderer tr, List<BubbleChatManager.Entry> entries,
                               int screenWidth, int screenHeight) {
        int maxWidth = Math.min(320, screenWidth - LEFT_MARGIN * 2 - 20);
        int y = screenHeight - BOTTOM_MARGIN;

        for (int i = entries.size() - 1; i >= 0; i--) {
            BubbleChatManager.Entry entry = entries.get(i);
            float alpha = entry.opacity();
            if (alpha <= 0f) {
                continue;
            }

            List<OrderedText> lines = tr.wrapLines(entry.text(), maxWidth - PADDING * 2);
            if (lines.isEmpty()) {
                continue;
            }

            int textWidth = 0;
            for (OrderedText line : lines) {
                textWidth = Math.max(textWidth, tr.getWidth(line));
            }
            int bubbleWidth = textWidth + PADDING * 2;
            int bubbleHeight = lines.size() * LINE_HEIGHT + PADDING * 2 - (LINE_HEIGHT - tr.fontHeight);

            int nameHeight = tr.fontHeight;
            int blockHeight = bubbleHeight + 2 + TAIL_HEIGHT + NAME_GAP + nameHeight;

            y -= blockHeight;
            int bx = LEFT_MARGIN;
            int by = y;

            int alphaByte = (int) (alpha * 255f);
            int border = (alphaByte << 24) | BORDER_RGB;
            int paper = ((int) (alpha * 235f) << 24) | PAPER_RGB;
            int text = (Math.max(alphaByte, 60) << 24) | TEXT_RGB;
            int name = (alphaByte << 24) | (entry.nameColor() & 0x00FFFFFF);

            ctx.fill(bx, by, bx + bubbleWidth + 2, by + bubbleHeight + 2, border);
            ctx.fill(bx + 1, by + 1, bx + bubbleWidth + 1, by + bubbleHeight + 1, paper);

            int ty = by + 1 + PADDING;
            for (OrderedText line : lines) {
                ctx.drawText(tr, line, bx + 1 + PADDING, ty, text, false);
                ty += LINE_HEIGHT;
            }

            int tailX = bx + 10;
            int tailY = by + bubbleHeight + 2;
            ctx.fill(tailX, tailY, tailX + 14, tailY + 2, border);
            ctx.fill(tailX + 2, tailY + 2, tailX + 12, tailY + 4, border);
            ctx.fill(tailX + 4, tailY + 4, tailX + 10, tailY + 6, border);
            ctx.fill(tailX + 2, tailY + 1, tailX + 11, tailY + 2, paper);
            ctx.fill(tailX + 4, tailY + 3, tailX + 9, tailY + 4, paper);

            int nameY = tailY + TAIL_HEIGHT + NAME_GAP - 2;
            ctx.drawTextWithShadow(tr, entry.name(), bx, nameY, name);

            y -= GAP_BETWEEN_BUBBLES;
        }
    }
}
