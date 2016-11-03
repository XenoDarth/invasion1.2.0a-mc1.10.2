package com.invasion;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.invasion.blocks.IMBlocks;
import com.invasion.client.gui.GuiHandler;
import com.invasion.tileentity.TileEntityNexus;

@Mod(modid = ModInfo.MODID, name = ModInfo.NAME, version = ModInfo.MOD_VERSION)
public class MainRegistry {
	
	@Instance(ModInfo.MODID)
	public static MainRegistry instance;
	
	@SidedProxy(serverSide = "com.invasion.CommonProxy", clientSide = "com.invasion.ClientProxy")
	public static CommonProxy proxy;
	
	/** The nexus block that has the world's invasion attached to it */
	private static TileEntityNexus activeNexus = null;
	private static boolean isInvasionActive = false;
	
	public static final CreativeTabs tabInvasion = new CreativeTabs("invasionTab"){
		@Override
		public Item getTabIconItem(){
			return Item.getItemFromBlock(IMBlocks.blockNexus);
		}
	};
	
	private static boolean enableLog = false;
	private static BufferedWriter logOut;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e){
		File logFile;
		try {
			logFile = proxy.getFile("/logs/invasion_log.log");
			if (!logFile.exists())  logFile.createNewFile();
			logOut = new BufferedWriter(new FileWriter(logFile));
		} catch (Exception exception) {
			logOut = null;
			MainRegistry.log("Couldn't write to logfile");
			MainRegistry.log(exception.getMessage());
		}
		
		print("Attempting to find blockNexus.json ....");
		InputStream is = null;
		try {
			is = Minecraft.getMinecraft().getResourceManager().getResource(new ModelResourceLocation(ModInfo.MODID + ":blockstates/blockNexus.json", null)).getInputStream();
			print("blockNexus.json found!");
		} catch (Exception exception){
			exception.printStackTrace();
		}
		if(is != null){
			try {
				is.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		print("End");
		
		IMBlocks.initBlocks();
		SoundHandler.init();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e){
		CraftingAndSmelting.mainRegistry();
		GameRegistry.registerTileEntity(TileEntityNexus.class, "tileEntityNexus");
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent e){
		
	}
	
	@EventHandler
	public void serverStarting(FMLServerStartingEvent e){
		
	}
	
	public static boolean isInvasionActive() {
		return isInvasionActive;
	}

	public static boolean tryGetInvasionPermission(TileEntityNexus nexus) {
		if(nexus == activeNexus) return true;
		if (nexus != null) {
			activeNexus = nexus;
			isInvasionActive = true;
			return true;
		}
		String s = "Nexus entity invalid";
		MainRegistry.log(s);
		return false;
	}

	public static void setInvasionEnded(TileEntityNexus nexus) {
		if (activeNexus == nexus) isInvasionActive = false;
	}

	public static void setNexusUnloaded(TileEntityNexus nexus) {
		if (activeNexus == nexus) {
			nexus = null;
			isInvasionActive = false;
		}
	}
	
	public static TileEntityNexus getActiveNexus() {
		return activeNexus;
	}
	
	public static void print(String msg){
		String time = "[" + new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + "]";
		System.out.println(time + " [Invasion]: " + msg);
	}
	
	public static void printErr(String msg){
		String time = "[" + new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + "]";
		System.err.println(time + " [Invasion] [ERROR]: " + msg);
	}
	
	public static void sendMessageToPlayers(List<EntityPlayer> players, String msg){
		for(EntityPlayer player : players){
			player.addChatMessage(new TextComponentString(msg));
		}
	}
	
	public static void sendMessageToPlayersMP(List<EntityPlayerMP> players, String msg){
		for(EntityPlayerMP player : players){
			player.addChatMessage(new TextComponentString(msg));
		}
	}
	
	public static void broadcastToAll(String msg){
		sendMessageToPlayersMP(FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerList(), msg);
	}
	
	public static void sendMessageToPlayer(EntityPlayerMP player, String message) {
		if (player != null) {
			player.addChatComponentMessage(new TextComponentString(message));
		}
	}

	public static void log(String s) {
		if (enableLog) {
			if (s == null) return;
			try {
				if (logOut != null) {
					logOut.write(s);
					logOut.newLine();
					logOut.flush();
				} else {
					System.out.println(s);
				}
			} catch (IOException e) {
				System.out.println("Couldn't write to invasion log file");
				System.out.println(s);
			}
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		try {
			if (logOut != null)  logOut.close();
		} catch (Exception e) {
			logOut = null;
			MainRegistry.log("Error closing invasion log file");
		} finally {
			super.finalize();
		}
	}

}
