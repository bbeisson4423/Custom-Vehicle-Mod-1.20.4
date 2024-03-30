package net.beison555.cvm.entity.client.renderer;

import net.beison555.cvm.entity.client.model.test.TestTireModel;
import net.beison555.cvm.entity.client.renderer.bean.RendererBodyBean;
import net.beison555.cvm.entity.client.renderer.bean.RendererPartsBean;
import net.beison555.cvm.entity.custom.general.CustomVehicleEntity;
import net.beison555.cvm.entity.layers.ModModelLayers;
import net.beison555.cvm.entity.textures.ModModelTextures;
import net.beison555.cvm.util.CodeConst;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import java.util.HashMap;
import java.util.Map;

public class TestBodyRenderer extends GenericRenderer {
    private Map<String,Double> frontPosMap = null;
    private Map<String,Double> middlePosMap = null;
    private Map<String,Double> rearPosMap = null;
    private Map<String,Double> tirePosMap = null;

    private Map<String,Float> frontRotMap = null;
    private Map<String,Float> middleRotMap = null;
    private Map<String,Float> rearRotMap = null;
    private Map<String,Float> tireRotMap = null;


    public TestBodyRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        rendererBodyBean = new RendererBodyBean();
        initMap();

        // Front
        setFrontModel(pContext);
        // Middle
        setMiddleModel(pContext);
        // Rear
        setRearModel(pContext);
        // Tire
        setTireModel(pContext);
    }

    private void initMap(){
        frontPosMap = new HashMap<String,Double>(){
            {
                put("X",0.0D);
                put("Y",(double) 2.0F);
                put("Z",0.0D);
            }
        };
        middlePosMap = new HashMap<String,Double>(){
            {
                put("X",0.0D);
                put("Y",(double) 2.0F);
                put("Z",0.0D);
            }
        };
        rearPosMap = new HashMap<String,Double>(){
            {
                put("X",0.0D);
                put("Y",(double) 2.0F);
                put("Z",0.0D);
            }
        };
        tirePosMap = new HashMap<String,Double>(){
            {
                put("X",0.0D);
                put("Y",(double) 2.0F);
                put("Z",0.0D);
            }
        };
        frontRotMap = new HashMap<String,Float>(){
            {
                put("X",0.0F);
                put("Y",180.0F);
                put("Z",180.0F);
            }
        };
        middleRotMap = new HashMap<String,Float>(){
            {
                put("X",0.0F);
                put("Y",180.0F);
                put("Z",180.0F);
            }
        };
        rearRotMap = new HashMap<String,Float>(){
            {
                put("X",0.0F);
                put("Y",180.0F);
                put("Z",180.0F);
            }
        };
        tireRotMap = new HashMap<String,Float>(){
            {
                put("X",0.0F);
                put("Y",180.0F);
                put("Z",180.0F);
            }
        };
    }

    private void setFrontModel(EntityRendererProvider.Context pContext){
        // Test
        setCommonModel(new TestTireModel<>(pContext.bakeLayer(ModModelLayers.TEST_FRONT_LAYER)), ModModelTextures.TEST_FRONT
                , CodeConst.PARTS_T3_FRONT_TEST, frontPosMap, frontRotMap);
        // Curve
        setCommonModel(new TestTireModel<>(pContext.bakeLayer(ModModelLayers.CURVE_FRONT_LAYER)), ModModelTextures.CURVE_FRONT
                , CodeConst.PARTS_T3_FRONT_CURVE, frontPosMap, frontRotMap);
    }

    private void setMiddleModel(EntityRendererProvider.Context pContext){
        // Test
        setCommonModel(new TestTireModel<>(pContext.bakeLayer(ModModelLayers.TEST_MIDDLE_LAYER)), ModModelTextures.TEST_MIDDLE
                , CodeConst.PARTS_T3_MIDDLE_TEST, middlePosMap, middleRotMap);
        // Curve
        setCommonModel(new TestTireModel<>(pContext.bakeLayer(ModModelLayers.CURVE_MIDDLE_LAYER)), ModModelTextures.CURVE_MIDDLE
                , CodeConst.PARTS_T3_MIDDLE_CURVE, middlePosMap, middleRotMap);
    }

    private void setRearModel(EntityRendererProvider.Context pContext){
        // Test
        setCommonModel(new TestTireModel<>(pContext.bakeLayer(ModModelLayers.TEST_REAR_LAYER)), ModModelTextures.TEST_REAR
                , CodeConst.PARTS_T3_REAR_TEST, rearPosMap, rearRotMap);
        // Curve
        setCommonModel(new TestTireModel<>(pContext.bakeLayer(ModModelLayers.CURVE_REAR_LAYER)), ModModelTextures.CURVE_REAR
                , CodeConst.PARTS_T3_REAR_CURVE, rearPosMap, rearRotMap);
    }

    private void setTireModel(EntityRendererProvider.Context pContext){
        // Test
        setCommonModel(new TestTireModel<>(pContext.bakeLayer(ModModelLayers.TEST_TIRE_LAYER)), ModModelTextures.TEST_TIRE
                , CodeConst.PARTS_T3_TIRE_TEST, tirePosMap, tireRotMap);
    }

    private void setCommonModel(EntityModel<CustomVehicleEntity> model,ResourceLocation rl, String id
            , Map<String,Double> posMap, Map<String,Float> rotMap){
        RendererPartsBean rendererPartsBean = new RendererPartsBean();
        rendererPartsBean.setModel(model);
        rendererPartsBean.setRl(rl);
        rendererPartsBean.setPosMap(posMap);
        rendererPartsBean.setRotMap(rotMap);

        rendererBodyBean.getRendererMap().put(id,rendererPartsBean);
    }

    @Override
    public ResourceLocation getTextureLocation(CustomVehicleEntity pEntity) {
        return null;
    }
}