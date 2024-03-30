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

public class ServerSpawnEntityPacket {
    public ServerSpawnEntityPacket() {
    }
    public ServerSpawnEntityPacket(FriendlyByteBuf buffer) {
    }
    public void encode(FriendlyByteBuf buffer) {
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
        BlockHitResult blockhitresult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);
        BlockPos blockpos = blockhitresult.getBlockPos();
        BlockEntity bEntity = level.getBlockEntity(blockpos);
        Direction dir = bEntity.getBlockState().getValue(MaterializationDeviceBlock.FACING);

        if(bEntity instanceof MaterializationDeviceBlockEntity){
            String type = "";
            ItemStackHandler itemStackHandler = ((MaterializationDeviceBlockEntity) bEntity).getItemHandler();
            ItemStack pStack = itemStackHandler.getStackInSlot(0);
            type = pStack.getTag().getString("cvm.type");

            switch(type){
                case "curve":
                    vEntity = ModEntities.TEST_BODY.get().create(level);
                    vEntity.setPartsFront(CodeConst.PARTS_T3_FRONT_CURVE);
                    vEntity.setPartsMiddle(CodeConst.PARTS_T3_MIDDLE_CURVE);
                    vEntity.setPartsRear(CodeConst.PARTS_T3_REAR_CURVE);
                    vEntity.setPartsTire(CodeConst.PARTS_T3_TIRE_TEST);
                    break;
                default:
                    vEntity = ModEntities.TEST_BODY.get().create(level);
                    vEntity.setPartsFront(CodeConst.PARTS_T3_FRONT_TEST);
                    vEntity.setPartsMiddle(CodeConst.PARTS_T3_MIDDLE_TEST);
                    vEntity.setPartsRear(CodeConst.PARTS_T3_REAR_TEST);
                    vEntity.setPartsTire(CodeConst.PARTS_T3_TIRE_TEST);
                    break;
            }
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

    /**
     * プレイヤーの目線の情報を取得する(バニラ参考)
     * @param level
     * @param player
     * @param fluid
     * @return
     */
    private static BlockHitResult getPlayerPOVHitResult(Level level, Player player, ClipContext.Fluid fluid) {
        float f = player.getXRot();
        float f1 = player.getYRot();
        Vec3 vec3 = player.getEyePosition();
        float f2 = Mth.cos(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f3 = Mth.sin(-f1 * ((float)Math.PI / 180F) - (float)Math.PI);
        float f4 = -Mth.cos(-f * ((float)Math.PI / 180F));
        float f5 = Mth.sin(-f * ((float)Math.PI / 180F));
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        double d0 = player.getBlockReach();
        Vec3 vec31 = vec3.add((double)f6 * d0, (double)f5 * d0, (double)f7 * d0);
        return level.clip(new ClipContext(vec3, vec31, ClipContext.Block.OUTLINE, fluid, player));
    }
}
