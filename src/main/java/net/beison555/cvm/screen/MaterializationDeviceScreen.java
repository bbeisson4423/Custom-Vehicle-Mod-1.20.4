package net.beison555.cvm.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.beison555.cvm.CustomVehicleMod;
import net.beison555.cvm.net.PacketHandler;
import net.beison555.cvm.net.ServerSpawnEntityPacket;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Inventory;

public class MaterializationDeviceScreen extends AbstractContainerScreen<MaterializationDeviceMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(CustomVehicleMod.MOD_ID,"textures/gui/materialization_device_gui.png");

    public MaterializationDeviceScreen(MaterializationDeviceMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;
    }

    /**
     * クリック時動作を定義
     * @param mouseX
     * @param mouseY
     * @param mouseButton
     * @return
     */
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        // isHovering(始点X座標,始点Y座標,幅X座標,幅Y座標)
        boolean clicked = false;
        // 左クリック時動作を定義
        if(mouseButton == 0) {
            // 組み立てボタン
            if(isHovering(110, 33, 49, 18, mouseX, mouseY)) {
                clicked = true;
                // エンティティを召喚する
                PacketHandler.sendToServer(new ServerSpawnEntityPacket());
            }

            if(clicked){
                // クリック音を再生
                minecraft.getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.f));
            }
        }

        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    /**
     * GUI描画内容を定義
     * @param guiGraphics
     * @param pPartialTick
     * @param pMouseX
     * @param pMouseY
     */
    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        renderButton(guiGraphics, x, y, pMouseX, pMouseY);
        renderProgressArrow(guiGraphics, x, y);
    }

    private void renderButton(GuiGraphics guiGraphics, int x, int y, int pMouseX, int pMouseY) {
        // guiGraphics.blit(上書きX座標,上書きY座標,始点X座標,始点Y座標,幅X座標,幅Y座標)
        if(isHovering(110, 33, 49, 18, pMouseX, pMouseY)) {
            guiGraphics.blit(TEXTURE, x + 110, y + 33, 177, 27, 50, 20);
        }

    }

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting()) {
            guiGraphics.blit(TEXTURE, x + 85, y + 30, 176, 0, 8, menu.getScaledProgress());
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics,mouseX,mouseY,delta);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}