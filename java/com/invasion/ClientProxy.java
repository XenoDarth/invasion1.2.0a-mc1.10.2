package com.invasion;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(ModInfo.MODID + ":" + id + ".json", null));
		//Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
		//.register(item, meta, new ModelResourceLocation(ModInfo.MODID + ":" + id, "inventory"));
	}
	
}
