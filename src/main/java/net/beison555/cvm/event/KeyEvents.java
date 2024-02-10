package net.beison555.cvm.event;

import net.beison555.cvm.CustomVehicleMod;
import net.beison555.cvm.entity.custom.TestVehicleEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@OnlyIn(Dist.CLIENT)
public class KeyEvents {
    public KeyEvents() {

    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.Key event) {
        Minecraft minecraft = Minecraft.getInstance();

        Player player = minecraft.player;

        if (player == null) {
            return;
        }

        Entity riding = player.getVehicle();

        if (!(riding instanceof TestVehicleEntity)) {
            return;
        }

        /**
         * エンティティ動作を制御する(キー入力があれば反映する)
         */
        TestVehicleEntity vehicle = (TestVehicleEntity) riding;
        if (player.equals(vehicle.getDriver())) {
            vehicle.updateControls(CustomVehicleMod.FORWARD_KEY.isDown(), CustomVehicleMod.BACK_KEY.isDown(), CustomVehicleMod.LEFT_KEY.isDown(), CustomVehicleMod.RIGHT_KEY.isDown(), player);
        }

    }
}
