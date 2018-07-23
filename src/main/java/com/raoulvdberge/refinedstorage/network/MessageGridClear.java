package com.raoulvdberge.refinedstorage.network;

import com.raoulvdberge.refinedstorage.api.network.grid.GridType;
import com.raoulvdberge.refinedstorage.api.network.grid.IGridNetworkAware;
import com.raoulvdberge.refinedstorage.api.network.security.Permission;
import com.raoulvdberge.refinedstorage.api.util.Action;
import com.raoulvdberge.refinedstorage.apiimpl.network.node.NetworkNodeGrid;
import com.raoulvdberge.refinedstorage.container.ContainerGrid;
import com.raoulvdberge.refinedstorage.util.StackUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

import javax.annotation.Nullable;

public class MessageGridClear extends MessageHandlerPlayerToServer<MessageGridClear> implements IMessage {
    public MessageGridClear() {
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        // NO OP
    }

    @Override
    public void toBytes(ByteBuf buf) {
        // NO OP
    }

    @Override
    public void handle(MessageGridClear message, EntityPlayerMP player) {
        Container container = player.openContainer;

        if (container instanceof ContainerGrid && ((ContainerGrid) container).getGrid() instanceof IGridNetworkAware) {
            clear((IGridNetworkAware) ((ContainerGrid) container).getGrid(), player);
        }
    }

    public static void clear(IGridNetworkAware grid, @Nullable EntityPlayer player) {
        InventoryCrafting matrix = grid.getCraftingMatrix();

        if (grid.getGridType() == GridType.CRAFTING && grid.getNetwork() != null && (player == null || grid.getNetwork().getSecurityManager().hasPermission(Permission.INSERT, player))) {
            for (int i = 0; i < matrix.getSizeInventory(); ++i) {
                ItemStack slot = matrix.getStackInSlot(i);

                if (!slot.isEmpty()) {
                    matrix.setInventorySlotContents(i, StackUtils.nullToEmpty(grid.getNetwork().insertItem(slot, slot.getCount(), Action.PERFORM)));
                }
            }
        } else if (grid.getGridType() == GridType.PATTERN) {
            ((NetworkNodeGrid) grid).clearMatrix();
        }
    }
}
