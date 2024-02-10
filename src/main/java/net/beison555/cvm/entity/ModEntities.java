package net.beison555.cvm.entity;

import net.beison555.cvm.CustomVehicleMod;
import net.beison555.cvm.entity.custom.TestVehicleEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, CustomVehicleMod.MOD_ID);

    // sized(横サイズ,縦サイズ)
    public static final RegistryObject<EntityType<TestVehicleEntity>> TEST_VEHICLE =
            ENTITY_TYPES.register("test_vehicle", () -> EntityType.Builder.of(TestVehicleEntity::new, MobCategory.CREATURE)
                    .sized(4f, 2f).build("test_vehicle"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}