package net.beison555.cvm.item.custom.parts.rear;

import net.beison555.cvm.util.CodeConst;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GeneralRearItem extends Item {
    public GeneralRearItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return pStack.hasTag();
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(pPlayer.getItemInHand(pUsedHand).hasTag()){
            CompoundTag cTag = pPlayer.getItemInHand(pUsedHand).getTag();
            if(CodeConst.PARTS_T3_REAR_TEST.equals(cTag.getString("cvm.parts.rear"))){
                cTag.putString("cvm.parts.rear", CodeConst.PARTS_T3_REAR_CURVE);
                pPlayer.getItemInHand(pUsedHand).setTag(cTag);
            }else{
                cTag.putString("cvm.parts.rear", CodeConst.PARTS_T3_REAR_TEST);
                pPlayer.getItemInHand(pUsedHand).setTag(cTag);
            }
        }else{
            CompoundTag cTag = new CompoundTag();
            cTag.putString("cvm.parts.rear", CodeConst.PARTS_T3_REAR_TEST);
            pPlayer.getItemInHand(pUsedHand).setTag(cTag);
        }

        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if(pStack.hasTag()){
            String currentFoundOre = pStack.getTag().getString("cvm.parts.rear");
            pTooltipComponents.add(Component.literal(currentFoundOre));
        }

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
