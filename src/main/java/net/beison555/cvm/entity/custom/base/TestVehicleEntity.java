package net.beison555.cvm.entity.custom.base;

import net.beison555.cvm.entity.ModEntities;
import net.beison555.cvm.entity.custom.general.CustomVehicleEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class TestVehicleEntity extends CustomVehicleEntity {

    /**
     * コンストラクタ
     * @param pEntityType
     * @param pLevel
     */
    public TestVehicleEntity(EntityType<? extends Entity> pEntityType, Level pLevel) {
        super(ModEntities.TEST_VEHICLE.get(), pLevel);
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

}