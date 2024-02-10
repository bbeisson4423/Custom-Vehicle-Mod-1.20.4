package net.beison555.cvm.entity.custom;

import net.beison555.cvm.entity.ModEntities;
import net.beison555.cvm.util.MathUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.PacketDistributor;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TestVehicleEntity extends Entity implements PlayerRideable {
    private static final EntityDataAccessor<Float> SPEED = SynchedEntityData.defineId(TestVehicleEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Boolean> STARTED = SynchedEntityData.defineId(TestVehicleEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> FORWARD = SynchedEntityData.defineId(TestVehicleEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> BACKWARD = SynchedEntityData.defineId(TestVehicleEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> LEFT = SynchedEntityData.defineId(TestVehicleEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> RIGHT = SynchedEntityData.defineId(TestVehicleEntity.class, EntityDataSerializers.BOOLEAN);

    protected float deltaRotation;

    @OnlyIn(Dist.CLIENT)
    private boolean collidedLastTick;

    public TestVehicleEntity(EntityType<? extends Entity> pEntityType, Level pLevel) {
        super(ModEntities.TEST_VEHICLE.get(), pLevel);
        this.setMaxUpStep(1f);
    }

    @Override
    protected void defineSynchedData() {
        entityData.define(STARTED, false);
        entityData.define(SPEED, 0F);
        entityData.define(FORWARD, false);
        entityData.define(BACKWARD, false);
        entityData.define(LEFT, false);
        entityData.define(RIGHT, false);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag p_20052_) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag p_20139_) {

    }

    public  float getMaxSpeed(){
        return 1.5f;
    }
    public float getMaxReverseSpeed(){
        return 0.2f;
    }
    public float getAcceleration(){
        return 0.5f;
    }
    public float getMaxRotationSpeed(){
        return 10.0f;
    }
    public float getMinRotationSpeed(){
        return 7.0f;
    }
    public float getRollResistance(){
        return 0.02f;
    }
    public float getRotationModifier(){
        return 0.5f;
    }
    public float getPitch(){
        return 3.0f;
    }

    @Override
    /**
     * 毎tickごとの処理
     */
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
//            this.setupAnimationStates();
        }

        controlCar();

        move(MoverType.SELF, getDeltaMovement());
    }

    /**
     * エンティティ操縦機能を定義
     */
    private void controlCar() {
        if (!isVehicle()) {
            setForward(false);
            setBackward(false);
            setLeft(false);
            setRight(false);
        }

        float maxSp = getMaxSpeed();
        float maxBackSp = getMaxReverseSpeed();

        float speed = MathUtils.subtractToZero(getSpeed(), getRollResistance());

        //キー入力によってエンティティ動作を制御する
        //前進・後退処理
        if((isForward() && isBackward()) || (!isForward() && !isBackward())){
            //前進・後退キーがいずれも入力or未入力の場合、speedを0に近づける
            if (speed >= 0) {
                speed = Math.max(speed - getAcceleration(), 0);
            }else{
                speed = Math.min(speed + getAcceleration(), 0);
            }
        }else{
            //前進・後退キーのいずれのみ入力の場合、前進・後退処理を行う
            if (isForward()) {
                if (speed <= maxSp) {
                    speed = Math.min(speed + getAcceleration(), maxSp);
                }
            }
            if (isBackward()) {
                if (speed >= -maxBackSp) {
                    speed = Math.max(speed - getAcceleration(), -maxBackSp);
                }
            }
        }
        setSpeed(speed);

        //旋回処理
        float rotationSpeed = 0;
        deltaRotation = 0;

        //速度が規定値以上のときにエンティティを旋回させる
        if (Math.abs(speed) > 0.02F) {
            rotationSpeed = Mth.abs(getRotationModifier() / (float) Math.pow(speed, 2));
            rotationSpeed = Mth.clamp(rotationSpeed, getMinRotationSpeed(), getMaxRotationSpeed());
        }
        if (speed < 0) {
            rotationSpeed = -rotationSpeed;
        }

        if (isLeft()) {
            deltaRotation += rotationSpeed;
        }
        if (isRight()) {
            deltaRotation -= rotationSpeed;
        }

        setYRot(getYRot() + deltaRotation);
        float delta = Math.abs(getYRot() + yRotO);
        while (getYRot() > 180F) {
            setYRot(getYRot() - 360F);
            yRotO = getYRot() - delta;
        }
        while (getYRot() <= -180F) {
            setYRot(getYRot() + 360F);
            yRotO = delta + getYRot();
        }

        //衝突判定
        if (horizontalCollision) {
            //衝突している場合、徐行状態にする
            if (level().isClientSide && !collidedLastTick) {
                onCollision(speed);
                collidedLastTick = true;
            }
        } else {
            //衝突していない場合、動力を与える
            setDeltaMovement(calculateMotionX(getSpeed(), getYRot()), getDeltaMovement().y, calculateMotionZ(getSpeed(), getYRot()));
            if (level().isClientSide) {
                collidedLastTick = false;
            }
        }
    }

    public void onCollision(float speed) {
        if (level().isClientSide) {

        }
        setSpeed(0.01F);
        setDeltaMovement(0D, getDeltaMovement().y, 0D);
    }

    public static double calculateMotionX(float speed, float rotationYaw) {
        return Mth.sin(rotationYaw * 0.017453292F) * speed;
    }
    public static double calculateMotionZ(float speed, float rotationYaw) {
        return Mth.cos(-rotationYaw * 0.017453292F) * speed;
    }

    /**
     * キー入力情報を更新する
     * @param forward
     * @param backward
     * @param left
     * @param right
     * @param player
     */
    public void updateControls(boolean forward, boolean backward, boolean left, boolean right, Player player) {
        boolean needsUpdate = false;

        //前進
        setForward(forward);
        //後退
        setBackward(backward);
        //左旋回
        setLeft(left);
        //右旋回
        setRight(right);

        if (level().isClientSide && needsUpdate) {
//            PacketDistributor.SERVER.noArg().send(new MessageControlCar(forward, backward, left, right, player));
        }
    }

    public void setSpeed(float speed) {
        this.entityData.set(SPEED, speed);
    }

    public float getSpeed() {
        return this.entityData.get(SPEED);
    }

    public void setForward(boolean forward) {
        entityData.set(FORWARD, forward);
    }

    public boolean isForward() {
        if (getDriver() == null || !canPlayerDriveCar(getDriver())) {
            return false;
        }
        return entityData.get(FORWARD);
    }

    public void setBackward(boolean backward) {
        entityData.set(BACKWARD, backward);
    }

    public boolean isBackward() {
        if (getDriver() == null || !canPlayerDriveCar(getDriver())) {
            return false;
        }
        return entityData.get(BACKWARD);
    }

    public void setLeft(boolean left) {
        entityData.set(LEFT, left);
    }

    public boolean isLeft() {
        return entityData.get(LEFT);
    }

    public void setRight(boolean right) {
        entityData.set(RIGHT, right);
    }

    public boolean isRight() {
        return entityData.get(RIGHT);
    }

    public Player getDriver() {
        List<Entity> passengers = getPassengers();
        if (passengers.size() <= 0) {
            return null;
        }

        if (passengers.get(0) instanceof Player) {
            return (Player) passengers.get(0);
        }

        return null;
    }
    public boolean canPlayerDriveCar(Player player) {
        if (player.equals(getDriver())) {
            return true;
        } else if (isInWater() || isInLava()) {
            return false;
        } else {
            return false;
        }
    }

    /**
     * プレイヤーがエンティティを殴れるかどうかを設定
     * @return
     */
    public boolean isPickable() {
        return true;
    }

    /**殴られたときの処理を定義*/
    public boolean hurt(DamageSource damageSource, float p_70097_2_) {
        if (this.isInvulnerableTo(damageSource)) {
            return false;
        } else {
            if (!this.isRemoved() && !this.level().isClientSide) {
                //エンティティを消去
                this.discard();
                this.markHurt();
                this.playSound(SoundEvents.LANTERN_BREAK, 1.0F, 1.0F);
            }

            return true;
        }
    }

    /**
     * エンティティの当たり判定
     * @return
     */
    public boolean canBeCollidedWith() {
        return true;
    }

    /**
     * エンティティの騎乗設定
     * @return
     */
    @Override
    public boolean canRiderInteract() {
        return true;
    }

    /**
     * 右クリック時の処理を定義
     * @param player
     * @param hand
     * @return
     */
    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        if (!this.level().isClientSide() && this.getPassengers().isEmpty()){
            player.startRiding(this);
            this.level().playSound((Player) null, this.blockPosition(), SoundEvents.WOOL_PLACE, SoundSource.BLOCKS, 1.0F, 1.0F);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    /**
     * 騎乗者の位置を調整する
     * @param passenger
     * @param moveFunc
     */
    @Override
    protected void positionRider(Entity passenger, MoveFunction moveFunc) {
        if (passenger == this.getFirstPassenger()) {
            // 位置を調整
            //      第一要素：騎乗者の底面の座標(X軸,Y軸,Z軸)
            //      第二要素：?
            //      第三要素：?
            //      第四要素：?
            var pos = this.position().add(0, 0.3, -0.4)
                    .add(new Vec3(0, 0, 0)
                            .xRot((float) Math.toRadians(30))
                            .zRot((float) Math.toRadians(0)));

            moveFunc.accept(passenger, pos.x, pos.y, pos.z);
        }
    }

    @Nullable
    @Override
    public LivingEntity getControllingPassenger() {
        final var firstPassenger = getFirstPassenger();

        if (firstPassenger instanceof LivingEntity living) {
            return living;
        } else {
            return null;
        }
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return super.getAddEntityPacket();
    }
}