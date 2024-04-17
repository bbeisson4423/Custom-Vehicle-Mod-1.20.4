package net.beison555.cvm.datagen;

import net.beison555.cvm.CustomVehicleMod;
import net.beison555.cvm.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, CustomVehicleMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        customLamp();
    }

    private void customLamp() {
        horizontalBlock(ModBlocks.MATERIALIZATION_DEVICE.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/materialization_device")));

        horizontalBlock(ModBlocks.DESIGN_DEVICE.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/design_device")));
    }
}
