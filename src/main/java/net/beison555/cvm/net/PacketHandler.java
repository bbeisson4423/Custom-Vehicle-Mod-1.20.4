package net.beison555.cvm.net;

import net.beison555.cvm.CustomVehicleMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.SimpleChannel;

public class PacketHandler {
    /**
     * クライアント・サーバー間でパケットを受け渡す
     */
    private static final SimpleChannel INSTANCE = ChannelBuilder.named(
                    new ResourceLocation(CustomVehicleMod.MOD_ID, "main"))
            .serverAcceptedVersions((status, version) -> true)
            .clientAcceptedVersions((status, version) -> true)
            .networkProtocolVersion(1)
            .simpleChannel();

    public static void register() {
        INSTANCE.messageBuilder(ServerSpawnEntityPacket.class, NetworkDirection.PLAY_TO_SERVER)
                .encoder(ServerSpawnEntityPacket::encode)
                .decoder(ServerSpawnEntityPacket::new)
                .consumerMainThread(ServerSpawnEntityPacket::handle)
                .add();
    }

    public static void sendToServer(Object msg) {
        INSTANCE.send(msg, PacketDistributor.SERVER.noArg());
    }

    public static void sendToPlayer(Object msg, ServerPlayer player) {
        INSTANCE.send(msg, PacketDistributor.PLAYER.with(player));
    }

    public static void sendToAllClients(Object msg) {
        INSTANCE.send(msg, PacketDistributor.ALL.noArg());
    }
}
