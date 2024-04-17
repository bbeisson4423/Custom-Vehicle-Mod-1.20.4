package net.beison555.cvm.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.beison555.cvm.CustomVehicleMod;
import net.beison555.cvm.net.PacketHandler;
import net.beison555.cvm.net.ServerDesignTabletPacket;
import net.beison555.cvm.net.ServerSpawnEntityPacket;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.Level;

public class DesignDeviceScreen extends AbstractContainerScreen<DesignDeviceMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(CustomVehicleMod.MOD_ID,"textures/gui/design_device_gui.png");
    private static final int FONT_COLOR = 4210752;

    private Level level;
    private Button buttonSpawn;

    public DesignDeviceScreen(DesignDeviceMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        this.imageWidth = 176;
        this.imageHeight = 187;

        super.init();
        this.inventoryLabelY = 10000;
        this.titleLabelY = 10000;
        this.level = getMenu().getLevel();

        // ボタン定義を追加
        // 実体化ボタン
        buttonSpawn = addRenderableWidget(Button.builder(Component.translatable("button.cvm.design_device.generate_tablet"), button -> {
            if (level.isClientSide) {
                // クリック音を再生
                minecraft.getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.f));
                // 車両端末に情報を追加する
                PacketHandler.sendToServer(new ServerDesignTabletPacket());
            }
        }).bounds(leftPos + 116, topPos + 76, 49, 18).build());
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        super.renderLabels(guiGraphics, mouseX, mouseY);

        // タイトル
        guiGraphics.drawString(font, getMenu().getBlockEntity().getDisplayName().getVisualOrderText(), 8, 6, FONT_COLOR, false);
        guiGraphics.drawString(font, playerInventoryTitle.getVisualOrderText(), 8, imageHeight - 96 + 2, FONT_COLOR, false);

        // 車両展開ボタン
        boolean canGenerateTablet = getMenu().getBlockEntity().hasRecipe();
        if(canGenerateTablet){
            // ボタンを活性化
            buttonSpawn.active = true;
        }else{
            buttonSpawn.active = false;
        }

        RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
    }

//    /**
//     * クリック時動作を定義
//     * @param mouseX
//     * @param mouseY
//     * @param mouseButton
//     * @return
//     */
//    @Override
//    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
//        // isHovering(始点X座標,始点Y座標,幅X座標,幅Y座標)
//        boolean clicked = false;
//        // 左クリック時動作を定義
//        if(mouseButton == 0) {
//        }
//
//        return super.mouseClicked(mouseX, mouseY, mouseButton);
//    }

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

        renderProgressArrow(guiGraphics, x, y);
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