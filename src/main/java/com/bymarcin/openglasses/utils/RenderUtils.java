package com.bymarcin.openglasses.utils;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import org.lwjgl.opengl.GL11;

import static net.minecraftforge.client.IItemRenderer.ItemRenderType.INVENTORY;

public class RenderUtils {
    private static final RenderItem itemRender = new RenderItem();
    private static final RenderBlocks blockRender = new RenderBlocks();
    private static final Minecraft mc = Minecraft.getMinecraft();

    static {
        blockRender.useInventoryTint = false;
    }

    public static void renderItemStackOnGUI(ItemStack stack, float x, float y, float scale, float angle) throws RuntimeException {
        if (stack == null) return;

        TextureManager tm = mc.getTextureManager();

        GL11.glPushMatrix();
        GL11.glScalef(scale, scale, 1F);
        GL11.glTranslatef(8f + x, 8f + y, 0.0f);
        GL11.glRotatef(angle, 0.0f, 0.0f, 1.0f);
        RenderHelper.enableGUIStandardItemLighting();
        itemRender.renderItemAndEffectIntoGUI(mc.fontRenderer, tm, stack, 0, 0);
        RenderHelper.disableStandardItemLighting();

        GL11.glPopMatrix();
    }
}
