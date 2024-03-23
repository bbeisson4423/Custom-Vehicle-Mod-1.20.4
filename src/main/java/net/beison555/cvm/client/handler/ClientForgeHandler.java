package net.beison555.cvm.client.handler;

import net.beison555.cvm.CustomVehicleMod;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CustomVehicleMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientForgeHandler {
    /**
     * クライアント側の操作をサーバー側で検知する
     * @param event
     */
    @SubscribeEvent
        public static void clientTick(TickEvent.ClientTickEvent event) {
            // 未定義
        }
}
