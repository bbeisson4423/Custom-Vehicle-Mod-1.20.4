package net.beison555.cvm.net;

import net.beison555.cvm.block.entity.DesignDeviceBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.minecraftforge.items.ItemStackHandler;

public class ServerDesignTabletPacket extends ServerGeneralPacket{
    public ServerDesignTabletPacket() {
    }
    public ServerDesignTabletPacket(FriendlyByteBuf friendlyByteBuf) {
    }

    /**
     * 車両端末に情報を追加する
     * @param context
     */
    public void handle(CustomPayloadEvent.Context context) {
        ServerPlayer player = context.getSender();
        if(player == null){
            return;
        }

        ServerLevel level = player.serverLevel();
        BlockHitResult blockhitresult = this.getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);
        BlockPos blockpos = blockhitresult.getBlockPos();
        BlockEntity bEntity = level.getBlockEntity(blockpos);

        if(bEntity instanceof DesignDeviceBlockEntity){
            ItemStack initStack = new ItemStack(Items.AIR);
            ItemStackHandler itemStackHandler = ((DesignDeviceBlockEntity) bEntity).getItemHandler();

            // 車両端末に部品情報を埋め込む
            CompoundTag cTag = new CompoundTag();
            cTag.putString("cvm.parts.front", itemStackHandler.getStackInSlot(2).getTag().getString("cvm.parts.front"));
            cTag.putString("cvm.parts.middle", itemStackHandler.getStackInSlot(3).getTag().getString("cvm.parts.middle"));
            cTag.putString("cvm.parts.rear", itemStackHandler.getStackInSlot(4).getTag().getString("cvm.parts.rear"));
            cTag.putString("cvm.parts.tire", itemStackHandler.getStackInSlot(5).getTag().getString("cvm.parts.tire"));

            itemStackHandler.getStackInSlot(0).setTag(cTag);
            itemStackHandler.setStackInSlot(1,initStack);
            itemStackHandler.setStackInSlot(2,initStack);
            itemStackHandler.setStackInSlot(3,initStack);
            itemStackHandler.setStackInSlot(4,initStack);
            itemStackHandler.setStackInSlot(5,initStack);
            itemStackHandler.setStackInSlot(6,initStack);
            itemStackHandler.setStackInSlot(7,initStack);
            itemStackHandler.setStackInSlot(8,initStack);
            itemStackHandler.setStackInSlot(9,initStack);
            itemStackHandler.setStackInSlot(10,initStack);
        }
    }
}
