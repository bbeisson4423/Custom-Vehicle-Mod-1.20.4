package net.beison555.cvm.screen;

import net.beison555.cvm.CustomVehicleMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, CustomVehicleMod.MOD_ID);

    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static final Supplier<MenuType<MaterializationDeviceMenu>> MATERIALIZATION_DEVICE_MENU =
            registerMenuType(MaterializationDeviceMenu::new, "materialization_device_menu");

    public static final Supplier<MenuType<DesignDeviceMenu>> DESIGN_DEVICE_MENU =
            registerMenuType(DesignDeviceMenu::new, "design_device_menu");

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}