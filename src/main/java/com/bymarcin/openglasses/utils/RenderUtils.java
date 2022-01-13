package com.bymarcin.openglasses.utils;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class RenderUtils {
    private static final RenderItem itemRender = new RenderItem();
    private static final RenderBlocks blockRender = new RenderBlocks();
    private static final Minecraft mc = Minecraft.getMinecraft();

    static {
        blockRender.useInventoryTint = false;
    }

    public static void renderItemStackOnGUI(ItemStack stack, float x, float y, float scale, float angle, float alpha) throws RuntimeException {
        if (stack == null) return;

        TextureManager tm = mc.getTextureManager();
        Item item = stack.getItem();
        int color;
        float f1, f2, f3;
        boolean renderAsBlock = item.getSpriteNumber() == 0 && RenderBlocks.renderItemIn3d(Block.getBlockFromItem(item).getRenderType());

        GL11.glPushMatrix();
        GL11.glAlphaFunc(GL11.GL_ALWAYS, 0);
        GL11.glScalef(scale, scale, 1F);
        GL11.glTranslatef(8f + x, 8f + y, 0.0f);
        GL11.glRotatef(angle, 0.0f, 0.0f, 1.0f);

        if (renderAsBlock) {
            GL11.glScalef(10.0F, 10.0F, 10.0F);
            GL11.glRotatef(210.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
        }

        for(int i = 0; i < item.getRenderPasses(stack.getItemDamage()); i++) {
            tm.bindTexture(item.getSpriteNumber() == 0 ? TextureMap.locationBlocksTexture : TextureMap.locationItemsTexture);

            color = item.getColorFromItemStack(stack, 0);
            f1 = (float)(color >> 16 & 255) / 255.0F;
            f2 = (float)(color >> 8 & 255) / 255.0F;
            f3 = (float)(color & 255) / 255.0F;
            GL11.glColor4f(f1, f2, f3, alpha);

            if (renderAsBlock) {
                blockRender.renderBlockAsItem(Block.getBlockFromItem(item), stack.getItemDamage(), 1.0f);
            } else {
                itemRender.renderIcon(-8, -8, item.getIcon(stack, i), 16, 16);
            }
        }
        GL11.glPopMatrix();
    }
}
