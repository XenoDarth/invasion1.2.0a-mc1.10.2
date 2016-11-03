package com.invasion;

import java.io.File;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class CommonProxy {
	
	public void registerItemRenderer(Item item, int meta, String id) {
		
	}
	
	public File getFile(String fileName) {
		return FMLCommonHandler.instance().getMinecraftServerInstance().getFile(fileName);
	}
	
}
