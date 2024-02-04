package net.beison555.cvm.item;

import net.beison555.cvm.CustomVehicleMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

/**
 * クリエイティブタブを定義
 */
public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CustomVehicleMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> COURSE_TAB = CREATIVE_MODE_TABS.register("cvm_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.VEHICLE_TABLET.get()))
                    .title(Component.translatable("creativetab.cvm_tab"))
                    .displayItems((displayParameters, output) -> {
                        output.accept(ModItems.VEHICLE_TABLET.get());
                    }).build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }

}