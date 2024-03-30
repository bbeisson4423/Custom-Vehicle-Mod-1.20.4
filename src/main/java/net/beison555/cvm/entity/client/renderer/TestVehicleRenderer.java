package net.beison555.cvm.entity.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.beison555.cvm.CustomVehicleMod;
import net.beison555.cvm.entity.client.model.test.TestVehicleModel;
import net.beison555.cvm.entity.custom.base.TestVehicleEntity;
import net.beison555.cvm.entity.layers.ModModelLayers;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class TestVehicleRenderer extends EntityRenderer<TestVehicleEntity> {
    public final EntityModel<TestVehicleEntity> model;

    public TestVehicleRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.model = new TestVehicleModel<>(pContext.bakeLayer(ModModelLayers.TEST_VEHICLE_LAYER));
    }

    @Override
    /**
     * modelに紐づくテクスチャのファイルパスを取得する
     */
    public ResourceLocation getTextureLocation(TestVehicleEntity pEntity) {
        return new ResourceLocation(CustomVehicleMod.MOD_ID, "textures/entity/car_basic_texture.png");
    }

    @Override
    /**
     * PoseStackを設定してentityを描画する？
     */
    public void render(TestVehicleEntity entity, float yaw, float tickDelta, PoseStack pose, MultiBufferSource buffers, int light) {
        pose.pushPose();
        pose.mulPose(Axis.ZP.rotationDegrees(180.0F));
        pose.translate(0.0D, (double) -1.5F, 0.0D);
        pose.scale(1F, 1F, 1F);
        pose.mulPose(Axis.YP.rotationDegrees(180.0F - yaw));

        VertexConsumer ivertexbuilder = buffers.getBuffer(this.model.renderType(getTextureLocation(entity)));
        this.model.renderToBuffer(pose, ivertexbuilder, light, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        pose.popPose();
        super.render(entity, yaw, tickDelta, pose, buffers, light);
    }
}