package com.bymarcin.openglasses.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class RenderUtils {
    public static final RenderItem itemRender = new RenderItem();
    private static final Minecraft mc = Minecraft.getMinecraft();

    public static void renderItemStackOnGUI(ItemStack stack, float x, float y, float scale, float angle) {
        if (stack == null) return;

        FontRenderer font = stack.getItem().getFontRenderer(stack);
        if (font == null) font = mc.fontRenderer;

        GL11.glPushMatrix();
        GL11.glScalef(scale, scale, 1F);
        GL11.glTranslatef(8f + x, 8f + y, 0.0f);
        GL11.glRotatef(angle, 0.0f, 0.0f, 1.0f);

        itemRender.renderItemAndEffectIntoGUI(font, mc.getTextureManager(), stack, -8, -8);

        GL11.glPopMatrix();
    }
}
