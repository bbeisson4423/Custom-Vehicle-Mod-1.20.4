package net.beison555.cvm;

import com.mojang.logging.LogUtils;
import net.beison555.cvm.block.ModBlocks;
import net.beison555.cvm.block.entity.ModBlockEntities;
import net.beison555.cvm.entity.ModEntities;
import net.beison555.cvm.entity.client.renderer.TestBodyRenderer;
import net.beison555.cvm.entity.client.renderer.TestVehicleRenderer;
import net.beison555.cvm.event.KeyEvents;
import net.beison555.cvm.item.ModCreativeModeTabs;
import net.beison555.cvm.item.ModItems;
import net.beison555.cvm.screen.MaterializationDeviceScreen;
import net.beison555.cvm.screen.ModMenuTypes;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;

/**
 * メインクラス
 */
@Mod(CustomVehicleMod.MOD_ID)
public class CustomVehicleMod {
    public static final String MOD_ID = "cvm";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static KeyMapping FORWARD_KEY;
    public static KeyMapping BACK_KEY;
    public static KeyMapping LEFT_KEY;
    public static KeyMapping RIGHT_KEY;
    public static KeyMapping CAR_GUI_KEY;
    public static KeyMapping START_KEY;
    public static KeyMapping HORN_KEY;
    public static KeyMapping CENTER_KEY;

    public CustomVehicleMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // クリエイティブタブを定義
        ModCreativeModeTabs.register(modEventBus);
        // MOD産アイテムを追加
        ModItems.register(modEventBus);
        // MOD産ブロックを追加
        ModBlocks.register(modEventBus);
        // MOD産ブロックエンティティを追加
        ModBlockEntities.register(modEventBus);
        // MOD産エンティティを追加
        ModEntities.register(modEventBus);
        // MOD産メニューを追加
        ModMenuTypes.register(modEventBus);

        // キー入力時イベントを追加
        MinecraftForge.EVENT_BUS.register(new KeyEvents());

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);

        if (FMLEnvironment.dist.isClient()) {
            modEventBus.addListener(CustomVehicleMod.this::onRegisterKeyBinds);
        }
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
            EntityRenderers.register(ModEntities.TEST_BODY.get(), TestBodyRenderer::new);
            MenuScreens.register(ModMenuTypes.MATERIALIZATION_DEVICE_MENU.get(), MaterializationDeviceScreen::new);
        }
    }

    /**
     * キーコンフィグを定義
     * @param event
     */
    @OnlyIn(Dist.CLIENT)
    public void onRegisterKeyBinds(RegisterKeyMappingsEvent event) {
        FORWARD_KEY = new KeyMapping("key.car_forward", GLFW.GLFW_KEY_W, "category.cvm");
        BACK_KEY = new KeyMapping("key.car_back", GLFW.GLFW_KEY_S, "category.cvm");
        LEFT_KEY = new KeyMapping("key.car_left", GLFW.GLFW_KEY_A, "category.cvm");
        RIGHT_KEY = new KeyMapping("key.car_right", GLFW.GLFW_KEY_D, "category.cvm");

        event.register(FORWARD_KEY);
        event.register(BACK_KEY);
        event.register(LEFT_KEY);
        event.register(RIGHT_KEY);
    }
}