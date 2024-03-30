package net.beison555.cvm.event;

import net.beison555.cvm.CustomVehicleMod;
import net.beison555.cvm.entity.client.model.curve.CurveFrontModel;
import net.beison555.cvm.entity.client.model.curve.CurveMiddleModel;
import net.beison555.cvm.entity.client.model.curve.CurveRearModel;
import net.beison555.cvm.entity.client.model.test.*;
import net.beison555.cvm.entity.layers.ModModelLayers;
import net.beison555.cvm.net.PacketHandler;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

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

        event.registerLayerDefinition(ModModelLayers.CURVE_FRONT_LAYER, CurveFrontModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.CURVE_MIDDLE_LAYER, CurveMiddleModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.CURVE_REAR_LAYER, CurveRearModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            PacketHandler.register();
        });
    }
}