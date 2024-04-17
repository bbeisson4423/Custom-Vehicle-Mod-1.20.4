package net.beison555.cvm.block.entity;

import net.beison555.cvm.block.custom.DesignDeviceBlock;
import net.beison555.cvm.item.ModItems;
import net.beison555.cvm.item.custom.parts.front.GeneralFrontItem;
import net.beison555.cvm.item.custom.parts.middle.GeneralMiddleItem;
import net.beison555.cvm.item.custom.parts.rear.GeneralRearItem;
import net.beison555.cvm.screen.DesignDeviceMenu;
import net.beison555.cvm.util.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class DesignDeviceBlockEntity extends BlockEntity implements MenuProvider {
    // アイテムスロットを定義する(インスタンス生成時の引数はスロット数を指定)
    private final ItemStackHandler itemHandler = new ItemStackHandler(12) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }

        // アイテムスロットの格納候補を定義
        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {


            return switch (slot) {
                case 0 -> stack.getItem() == ModItems.VEHICLE_TABLET.get();
                case 2 -> stack.getItem() instanceof GeneralFrontItem;
                case 3 -> stack.getItem() instanceof GeneralMiddleItem;
                case 4 -> stack.getItem() instanceof GeneralRearItem;
                case 5 -> stack.getItem() == ModItems.GENERAL_TIRE.get();
                default -> super.isItemValid(slot, stack);
            };
        }
    };

    private static final int INPUT_SLOT_TABLET = 0;
    private static final int INPUT_SLOT_TIRE = 5;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    private final Map<Direction, LazyOptional<WrappedHandler>> directionWrappedHandlerMap =
            new InventoryDirectionWrapper(itemHandler,
                    new InventoryDirectionEntry(Direction.DOWN, INPUT_SLOT_TABLET, false),
                    new InventoryDirectionEntry(Direction.NORTH, INPUT_SLOT_TABLET, false),
                    new InventoryDirectionEntry(Direction.SOUTH, INPUT_SLOT_TABLET, false),
                    new InventoryDirectionEntry(Direction.EAST, INPUT_SLOT_TABLET, false),
                    new InventoryDirectionEntry(Direction.WEST, INPUT_SLOT_TABLET, false),
                    new InventoryDirectionEntry(Direction.UP, INPUT_SLOT_TABLET, true)).directionsMap;

    private LazyOptional<IEnergyStorage> lazyEnergyHandler = LazyOptional.empty();
    private LazyOptional<IFluidHandler> lazyFluidHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 78;

    private final ModEnergyStorage ENERGY_STORAGE = createEnergyStorage();
    private final FluidTank FLUID_TANK = createFluidTank();

    private FluidTank createFluidTank() {
        return new FluidTank(64000) {
            @Override
            protected void onContentsChanged() {
                setChanged();
                if(!level.isClientSide()) {
                    level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
                }
            }

            @Override
            public boolean isFluidValid(FluidStack stack) {
                return true;
            }
        };
    }

    private ModEnergyStorage createEnergyStorage() {
        return new ModEnergyStorage(64000, 200) {
            @Override
            public void onEnergyChanged() {
                setChanged();
                getLevel().sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        };
    }

//    public ItemStack getRenderStack() {
//        ItemStack stack = itemHandler.getStackInSlot(OUTPUT_SLOT);
//
//        if(stack.isEmpty()) {
//            stack = itemHandler.getStackInSlot(INPUT_SLOT);
//        }
//
//        return stack;
//    }

    public DesignDeviceBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.DESIGN_DEVICE_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> DesignDeviceBlockEntity.this.progress;
                    case 1 -> DesignDeviceBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> DesignDeviceBlockEntity.this.progress = pValue;
                    case 1 -> DesignDeviceBlockEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    public IEnergyStorage getEnergyStorage() {
        return this.ENERGY_STORAGE;
    }

    public FluidStack getFluid() {
        return FLUID_TANK.getFluid();
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("container.cvm.design_device");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new DesignDeviceMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ENERGY) {
            return lazyEnergyHandler.cast();
        }

        if(cap == ForgeCapabilities.FLUID_HANDLER) {
            return lazyFluidHandler.cast();
        }

        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            if(side == null) {
                return lazyItemHandler.cast();
            }

            if(directionWrappedHandlerMap.containsKey(side)) {
                Direction localDir = this.getBlockState().getValue(DesignDeviceBlock.FACING);

                if(side == Direction.DOWN ||side == Direction.UP) {
                    return directionWrappedHandlerMap.get(side).cast();
                }

                return switch (localDir) {
                    default -> directionWrappedHandlerMap.get(side.getOpposite()).cast();
                    case EAST -> directionWrappedHandlerMap.get(side.getClockWise()).cast();
                    case SOUTH -> directionWrappedHandlerMap.get(side).cast();
                    case WEST -> directionWrappedHandlerMap.get(side.getCounterClockWise()).cast();
                };
            }
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
        lazyEnergyHandler = LazyOptional.of(() -> ENERGY_STORAGE);
        lazyFluidHandler = LazyOptional.of(() -> FLUID_TANK);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
        lazyEnergyHandler.invalidate();
        lazyFluidHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("gem_empowering_station.progress", progress);
        pTag.putInt("energy", ENERGY_STORAGE.getEnergyStored());
        pTag = FLUID_TANK.writeToNBT(pTag);

        super.saveAdditional(pTag);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        saveAdditional(tag);
        return tag;
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("gem_empowering_station.progress");
        ENERGY_STORAGE.setEnergy(pTag.getInt("energy"));
        FLUID_TANK.readFromNBT(pTag);
    }

    public void tick(Level level, BlockPos pPos, BlockState pState) {
//        if (isOutputSlotEmptyOrReceivable() && hasRecipe()) {
//            increaseCraftingProcess();
//            setChanged(level, pPos, pState);
//
//            if (hasProgressFinished()) {
//                craftItem();
//                resetProgress();
//            }
//        } else {
//            resetProgress();
//        }
    }

