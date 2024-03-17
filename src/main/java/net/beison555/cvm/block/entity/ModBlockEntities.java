package net.beison555.cvm.block.entity;

import net.beison555.cvm.CustomVehicleMod;
import net.beison555.cvm.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, CustomVehicleMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<MaterializationDeviceBlockEntity>> MATERIALIZATION_DEVICE_BE =
            BLOCK_ENTITIES.register("materialization_device_block_entity", () ->
                    BlockEntityType.Builder.of(MaterializationDeviceBlockEntity::new,
                            ModBlocks.MATERIALIZATION_DEVICE.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}