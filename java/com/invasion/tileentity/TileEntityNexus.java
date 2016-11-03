package com.invasion.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.invasion.items.IMItems;

public class TileEntityNexus extends TileEntity implements IInventory {
	
	public static enum EnumNexusMode {
		INVASION(1), CONTINUOUS(2), ULTRA_INVASION(3);
		public final int id;
		private EnumNexusMode(int id){
			this.id = id;
		}
		public static EnumNexusMode getModeFromID(int id){
			for(EnumNexusMode mode : EnumNexusMode.values()){
				if(mode.id == id) return mode;
			}
			return null;
		}
	}
	
	private final ItemStack[] nexusItems = new ItemStack[3];
	
	//Config
	/** Maximum cook time in ticks to convert traps to rift traps. */
	public int maxCookTime = 20 * 60 * 3;
	/** Maximum time in ticks to generate rift flux in invasion mode. */
	public int maxGenerationTime = 20 * 60 * 2;
	/** Maximum time in ticks to activate the nexus. */
	public int maxActivationTime = 20 * 30;
	
	//Common fields
	private EnumNexusMode mode = null;
	private int activationTicks = 0;
	private int nexusLevel = 0;
	private int nexusKills = 0;
	private int maxHP = 100;
	private int HP = 100;
	private int cookTicks = 0;
	private int generationTicks = 0;
	private int spawnRadius = 64;
	
	//Invasion mode
	private int currentWave = 0;
	private int ticksLeftInWave = 0;
	
	//Continuous mode
	private int powerLevel = 0;
	
	public TileEntityNexus(World world){
		this.worldObj = world;
	}
	
	public EnumNexusMode getNexusMode(){
		return this.mode;
	}
	
	public int getMode(){
		return this.mode == null ? 0 : this.mode.id;
	}
	
	public int getNexusLevel(){
		return this.nexusLevel;
	}
	
	public int getNexusKills(){
		return this.nexusKills;
	}
	
	public int getSpawnRadius(){
		return this.spawnRadius;
	}
	
	public int getCurrentWave(){
		return this.currentWave;
	}
	
	public boolean isActivating(){
		return this.activationTicks > 0 && this.activationTicks < 400;
	}
	
	public int getNexusPowerLevel(){
		return this.powerLevel;
	}
	
	public int getActivationProgressScaled(int i){
		return this.activationTicks * i / 400;
	}
	
	public int getGenerationProgressScaled(int i){
		return this.generationTicks * i / 3000;
	}

	public int getCookProgressScaled(int i){
		return this.cookTicks * i / 1200;
	}
	
	//----------------------------------------------- Inventory -----------------------------------------------\\
	
	@Override
	public String getName(){
		return "nexus";
	}
	
	@Override
	public boolean hasCustomName(){
		return false;
	}
	
	@Override
	public int getSizeInventory(){
		return this.nexusItems.length;
	}
	
	@Override
	public ItemStack getStackInSlot(int index){
		return (index < 0 || index >= (this.nexusItems.length-1) ? null : this.nexusItems[index]);
	}

	@Override
	public ItemStack decrStackSize(int index, int count){
		if(index < 0 || index >= (this.nexusItems.length-1)) return null;
		return this.nexusItems[index].splitStack(count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index){
		if(index < 0 || index >= (this.nexusItems.length-1)) return null;
		ItemStack stack = this.nexusItems[index];
		this.nexusItems[index] = null;
		return stack;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack){
		if(index < 0 || index >= (this.nexusItems.length-1)) return;
		this.nexusItems[index] = stack;
	}

	@Override
	public int getInventoryStackLimit(){
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player){
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player){
		
	}

	@Override
	public void closeInventory(EntityPlayer player){
		
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack){
		if(stack == null) return false;
		if(stack.getItem() == null) return false;
		if(index < 0 || index >= (this.nexusItems.length-1)) return false;
		if(index == 0) return stack.getItem() == IMItems.itemNexusCatalyst || stack.getItem() == IMItems.itemStableNexusCatalyst;
		if(index == 1) return stack.getItem() == IMItems.itemRiftFlux || stack.getItem() == IMItems.itemIMTrap;
		return false;
	}

	@Override
	public int getField(int id){
		return 0;
	}

	@Override
	public void setField(int id, int value){
		
	}

	@Override
	public int getFieldCount(){
		return 0;
	}

	@Override
	public void clear(){
		for(int i=0; i<this.nexusItems.length; i++){
			this.nexusItems[i] = null;
		}
	}
	
}
