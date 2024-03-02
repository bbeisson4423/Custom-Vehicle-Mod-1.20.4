package net.beison555.cvm.entity.client.renderer.bean;

import net.beison555.cvm.entity.custom.general.CustomVehicleEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class RendererPartsBean {
    // Model情報
    private EntityModel<CustomVehicleEntity> model = null;
    // 描画位置情報
    private Map<String,Double> posMap = null;
    // 軸回転率
    private Map<String,Float> rotMap = null;
    // テクスチャ情報
    private ResourceLocation rl = null;

    public RendererPartsBean() {
    }

    public EntityModel<CustomVehicleEntity> getModel() {
        return model;
    }

    public void setModel(EntityModel<CustomVehicleEntity> model) {
        this.model = model;
    }

    public Map<String, Double> getPosMap() {
        return posMap;
    }

    public void setPosMap(Map<String, Double> posMap) {
        this.posMap = posMap;
    }

    public Map<String, Float> getRotMap() {
        return rotMap;
    }

    public void setRotMap(Map<String, Float> rotMap) {
        this.rotMap = rotMap;
    }

    public ResourceLocation getRl() {
        return rl;
    }

    public void setRl(ResourceLocation rl) {
        this.rl = rl;
    }
}
