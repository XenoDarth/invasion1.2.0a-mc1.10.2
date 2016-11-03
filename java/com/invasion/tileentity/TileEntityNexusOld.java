package com.invasion.tileentity;

import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;

public class TileEntityNexusOld {//extends TileEntity implements IInventory {
	/*
	private final ItemStack[] nexusItems;

	private static final long BIND_EXPIRE_TIME = 300000;
	//private IMWaveSpawner waveSpawner;
	//private IMWaveBuilder waveBuilder;
	private ItemStack[] nexusItemStacks;
	/** Appears to be the actual spawn range. Also binds players within this range to the nexus on activation. Not sure why this is being used, though, when there's a spawnRadius field. */
/*	private AxisAlignedBB boundingBoxToRadius;
	private HashMap<String, Long> boundPlayers;
	//private List<EntityIMLiving> mobList;
	//private AttackerAI attackerAI;
	private int activationTimer;
	private int currentWave;
	private int spawnRadius;
	private int nexusLevel;
	private int nexusKills;
	private int generation;
	private int cookTime;
	private int maxHp;
	private int hp;
	private int lastHp;
	private int mode;
	private int powerLevel;
	private int lastPowerLevel;
	private int powerLevelTimer;
	private int mobsLeftInWave;
	private int lastMobsLeftInWave;
	private int mobsToKillInWave;
	private int nextAttackTime;
	private int daysToAttack;
	private long lastWorldTime;
	private int zapTimer;
	private int errorState;
	private int tickCount;
	private int cleanupTimer;
	private long spawnerElapsedRestore;
	private long timer;
	private long waveDelayTimer;
	private long waveDelay;
	private boolean continuousAttack;
	private boolean mobsSorted;
	private boolean resumedFromNBT;
	private boolean activated;
	
	public TileEntityNexusOld(World world) {
		this.nexusItems = new ItemStack[2];
		this.worldObj = world;
		this.spawnRadius = 52;
		//this.waveSpawner = new IMWaveSpawner(this, this.spawnRadius);
		//this.waveBuilder = new IMWaveBuilder();
		this.nexusItemStacks = new ItemStack[2];
		//this.boundingBoxToRadius = AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e);
		//this.boundingBoxToRadius.func_72324_b((double)(this.field_145851_c - (this.spawnRadius + 10)), (double)(this.field_145848_d - (this.spawnRadius + 40)), (double)(this.field_145849_e - (this.spawnRadius + 10)), (double)(this.field_145851_c + (this.spawnRadius + 10)), (double)(this.field_145848_d + (this.spawnRadius + 40)), (double)(this.field_145849_e + (this.spawnRadius + 10)));
		this.boundPlayers = new HashMap();
		//this.mobList = new ArrayList<EntityIMLiving>();
		//this.attackerAI = new AttackerAI(this);
		this.activationTimer = 0;
		this.cookTime = 0;
		this.currentWave = 0;
		this.nexusLevel = 1;
		this.nexusKills = 0;
		this.generation = 0;
		this.lastHp = 100;
		this.hp = 100;
		this.maxHp = 100;
		this.mode = 0;
		this.powerLevelTimer = 0;
		this.powerLevel = 0;
		this.lastPowerLevel = 0;
		this.mobsLeftInWave = 0;
		this.nextAttackTime = 0;
		this.daysToAttack = 0;
		this.lastWorldTime = 0;
		this.errorState = 0;
		this.tickCount = 0;
		this.timer = 0;
		this.zapTimer = 0;
		this.cleanupTimer = 0;
		this.waveDelayTimer = -1;
		this.waveDelay = 0;
		this.continuousAttack = false;
		this.mobsSorted = false;
		this.resumedFromNBT = false;
		this.activated = false;
	}
	
	/*public void update() {
		if (this.worldObj.isRemote) return;
		this.updateStatus();
		this.updateAI();
		if (this.mode == 1 || this.mode == 2 || this.mode == 3) {
			if (this.resumedFromNBT) {
				//this.boundingBoxToRadius.func_72324_b((double)(this.field_145851_c - (this.spawnRadius + 10)), 0.0, (double)(this.field_145849_e - (this.spawnRadius + 10)), (double)(this.field_145851_c + (this.spawnRadius + 10)), 127.0, (double)(this.field_145849_e + (this.spawnRadius + 10)));
				if (this.mode == 2 && this.continuousAttack) {
					if (this.resumeSpawnerContinuous()) {
						this.mobsLeftInWave = this.lastMobsLeftInWave += this.acquireEntities();
						MainRegistry.log("mobsLeftInWave: " + this.mobsLeftInWave);
						MainRegistry.log("mobsToKillInWave: " + this.mobsToKillInWave);
					}
				} else {
					this.resumeSpawnerInvasion();
					this.acquireEntities();
				}
				this.attackerAI.onResume();
				this.resumedFromNBT = false;
			}
			try {
				++this.tickCount;
				if (this.tickCount == 60) {
					this.tickCount -= 60;
					this.bindPlayers();
					this.updateMobList();
				}
				if (this.mode == 1 || this.mode == 3) {
					this.doInvasion(50);
				} else if (this.mode == 2) {
					this.doContinuous(50);
				}
			}
			catch (WaveSpawnerException e) {
				MainRegistry.log(e.getMessage());
				e.printStackTrace();
				this.stop();
			}
		}
		if (this.cleanupTimer++ > 40) {
			this.cleanupTimer = 0;
			if (this.worldObj.getBlockState(this.pos) != BlocksAndItems.blockNexus) {
				MainRegistry.setInvasionEnded(this);
				this.stop();
				MainRegistry.log("Stranded nexus entity trying to delete itself...");
				try {
					this.finalize();
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		}
	}*/
/*
	public void emergencyStop() {
		MainRegistry.log("Nexus manually stopped by command");
		this.stop();
		this.killAllMobs();
	}

	public void debugStatus() {
		MainRegistry.sendMessageToPlayers(this.getBoundPlayers(), "Current Time: " + this.worldObj.getWorldTime());
		MainRegistry.sendMessageToPlayers(this.getBoundPlayers(), "Time to next: " + this.nextAttackTime);
		MainRegistry.sendMessageToPlayers(this.getBoundPlayers(), "Days to attack: " + this.daysToAttack);
		MainRegistry.sendMessageToPlayers(this.getBoundPlayers(), "Mobs left: " + this.mobsLeftInWave);
		MainRegistry.sendMessageToPlayers(this.getBoundPlayers(), "Mode: " + this.mode);
	}

	public void debugStartInvaion(int startWave) {
		MainRegistry.tryGetInvasionPermission(this);
		this.startInvasion(startWave);
		this.activated = true;
	}

	public void createBolt(int x, int y, int z, int t) {
		EntityIMBolt bolt = new EntityIMBolt(this.worldObj, (double)this.pos.getX() + 0.5, (double)this.pos.getY() + 0.5, (double)this.pos.getZ() + 0.5, (double)x + 0.5, (double)y + 0.5, (double)z + 0.5, t, 1);
		this.worldObj.spawnEntityInWorld(bolt);
	}

	public boolean setSpawnRadius(int radius) {
		if (!this.waveSpawner.isActive() && radius > 8) {
			this.spawnRadius = radius;
			this.waveSpawner.setRadius(radius);
			//this.boundingBoxToRadius.func_72324_b((double)(this.field_145851_c - (this.spawnRadius + 10)), 0.0, (double)(this.field_145849_e - (this.spawnRadius + 10)), (double)(this.field_145851_c + (this.spawnRadius + 10)), 127.0, (double)(this.field_145849_e + (this.spawnRadius + 10)));
			return true;
		}
		return false;
	}
	
	public void attackNexus(int damage) {
		this.hp -= damage;
		if (this.hp <= 0) {
			this.hp = 0;
			if (this.mode == 1) {
				this.theEnd();
			}
		}
		while (this.hp + 5 <= this.lastHp) {
			MainRegistry.sendMessageToPlayers(this.getBoundPlayers(), "Nexus at " + (this.lastHp - 5) + " hp");
			this.lastHp -= 5;
		}
	}
	
	public void registerMobDied() {
		this.nexusKills++;
		this.mobsLeftInWave--;
		if (this.mobsLeftInWave <= 0) {
			if (this.lastMobsLeftInWave > 0) {
				MainRegistry.sendMessageToPlayers(this.getBoundPlayers(), "Nexus rift stable again!");
				MainRegistry.sendMessageToPlayers(this.getBoundPlayers(), "Unleashing tapped energy...");
				this.lastMobsLeftInWave = this.mobsLeftInWave;
			}
			return;
		}
		while ((float)this.mobsLeftInWave + (float)this.mobsToKillInWave * 0.1f <= (float)this.lastMobsLeftInWave) {
			MainRegistry.sendMessageToPlayers(this.getBoundPlayers(), "Nexus rift stabilised to " + (100 - 100 * this.mobsLeftInWave / this.mobsToKillInWave) + "%");
			this.lastMobsLeftInWave = (int)((float)this.lastMobsLeftInWave - (float)this.mobsToKillInWave * 0.1f);
		}
	}
	
	public boolean isActivating() {
		return this.activationTimer > 0 && this.activationTimer < 400;
	}
	
	public int getMode() {
		return this.mode;
	}
	
	public int getActivationTimer() {
		return this.activationTimer;
	}
	
	public int getSpawnRadius() {
		return this.spawnRadius;
	}
	
	public int getNexusKills() {
		return this.nexusKills;
	}
	
	public int getGeneration() {
		return this.generation;
	}
	
	public int getNexusLevel() {
		return this.nexusLevel;
	}
	
	public int getPowerLevel() {
		return this.powerLevel;
	}
	
	public int getCookTime() {
		return this.cookTime;
	}
	
	@Override
	public World getWorld() {
		return this.worldObj;
	}
	
	/*public List<EntityIMLiving> getMobList() {
		return this.mobList;
	}*/
/*	
	public int getActivationProgressScaled(int i){
		return this.activationTimer * i / 400;
	}
	
	public int getGenerationProgressScaled(int i){
		return this.generation * i / 3000;
	}
	
	public int getCookProgressScaled(int i){
		return this.cookTime * i / 1200;
	}
	
	public int getNexusPowerLevel(){
		return this.powerLevel;
	}
	
	public int getCurrentWave(){
		return this.currentWave;
	}
	
	public void setActivationTimer(int i){
		this.activationTimer = i;
	}
	
	public void setNexusLevel(int i){
		this.nexusLevel = i;
	}

	public void setNexusKills(int i){
		this.nexusKills = i;
	}
	
	public void setGeneration(int i){
		this.generation = i;
	}
	
	public void setNexusPowerLevel(int i){
		this.powerLevel = i;
	}

	public void setCookTime(int i){
		this.cookTime = i;
	}

	public void setWave(int wave){
		this.currentWave = wave;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		int i;
		//MainRegistry.log("Restoring TileEntityNexus from NBT");
		super.readFromNBT(nbttagcompound);
		NBTTagList nbttaglist = nbttagcompound.getTagList("Items", 0);
		this.nexusItemStacks = new ItemStack[2/*this.func_70302_i_()*///];
/*		for (i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.get(i);
			byte byte0 = nbttagcompound1.getByte("Slot");
			if (byte0 < 0 || byte0 >= this.nexusItemStacks.length) continue;
			this.nexusItemStacks[byte0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
		}
		nbttaglist = nbttagcompound.getTagList("boundPlayers", 0);
		for (i = 0; i < nbttaglist.tagCount(); ++i) {
			if(nbttaglist.get(i) instanceof NBTTagCompound);
			NBTTagCompound tag1 = (NBTTagCompound)nbttaglist.get(i);
			this.boundPlayers.put(tag1.getString("name"), System.currentTimeMillis());
			//MainRegistry.log("Added bound player: " + tag1.getString("name"));
		}
		this.activationTimer = nbttagcompound.getShort("activationTimer");
		this.mode = nbttagcompound.getInteger("mode");
		this.currentWave = nbttagcompound.getShort("currentWave");
		this.spawnRadius = nbttagcompound.getShort("spawnRadius");
		this.nexusLevel = nbttagcompound.getShort("nexusLevel");
		this.hp = nbttagcompound.getShort("hp");
		this.nexusKills = nbttagcompound.getInteger("nexusKills");
		this.generation = nbttagcompound.getShort("generation");
		this.powerLevel = nbttagcompound.getInteger("powerLevel");
		this.lastPowerLevel = nbttagcompound.getInteger("lastPowerLevel");
		this.nextAttackTime = nbttagcompound.getInteger("nextAttackTime");
		this.daysToAttack = nbttagcompound.getInteger("daysToAttack");
		this.continuousAttack = nbttagcompound.getBoolean("continuousAttack");
		this.activated = nbttagcompound.getBoolean("activated");
		//this.boundingBoxToRadius.func_72324_b((double)(this.field_145851_c - (this.spawnRadius + 10)), (double)(this.field_145848_d - (this.spawnRadius + 40)), (double)(this.field_145849_e - (this.spawnRadius + 10)), (double)(this.field_145851_c + (this.spawnRadius + 10)), (double)(this.field_145848_d + (this.spawnRadius + 40)), (double)(this.field_145849_e + (this.spawnRadius + 10)));
		//MainRegistry.log("activationTimer = " + this.activationTimer);
		//MainRegistry.log("mode = " + this.mode);
		//MainRegistry.log("currentWave = " + this.currentWave);
		//MainRegistry.log("spawnRadius = " + this.spawnRadius);
		//MainRegistry.log("nexusLevel = " + this.nexusLevel);
		//MainRegistry.log("hp = " + this.hp);
		//MainRegistry.log("nexusKills = " + this.nexusKills);
		//MainRegistry.log("powerLevel = " + this.powerLevel);
		//MainRegistry.log("lastPowerLevel = " + this.lastPowerLevel);
		//MainRegistry.log("nextAttackTime = " + this.nextAttackTime);
		//this.waveSpawner.setRadius(this.spawnRadius);
		if (this.mode == 1 || this.mode == 3 || this.mode == 2 && this.continuousAttack) {
			//MainRegistry.log("Nexus is active; flagging for restore");
			this.resumedFromNBT = true;
			this.spawnerElapsedRestore = nbttagcompound.getLong("spawnerElapsed");
			//MainRegistry.log("spawnerElapsed = " + this.spawnerElapsedRestore);
		}
		//this.attackerAI.readFromNBT(nbttagcompound);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbttagcompound) {
		if (this.mode != 0) {
			//MainRegistry.setNexusUnloaded(this);
		}
		super.writeToNBT(nbttagcompound);
		nbttagcompound.setShort("activationTimer", (short)this.activationTimer);
		nbttagcompound.setShort("currentWave", (short)this.currentWave);
		nbttagcompound.setShort("spawnRadius", (short)this.spawnRadius);
		nbttagcompound.setShort("nexusLevel", (short)this.nexusLevel);
		nbttagcompound.setShort("hp", (short)this.hp);
		nbttagcompound.setInteger("nexusKills", this.nexusKills);
		nbttagcompound.setShort("generation", (short)this.generation);
		//nbttagcompound.setLong("spawnerElapsed", this.waveSpawner.getElapsedTime());
		nbttagcompound.setInteger("mode", this.mode);
		nbttagcompound.setInteger("powerLevel", this.powerLevel);
		nbttagcompound.setInteger("lastPowerLevel", this.lastPowerLevel);
		nbttagcompound.setInteger("nextAttackTime", this.nextAttackTime);
		nbttagcompound.setInteger("daysToAttack", this.daysToAttack);
		nbttagcompound.setBoolean("continuousAttack", this.continuousAttack);
		nbttagcompound.setBoolean("activated", this.activated);
		NBTTagList nbttaglist = new NBTTagList();
		for (int i = 0; i < this.nexusItemStacks.length; ++i) {
			if (this.nexusItemStacks[i] == null) continue;
			NBTTagCompound nbttagcompound1 = new NBTTagCompound();
			nbttagcompound1.setByte("Slot", (byte)i);
			this.nexusItemStacks[i].writeToNBT(nbttagcompound1);
			nbttaglist.appendTag(nbttagcompound1);
		}
		nbttagcompound.setTag("Items", nbttaglist);
		NBTTagList nbttaglist2 = new NBTTagList();
		for (Map.Entry<String, Long> entry : this.boundPlayers.entrySet()) {
			NBTTagCompound nbttagcompound1 = new NBTTagCompound();
			nbttagcompound1.setString("name", entry.getKey());
			nbttaglist2.appendTag((NBTBase)nbttagcompound1);
		}
		nbttagcompound.setTag("boundPlayers", (NBTBase)nbttaglist2);
		//this.attackerAI.writeToNBT(nbttagcompound);
		return nbttagcompound;
	}
	
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
*/
}
