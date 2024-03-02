package net.beison555.cvm.entity.client.renderer;

import net.beison555.cvm.entity.client.model.TestFrontModel;
import net.beison555.cvm.entity.client.model.TestMiddleModel;
import net.beison555.cvm.entity.client.model.TestRearModel;
import net.beison555.cvm.entity.client.model.TestTireModel;
import net.beison555.cvm.entity.client.renderer.bean.RendererBodyBean;
import net.beison555.cvm.entity.client.renderer.bean.RendererPartsBean;
import net.beison555.cvm.entity.custom.base.TestBodyEntity;
import net.beison555.cvm.entity.layers.ModModelLayers;
import net.beison555.cvm.entity.textures.ModModelTextures;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class TestBodyRenderer extends GenericRenderer {

    public TestBodyRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        RendererBodyBean rendererBodyBean = new RendererBodyBean();
        RendererPartsBean rendererPartsBean = null;

        // Front
        rendererPartsBean = new RendererPartsBean();
        rendererPartsBean.setModel(new TestFrontModel<>(pContext.bakeLayer(ModModelLayers.TEST_FRONT_LAYER)));
        rendererPartsBean.setRl(ModModelTextures.TEST_FRONT);
        Map<String,Double> frontPosMap = new HashMap<String,Double>(){
            {
                put("X",0.0D);
                put("Y",(double) 2.0F);
                put("Z",0.0D);
            }
        };
        rendererPartsBean.setPosMap(frontPosMap);
        Map<String,Float> frontRotMap = new HashMap<String,Float>(){
            {
                put("X",0.0F);
                put("Y",180.0F);
                put("Z",180.0F);
            }
        };
        rendererPartsBean.setRotMap(frontRotMap);
        rendererBodyBean.getRendererMap().put("FRONT",rendererPartsBean);

        // Middle
        rendererPartsBean = new RendererPartsBean();
        rendererPartsBean.setModel(new TestMiddleModel<>(pContext.bakeLayer(ModModelLayers.TEST_MIDDLE_LAYER)));
        rendererPartsBean.setRl(ModModelTextures.TEST_MIDDLE);
        Map<String,Double> middlePosMap = new HashMap<String,Double>(){
            {
                put("X",0.0D);
                put("Y",(double) 2.0F);
                put("Z",0.0D);
            }
        };
        rendererPartsBean.setPosMap(middlePosMap);
        Map<String,Float> middleRotMap = new HashMap<String,Float>(){
            {
                put("X",0.0F);
                put("Y",180.0F);
                put("Z",180.0F);
            }
        };
        rendererPartsBean.setRotMap(middleRotMap);
        rendererBodyBean.getRendererMap().put("MIDDLE",rendererPartsBean);

        // Rear
        rendererPartsBean = new RendererPartsBean();
        rendererPartsBean.setModel(new TestRearModel<>(pContext.bakeLayer(ModModelLayers.TEST_REAR_LAYER)));
        rendererPartsBean.setRl(ModModelTextures.TEST_REAR);
        Map<String,Double> rearPosMap = new HashMap<String,Double>(){
            {
                put("X",0.0D);
                put("Y",(double) 2.0F);
                put("Z",0.0D);
            }
        };
        rendererPartsBean.setPosMap(rearPosMap);
        Map<String,Float> rearRotMap = new HashMap<String,Float>(){
            {
                put("X",0.0F);
                put("Y",180.0F);
                put("Z",180.0F);
            }
        };
        rendererPartsBean.setRotMap(rearRotMap);
        rendererBodyBean.getRendererMap().put("REAR",rendererPartsBean);

        // Tire
        rendererPartsBean = new RendererPartsBean();
        rendererPartsBean.setModel(new TestTireModel<>(pContext.bakeLayer(ModModelLayers.TEST_TIRE_LAYER)));
        rendererPartsBean.setRl(ModModelTextures.TEST_TIRE);
        Map<String,Double> tirePosMap = new HashMap<String,Double>(){
            {
                put("X",0.0D);
                put("Y",(double) 2.0F);
                put("Z",0.0D);
            }
        };
        rendererPartsBean.setPosMap(tirePosMap);
        Map<String,Float> tireRotMap = new HashMap<String,Float>(){
            {
                put("X",0.0F);
                put("Y",180.0F);
                put("Z",180.0F);
            }
        };
        rendererPartsBean.setRotMap(tireRotMap);
        rendererBodyBean.getRendererMap().put("TIRE",rendererPartsBean);

        this.rendererBodyBean = rendererBodyBean;
    }

    @Override
    public ResourceLocation getTextureLocation(TestBodyEntity pEntity) {
        return null;
    }
}