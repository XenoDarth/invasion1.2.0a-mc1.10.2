package com.invasion.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.invasion.tileentity.TileEntityNexus;

public class ContainerNexus extends Container {

	private TileEntityNexus nexus;

	public ContainerNexus(InventoryPlayer inventoryplayer, TileEntityNexus tileEntityNexus) {
		this.nexus = tileEntityNexus;
		this.addSlotToContainer(new Slot(tileEntityNexus, 0, 32, 33));
		this.addSlotToContainer(new SlotOutput(tileEntityNexus, 1, 102, 33));
		for (int i = 0; i < 3; ++i) {
			for (int k = 0; k < 9; ++k) {
				this.addSlotToContainer(new Slot(inventoryplayer, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
			}
		}
		for (int j = 0; j < 9; ++j) {
			this.addSlotToContainer(new Slot((IInventory)inventoryplayer, j, 8 + j * 18, 142));
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return this.nexus.isUseableByPlayer(entityplayer);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int i) {
		ItemStack itemstack = null;
		Slot slot = this.getSlot(i);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (i == 1 ? !this.mergeItemStack(itemstack1, 2, 38, true) : (i >= 2 && i < 29 ? !this.mergeItemStack(itemstack1, 29, 38, false) : (i >= 29 && i < 38 ? !this.mergeItemStack(itemstack1, 2, 29, false) : !this.mergeItemStack(itemstack1, 2, 38, false)))) {
				return null;
			}
			if (itemstack1.stackSize == 0) {
				slot.putStack(null);
			} else {
				slot.onSlotChanged();
			}
			if (itemstack1.stackSize != itemstack.stackSize) {
				slot.onPickupFromSlot(player, itemstack1);
			} else {
				return null;
			}
		}
		return itemstack;
	}

}