//    private void craftItem() {
//        this.itemHandler.extractItem(INPUT_SLOT, 1, false);
//
////        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(ModItems.ALEXANDRITE.get(),
////                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + 1));
//        this.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(Items.IRON_BARS,
//                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + 1));
//    }

    /**
     * 車両端末のクラフト可否を返す
     * @return
     */
    public boolean hasRecipe() {
        boolean result = false;
        ItemStack pStackTablet = this.itemHandler.getStackInSlot(INPUT_SLOT_TABLET);

        if(pStackTablet.getItem() == ModItems.VEHICLE_TABLET.get()
            && InputCheck.isNullOrBlank(pStackTablet.getTag())){
            // 情報が空の車両端末が存在する場合、Tierに応じたパーツがセットされているかチェックする
            ItemStack pStackTire = this.itemHandler.getStackInSlot(INPUT_SLOT_TIRE);
            if(pStackTire.getItem() == ModItems.GENERAL_TIRE.get()){
                result = true;
            }
        }

        return result;
    }

    public boolean hasVehicleTablet() {
        return this.itemHandler.getStackInSlot(INPUT_SLOT_TABLET).getItem() == ModItems.VEHICLE_TABLET.get();
    }

    public ItemStackHandler getItemHandler() {
        return this.itemHandler;
    }



//    private boolean hasRecipeItemInInputSlot() {
//        return this.itemHandler.getStackInSlot(INPUT_SLOT).getItem() == Items.IRON_ORE;
//    }
//    private boolean canInsertItemIntoOutputSlot(Item item) {
//        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item);
//    }
//    private boolean canInsertAmountIntoOutputSlot(int count) {
//        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize() >=
//                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count;
//    }
//    private boolean isOutputSlotEmptyOrReceivable() {
//        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() ||
//                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() < this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
//    }

}