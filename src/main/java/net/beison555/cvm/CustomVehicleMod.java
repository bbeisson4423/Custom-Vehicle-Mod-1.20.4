package net.beison555.cvm;

import com.mojang.logging.LogUtils;
import net.beison555.cvm.entity.ModEntities;
import net.beison555.cvm.entity.client.TestVehicleRenderer;
import net.beison555.cvm.item.ModCreativeModeTabs;
import net.beison555.cvm.item.ModItems;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

/**
 * メインクラス
 */
@Mod(CustomVehicleMod.MOD_ID)
public class CustomVehicleMod {
    public static final String MOD_ID = "cvm";
    private static final Logger LOGGER = LogUtils.getLogger();

    public CustomVehicleMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // クリエイティブタブを定義
        ModCreativeModeTabs.register(modEventBus);
        // MOD産アイテムを追加
        ModItems.register(modEventBus);
        // MOD産エンティティを追加
        ModEntities.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntities.TEST_VEHICLE.get(), TestVehicleRenderer::new);
        }
    }
}