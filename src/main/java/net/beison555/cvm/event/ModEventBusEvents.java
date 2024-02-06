package net.beison555.cvm.event;

import net.beison555.cvm.CustomVehicleMod;
import net.beison555.cvm.entity.client.TestVehicleModel;
import net.beison555.cvm.entity.layers.ModModelLayers;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CustomVehicleMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.TEST_VEHICLE_LAYER, TestVehicleModel::createBodyLayer);
    }
}