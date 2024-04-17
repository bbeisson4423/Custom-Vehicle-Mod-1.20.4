package net.beison555.cvm.net;

import net.beison555.cvm.block.custom.MaterializationDeviceBlock;
import net.beison555.cvm.block.entity.MaterializationDeviceBlockEntity;
import net.beison555.cvm.entity.ModEntities;
import net.beison555.cvm.entity.custom.general.CustomVehicleEntity;
import net.beison555.cvm.util.CodeConst;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.minecraftforge.items.ItemStackHandler;

import static net.minecraft.world.level.block.Rotation.NONE;

public class ServerSpawnEntityPacket extends ServerGeneralPacket{
    public ServerSpawnEntityPacket() {
    }
    public ServerSpawnEntityPacket(FriendlyByteBuf friendlyByteBuf) {
    }

    /**
     * 車両端末の情報からエンティティをスポーンさせる
     * @param context
     */
    public void handle(CustomPayloadEvent.Context context) {
        CustomVehicleEntity vEntity = null;
        ServerPlayer player = context.getSender();
        if(player == null){
            return;
        }

        ServerLevel level = player.serverLevel();
        BlockHitResult blockhitresult = this.getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);
        BlockPos blockpos = blockhitresult.getBlockPos();
        BlockEntity bEntity = level.getBlockEntity(blockpos);
        Direction dir = bEntity.getBlockState().getValue(MaterializationDeviceBlock.FACING);

        if(bEntity instanceof MaterializationDeviceBlockEntity){
            ItemStackHandler itemStackHandler = ((MaterializationDeviceBlockEntity) bEntity).getItemHandler();
            ItemStack pStack = itemStackHandler.getStackInSlot(0);

            String front = pStack.getTag().getString("cvm.parts.front");
            String middle = pStack.getTag().getString("cvm.parts.middle");
            String rear = pStack.getTag().getString("cvm.parts.rear");
            String tire = pStack.getTag().getString("cvm.parts.tire");

            vEntity = ModEntities.TEST_BODY.get().create(level);
            vEntity.setPartsFront(front);
            vEntity.setPartsMiddle(middle);
            vEntity.setPartsRear(rear);
            vEntity.setPartsTire(tire);
        }

        // ブロックの向いている方角によってスポーン位置を設定する
        Double xPos = 0D;
        Rotation yRotation = NONE;
        float yRot = 0F;
        Double zPos = 0D;
        switch(dir){
            case NORTH:
                xPos = 1D;
                yRot = 180F;
                zPos = -3D;
                break;
            case SOUTH:
                xPos = 0D;
                yRot = 0F;
                zPos = 4D;
                break;
            case EAST:
                xPos = 4D;
                yRot = 90F;
                zPos = 1D;
                break;
            case WEST:
                xPos = -3D;
                yRot = -90F;
                zPos = 0D;
                break;
        }
        vEntity.moveTo((double)blockpos.getX() + xPos, (double)blockpos.getY(), (double)blockpos.getZ() + zPos, 0.0F, 0.0F);
        vEntity.setYRot(yRot);
        vEntity.rotate(yRotation);
        level.addFreshEntity(vEntity);

        for(ServerPlayer serverplayer : level.getEntitiesOfClass(ServerPlayer.class, vEntity.getBoundingBox().inflate(5.0D))) {
            CriteriaTriggers.SUMMONED_ENTITY.trigger(serverplayer, vEntity);
        }
    }
}
