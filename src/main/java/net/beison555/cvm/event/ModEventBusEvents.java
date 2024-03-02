package net.beison555.cvm.event;

import net.beison555.cvm.CustomVehicleMod;
import net.beison555.cvm.entity.client.model.*;
import net.beison555.cvm.entity.layers.ModModelLayers;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CustomVehicleMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    /**
     * layerとmodelを紐づける
     * @param event
     */
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.TEST_VEHICLE_LAYER, TestVehicleModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.TEST_BODY_LAYER, TestBodyModel::createBodyLayer);

        event.registerLayerDefinition(ModModelLayers.TEST_FRONT_LAYER, TestFrontModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.TEST_MIDDLE_LAYER, TestMiddleModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.TEST_REAR_LAYER, TestRearModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.TEST_TIRE_LAYER, TestTireModel::createBodyLayer);
    }
}