package com.invasion.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.invasion.tileentity.TileEntityNexus;

public class GuiNexus extends GuiContainer {

	private static final ResourceLocation background = new ResourceLocation("invmod:textures/nexusgui.png");
    private TileEntityNexus tileEntityNexus;

    public GuiNexus(InventoryPlayer inventoryplayer, TileEntityNexus tileentityNexus) {
        super(new ContainerNexus(inventoryplayer, tileentityNexus));
        this.tileEntityNexus = tileentityNexus;
    }
    
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        this.fontRendererObj.drawString("Nexus - Level " + this.tileEntityNexus.getNexusLevel(), 46, 6, 4210752);
        this.fontRendererObj.drawString("" + this.tileEntityNexus.getNexusKills() + " mobs killed", 96, 60, 4210752);
        this.fontRendererObj.drawString("R: " + this.tileEntityNexus.getSpawnRadius(), 142, 72, 4210752);
        if (this.tileEntityNexus.getMode() == 1 || this.tileEntityNexus.getMode() == 3) {
            this.fontRendererObj.drawString("Activated!", 13, 62, 4210752);
            this.fontRendererObj.drawString("Wave " + this.tileEntityNexus.getCurrentWave(), 55, 37, 4210752);
        } else if (this.tileEntityNexus.getMode() == 2) {
            this.fontRendererObj.drawString("Power:", 56, 31, 4210752);
            this.fontRendererObj.drawString("" + this.tileEntityNexus.getNexusPowerLevel(), 61, 44, 4210752);
        }
        if (this.tileEntityNexus.isActivating() && this.tileEntityNexus.getMode() == 0) {
            this.fontRendererObj.drawString("Activating...", 13, 62, 4210752);
            if (this.tileEntityNexus.getMode() != 4) {
                this.fontRendererObj.drawString("Are you sure?", 8, 72, 4210752);
            }
        }
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        //this.field_146297_k.func_110434_K().func_110577_a(background);
        Minecraft.getMinecraft().getTextureManager().bindTexture(background);
        int j = (this.guiLeft - this.xSize) / 2;
        int k = (this.guiTop - this.ySize) / 2;
        this.drawTexturedModalRect(j, k, 0, 0, this.xSize, this.ySize);
        int l = this.tileEntityNexus.getGenerationProgressScaled(26);
        this.drawTexturedModalRect(j + 126, k + 28 + 26 - l, 185, 26 - l, 9, l);
        l = this.tileEntityNexus.getCookProgressScaled(18);
        this.drawTexturedModalRect(j + 31, k + 51, 204, 0, l, 2);
        if (this.tileEntityNexus.getMode() == 1 || this.tileEntityNexus.getMode() == 3) {
            this.drawTexturedModalRect(j + 19, k + 29, 176, 0, 9, 31);
            this.drawTexturedModalRect(j + 19, k + 19, 194, 0, 9, 9);
        } else if (this.tileEntityNexus.getMode() == 2) {
            this.drawTexturedModalRect(j + 19, k + 29, 176, 31, 9, 31);
        }
        if ((this.tileEntityNexus.getMode() == 0 || this.tileEntityNexus.getMode() == 2) && this.tileEntityNexus.isActivating()) {
            l = this.tileEntityNexus.getActivationProgressScaled(31);
            this.drawTexturedModalRect(j + 19, k + 29 + 31 - l, 176, 31 - l, 9, l);
        } else if (this.tileEntityNexus.getMode() == 4 && this.tileEntityNexus.isActivating()) {
            l = this.tileEntityNexus.getActivationProgressScaled(31);
            this.drawTexturedModalRect(j + 19, k + 29 + 31 - l, 176, 62 - l, 9, l);
        }
    }

}
