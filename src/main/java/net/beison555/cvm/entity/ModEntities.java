package net.beison555.cvm.entity;

import net.beison555.cvm.CustomVehicleMod;
import net.beison555.cvm.entity.custom.base.TestBodyEntity;
import net.beison555.cvm.entity.custom.base.TestVehicleEntity;
import net.minecraft.core.NonNullList;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_FR =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, CustomVehicleMod.MOD_ID);

    // sized(横サイズ,縦サイズ)
    public static final RegistryObject<EntityType<TestVehicleEntity>> TEST_VEHICLE =
            ENTITY_FR.register("test_vehicle", () -> EntityType.Builder.of(TestVehicleEntity::new, MobCategory.CREATURE)
                    .sized(4f, 2f).build("test_vehicle"));

    public static final RegistryObject<EntityType<TestBodyEntity>> TEST_BODY =
            ENTITY_FR.register("test_body", () -> EntityType.Builder.of(TestBodyEntity::new, MobCategory.CREATURE)
                    .sized(1f, 1f).build("test_body"));

    public static void register(IEventBus eventBus) {
        ENTITY_FR.register(eventBus);
    }
}