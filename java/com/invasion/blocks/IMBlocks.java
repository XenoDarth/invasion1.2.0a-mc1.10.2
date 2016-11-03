package com.invasion.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class IMBlocks {
	
	public static BlockNexus blockNexus;
	
	public static void initBlocks(){
		blockNexus = register(new BlockNexus());
	}
	
	private static <T extends Block> T register(T block, ItemBlock itemBlock) {
		GameRegistry.register(block);
		GameRegistry.register(itemBlock);

		if(block instanceof IIMBlock){
			((IIMBlock)block).registerItemModel(itemBlock);
		}

		return block;
	}

	private static <T extends Block> T register(T block) {
		ItemBlock itemBlock = new ItemBlock(block);
		itemBlock.setRegistryName(block.getRegistryName());
		return register(block, itemBlock);
	}
	
}
