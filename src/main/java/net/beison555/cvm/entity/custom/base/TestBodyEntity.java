package net.beison555.cvm.entity.custom.base;

import net.beison555.cvm.entity.ModEntities;
import net.beison555.cvm.entity.custom.general.CustomVehicleEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class TestBodyEntity extends CustomVehicleEntity {

    /**
     * コンストラクタ
     * @param pEntityType
     * @param pLevel
     */
    public TestBodyEntity(EntityType<? extends Entity> pEntityType, Level pLevel) {
        super(ModEntities.TEST_BODY.get(), pLevel);
        setParameter();
    }

    //========================================【パラメータ設定(S)】========================================
    private void setParameter(){
        setMaxSpeed(1.5f);
        setMaxReverseSpeed(0.5f);
        setAcceleration(0.5f);
        setMaxRotationSpeed(7.0f);
        setMinRotationSpeed(5.0f);
        setRollResistance(0.02f);
        setRotationModifier(0.5f);
        setPitch(3.0f);

        setCarWidth(4D);
        setCarHeight(2D);
    }
    //========================================【パラメータ設定(E)】========================================

    //========================================【エンティティ個別設定(S)】========================================
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
            var pos = this.position().add(0, 0.1, 0.2)
                    .add(new Vec3(0, 0, 0)
                            .xRot((float) Math.toRadians(30))
                            .zRot((float) Math.toRadians(0)));

            moveFunc.accept(passenger, pos.x, pos.y, pos.z);
        }
    }
    //========================================【エンティティ個別設定(E)】========================================

}