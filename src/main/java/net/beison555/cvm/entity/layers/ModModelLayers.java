package net.beison555.cvm.entity.layers;

import net.beison555.cvm.CustomVehicleMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {
    public static final ModelLayerLocation TEST_VEHICLE_LAYER = new ModelLayerLocation(
            new ResourceLocation(CustomVehicleMod.MOD_ID, "test_vehicle_layer"), "test_vehicle_layer");
    public static final ModelLayerLocation TEST_BODY_LAYER = new ModelLayerLocation(
            new ResourceLocation(CustomVehicleMod.MOD_ID, "test_body_layer"), "test_body_layer");

    public static final ModelLayerLocation TEST_FRONT_LAYER = new ModelLayerLocation(
            new ResourceLocation(CustomVehicleMod.MOD_ID, "test_front_layer"), "test_front_layer");
    public static final ModelLayerLocation TEST_MIDDLE_LAYER = new ModelLayerLocation(
            new ResourceLocation(CustomVehicleMod.MOD_ID, "test_middle_layer"), "test_middle_layer");
    public static final ModelLayerLocation TEST_REAR_LAYER = new ModelLayerLocation(
            new ResourceLocation(CustomVehicleMod.MOD_ID, "test_rear_layer"), "test_rear_layer");
    public static final ModelLayerLocation TEST_TIRE_LAYER = new ModelLayerLocation(
            new ResourceLocation(CustomVehicleMod.MOD_ID, "test_tire_layer"), "test_tire_layer");

    public static final ModelLayerLocation CURVE_FRONT_LAYER = new ModelLayerLocation(
            new ResourceLocation(CustomVehicleMod.MOD_ID, "curve_front_layer"), "curve_front_layer");
    public static final ModelLayerLocation CURVE_MIDDLE_LAYER = new ModelLayerLocation(
            new ResourceLocation(CustomVehicleMod.MOD_ID, "curve_middle_layer"), "curve_middle_layer");
    public static final ModelLayerLocation CURVE_REAR_LAYER = new ModelLayerLocation(
            new ResourceLocation(CustomVehicleMod.MOD_ID, "curve_rear_layer"), "curve_rear_layer");
}