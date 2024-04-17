package net.beison555.cvm.datagen;

import net.beison555.cvm.CustomVehicleMod;
import net.beison555.cvm.block.ModBlocks;
import net.beison555.cvm.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CustomVehicleMod.MOD_ID, existingFileHelper);
    }

    @Override
    /**
     * アイテム追加用プロバイダー
     */
    protected void registerModels() {
        simpleItem(ModItems.VEHICLE_TABLET);

        complexBlock(ModBlocks.MATERIALIZATION_DEVICE.get());
        complexBlock(ModBlocks.DESIGN_DEVICE.get());
    }

    private ItemModelBuilder saplingItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(CustomVehicleMod.MOD_ID,"block/" + item.getId().getPath()));
    }

    private ItemModelBuilder complexBlock(Block block) {
        return withExistingParent(ForgeRegistries.BLOCKS.getKey(block).getPath(), new ResourceLocation(CustomVehicleMod.MOD_ID,
                "block/" + ForgeRegistries.BLOCKS.getKey(block).getPath()));
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(CustomVehicleMod.MOD_ID,"item/" + item.getId().getPath()));
    }

    public void fenceItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock){
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture", new ResourceLocation(CustomVehicleMod.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void wallItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock){
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall", new ResourceLocation(CustomVehicleMod.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void buttonItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock){
            this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                    .texture("texture", new ResourceLocation(CustomVehicleMod.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public ItemModelBuilder simpleBlockItem(RegistryObject<Block> item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(CustomVehicleMod.MOD_ID, "item/" + item.getId().getPath()));
    }

    public ItemModelBuilder simpleItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(CustomVehicleMod.MOD_ID, "item/" + item.getId().getPath()));
    }
}
