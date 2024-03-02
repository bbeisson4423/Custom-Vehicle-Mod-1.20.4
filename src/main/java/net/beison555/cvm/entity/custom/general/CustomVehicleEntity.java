package net.beison555.cvm.entity.custom.general;

import net.beison555.cvm.util.MathUtils;
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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomVehicleEntity extends Entity implements PlayerRideable {
    private static final EntityDataAccessor<Float> SPEED = SynchedEntityData.defineId(CustomVehicleEntity.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Boolean> STARTED = SynchedEntityData.defineId(CustomVehicleEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> FORWARD = SynchedEntityData.defineId(CustomVehicleEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> BACKWARD = SynchedEntityData.defineId(CustomVehicleEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> LEFT = SynchedEntityData.defineId(CustomVehicleEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> RIGHT = SynchedEntityData.defineId(CustomVehicleEntity.class, EntityDataSerializers.BOOLEAN);

    private int steps;
    private double clientX;
    private double clientY;
    private double clientZ;
    private double clientYaw;
    private double clientPitch;
    protected float deltaRotation;

    private float maxSpeed;
    private float maxReverseSpeed;
    private float acceleration;
    private float maxRotationSpeed;
    private float minRotationSpeed;
    private float rollResistance;
    private float rotationModifier;
    private float pitch;

    private double CarWidth;
    private double CarHeight;

    @OnlyIn(Dist.CLIENT)
    private boolean collidedLastTick;

    /**
     * コンストラクタ
     * @param pEntityType
     * @param pLevel
     */
    public CustomVehicleEntity(EntityType<? extends Entity> pEntityType, Level pLevel) {
//        super(ModEntities.TEST_VEHICLE.get(), pLevel);
        super(pEntityType, pLevel);
        this.blocksBuilding = true;
        this.setMaxUpStep(1f);
        recalculateBoundingBox();
    }


    //========================================【初期処理(S)】========================================
    @Override
    protected void readAdditionalSaveData(CompoundTag p_20052_) {

    }
    @Override
    protected void addAdditionalSaveData(CompoundTag p_20139_) {

    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return super.getAddEntityPacket();
    }
    //========================================【初期処理(E)】========================================


    //========================================【SynchedEntityData関連処理(S)】========================================
    @Override
    protected void defineSynchedData() {
        entityData.define(STARTED, false);
        entityData.define(SPEED, 0F);
        entityData.define(FORWARD, false);
        entityData.define(BACKWARD, false);
        entityData.define(LEFT, false);
        entityData.define(RIGHT, false);
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

    //========================================【SynchedEntityData関連処理(E)】========================================


    //========================================【毎tick処理(S)】========================================
    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
//            this.setupAnimationStates();
        }else{
            this.xo = getX();
            this.yo = getY();
            this.zo = getZ();
        }
        tickLerp();
        recalculateBoundingBox();

        //エンティティの動力情報を更新する
        controlCar();
        move(MoverType.SELF, getDeltaMovement());
    }

    private void tickLerp() {
        if (this.isControlledByLocalInstance()) {
            this.steps = 0;
            this.syncPacketPositionCodec(this.getX(), this.getY(), this.getZ());
        }

        if (this.steps > 0) {
            double d0 = getX() + (clientX - getX()) / (double) steps;
            double d1 = getY() + (clientY - getY()) / (double) steps;
            double d2 = getZ() + (clientZ - getZ()) / (double) steps;
            double d3 = Mth.wrapDegrees(clientYaw - (double) getYRot());
            setYRot((float) ((double) getYRot() + d3 / (double) steps));
            setXRot((float) ((double) getXRot() + (clientPitch - (double) getXRot()) / (double) steps));
            --steps;
            setPos(d0, d1, d2);
            setRot(getYRot(), getXRot());
        }
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void lerpTo(double x, double y, double z, float yaw, float pitch, int posRotationIncrements) {
        this.clientX = x;
        this.clientY = y;
        this.clientZ = z;
        this.clientYaw = yaw;
        this.clientPitch = pitch;
        this.steps = 10;
    }
    //========================================【毎tick処理(E)】========================================


    //========================================【エンティティ操縦関連(S)】========================================
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
//        float delta = Math.abs(getYRot() + yRotO);
//        while (getYRot() > 180F) {
//            setYRot(getYRot() - 360F);
//            yRotO = getYRot() - delta;
//
//            System.out.println("delta:" + delta);
//            System.out.println("YRot_A:" + getYRot());
//            System.out.println("YRot0_A:" + yRotO);
//        }
//        while (getYRot() <= -180F) {
//            setYRot(getYRot() + 360F);
//            yRotO = delta + getYRot();
//
//            System.out.println("delta:" + delta);
//            System.out.println("YRot_B:" + getYRot());
//            System.out.println("YRot0_B:" + yRotO);
//        }

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
    public static double calculateMotionX(float speed, float rotationYaw) {
        return Mth.sin(rotationYaw * 0.017453292F) * speed;
    }
    public static double calculateMotionZ(float speed, float rotationYaw) {
        return Mth.cos(-rotationYaw * 0.017453292F) * speed;
    }

    /**
     * 衝突時に徐行状態にする
     * @param speed
     */
    public void onCollision(float speed) {
        if (level().isClientSide) {

        }
        setSpeed(0.01F);
        setDeltaMovement(0D, getDeltaMovement().y, 0D);
    }
    //========================================【エンティティ操縦関連(E)】========================================


    //========================================【エンティティ当たり判定関連(S)】========================================
    /**
     * エンティティの当たり判定
     * @return
     */
    public boolean canBeCollidedWith() {
        return true;
    }

    /**
     * エンティティの当たり判定を再計算する
     */
    public void recalculateBoundingBox() {
        double width = getCarWidth();
        double height = getCarHeight();
        setBoundingBox(new AABB(getX() - width / 2D
                , getY()
                , getZ() - width / 2D
                , getX() + width / 2D
                , getY() + height
                , getZ() + width / 2D));
    }
    //========================================【エンティティ当たり判定関連(E)】========================================


    //========================================【エンティティ個別設定(S)】========================================
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

    //========================================【エンティティ個別設定(E)】========================================


    //========================================【キー入力関連(S)】========================================
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

    //========================================【キー入力関連(E)】========================================


    //========================================【ゲッター・セッター(S)】========================================
    public void setMaxSpeed(float maxSpeed){
        this.maxSpeed = maxSpeed;
    }
    public float getMaxSpeed(){
        return maxSpeed;
    }

    public void setMaxReverseSpeed(float maxReverseSpeed){
        this.maxReverseSpeed = maxReverseSpeed;
    }
    public float getMaxReverseSpeed(){
        return maxReverseSpeed;
    }

    public void setAcceleration(float acceleration){
        this.acceleration = acceleration;
    }
    public float getAcceleration(){
        return acceleration;
    }

    public void setMaxRotationSpeed(float maxRotationSpeed){
        this.maxRotationSpeed = maxRotationSpeed;
    }
    public float getMaxRotationSpeed(){
        return maxRotationSpeed;
    }

    public void setMinRotationSpeed(float minRotationSpeed){
        this.minRotationSpeed = minRotationSpeed;
    }
    public float getMinRotationSpeed(){
        return minRotationSpeed;
    }

    public void setRollResistance(float rollResistance){
        this.rollResistance = rollResistance;
    }
    public float getRollResistance(){
        return rollResistance;
    }

    public void setRotationModifier(float rotationModifier){
        this.rotationModifier = rotationModifier;
    }
    public float getRotationModifier(){
        return rotationModifier;
    }

    public void setPitch(float pitch){
        this.pitch = pitch;
    }
    public float getPitch(){
        return pitch;
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
     * エンティティの当たり判定を取得する
     */
    public double getCarWidth() {
        return CarWidth;
    }
    public void setCarWidth(double carWidth) {
        CarWidth = carWidth;
    }
    public double getCarHeight() {
        return CarHeight;
    }
    public void setCarHeight(double carHeight) {
        CarHeight = carHeight;
    }

    /**
     * エンティティの騎乗設定
     * @return
     */
    @Override
    public boolean canRiderInteract() {
        return true;
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
    //========================================【ゲッター・セッター(E)】========================================
}