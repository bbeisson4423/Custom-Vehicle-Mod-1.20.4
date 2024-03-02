package net.beison555.cvm.entity.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.beison555.cvm.entity.client.renderer.bean.RendererBodyBean;
import net.beison555.cvm.entity.client.renderer.bean.RendererPartsBean;
import net.beison555.cvm.entity.custom.base.TestBodyEntity;
import net.beison555.cvm.entity.custom.general.CustomVehicleEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class GenericRenderer extends EntityRenderer<TestBodyEntity> {
    public RendererBodyBean rendererBodyBean;

    public GenericRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(TestBodyEntity pEntity) {
        return null;
    }

    @Override
    /**
     * PoseStackを設定してentityを描画する
     */
    public void render(TestBodyEntity entity, float yaw, float tickDelta, PoseStack pose, MultiBufferSource buffers, int light) {
        RendererPartsBean rendererPartsBean = null;

        // Front
        rendererPartsBean = rendererBodyBean.getRendererMap().get("FRONT");
        renderParts(rendererPartsBean.getModel(), rendererPartsBean.getPosMap(), rendererPartsBean.getRotMap(), rendererPartsBean.getRl()
                , entity, yaw, tickDelta, pose, buffers, light);

        // Middle
        rendererPartsBean = rendererBodyBean.getRendererMap().get("MIDDLE");
        renderParts(rendererPartsBean.getModel(), rendererPartsBean.getPosMap(), rendererPartsBean.getRotMap(), rendererPartsBean.getRl()
                , entity, yaw, tickDelta, pose, buffers, light);

        // Rear
        rendererPartsBean = rendererBodyBean.getRendererMap().get("REAR");
        renderParts(rendererPartsBean.getModel(), rendererPartsBean.getPosMap(), rendererPartsBean.getRotMap(), rendererPartsBean.getRl()
                , entity, yaw, tickDelta, pose, buffers, light);

        // Tire
        rendererPartsBean = rendererBodyBean.getRendererMap().get("TIRE");
        renderParts(rendererPartsBean.getModel(), rendererPartsBean.getPosMap(), rendererPartsBean.getRotMap(), rendererPartsBean.getRl()
                , entity, yaw, tickDelta, pose, buffers, light);

    }

    private void renderParts(EntityModel<CustomVehicleEntity> model, Map<String,Double> posMap, Map<String,Float> rotMap, ResourceLocation rl
            , TestBodyEntity entity, float yaw, float tickDelta, PoseStack pose, MultiBufferSource buffers, int light) {
        // ※モデルの位置・回転率操作は相対的に行われる

        // PoseStack初期化
        pose.clear();
        pose.pushPose();

        // モデルのサイズ倍率
        pose.scale(1F, 1F, 1F);
        // 中心点の位置(X,Y,Z)
        pose.translate(posMap.get("X"), posMap.get("Y"), posMap.get("Z"));
        // X軸回転率
        pose.mulPose(Axis.XP.rotationDegrees(rotMap.get("X")));
        // Y軸回転率
        pose.mulPose(Axis.YP.rotationDegrees(rotMap.get("Y") + yaw));
        // Z軸回転率
        pose.mulPose(Axis.ZP.rotationDegrees(rotMap.get("Z")));

        VertexConsumer ivertexbuilder = buffers.getBuffer(model.renderType(rl));
        model.renderToBuffer(pose, ivertexbuilder, light, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

        // レンダリング処理
        pose.popPose();
        super.render(entity, yaw, tickDelta, pose, buffers, light);
    }
}