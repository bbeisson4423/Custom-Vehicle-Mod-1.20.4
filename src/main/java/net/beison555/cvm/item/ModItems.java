package net.beison555.cvm.item;

import net.beison555.cvm.CustomVehicleMod;
import net.beison555.cvm.item.custom.VehicleTabletItem;
import net.beison555.cvm.item.custom.parts.front.GeneralFrontItem;
import net.beison555.cvm.item.custom.parts.middle.GeneralMiddleItem;
import net.beison555.cvm.item.custom.parts.rear.GeneralRearItem;
import net.beison555.cvm.item.custom.parts.tire.GeneralTireItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * MOD産アイテムを追加
 */
public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CustomVehicleMod.MOD_ID);

    public static final RegistryObject<Item> VEHICLE_TABLET = ITEMS.register("vehicle_tablet",
            () -> new VehicleTabletItem(new Item.Properties()));

    public static final RegistryObject<Item> GENERAL_FRONT = ITEMS.register("general_front",
            () -> new GeneralFrontItem(new Item.Properties()));

    public static final RegistryObject<Item> GENERAL_MIDDLE = ITEMS.register("general_middle",
            () -> new GeneralMiddleItem(new Item.Properties()));

    public static final RegistryObject<Item> GENERAL_REAR = ITEMS.register("general_rear",
            () -> new GeneralRearItem(new Item.Properties()));

    public static final RegistryObject<Item> GENERAL_TIRE = ITEMS.register("general_tire",
            () -> new GeneralTireItem(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
