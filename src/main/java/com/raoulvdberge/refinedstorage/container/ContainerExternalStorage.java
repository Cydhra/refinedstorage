package com.raoulvdberge.refinedstorage.container;

import com.raoulvdberge.refinedstorage.container.slot.SlotFilterItemOrFluid;
import com.raoulvdberge.refinedstorage.tile.TileExternalStorage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerExternalStorage extends ContainerBase {
    public ContainerExternalStorage(TileExternalStorage externalStorage, EntityPlayer player) {
        super(externalStorage, player);

        for (int i = 0; i < 9; ++i) {
            addSlotToContainer(new SlotFilterItemOrFluid(externalStorage.getNode(), i, 8 + (18 * i), 20));
        }

        addPlayerInventory(8, 141);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        Slot slot = getSlot(index);

        if (slot.getHasStack() && index >= 8) {
            return mergeItemStackToFilters(slot.getStack(), 0, 9);
        }

        return ItemStack.EMPTY;
    }
}
