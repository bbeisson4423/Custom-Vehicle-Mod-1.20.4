package net.beison555.cvm.entity.client.renderer.bean;

import net.beison555.cvm.entity.custom.general.CustomVehicleEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class RendererBodyBean {
    public Map<String, RendererPartsBean> rendererMap = new HashMap<String, RendererPartsBean>();

    public RendererBodyBean(){
    }

    public Map<String, RendererPartsBean> getRendererMap() {
        return rendererMap;
    }

    public void setRendererMap(Map<String, RendererPartsBean> rendererMap) {
        this.rendererMap = rendererMap;
    }
}
