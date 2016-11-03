package com.invasion.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

import com.invasion.MainRegistry;

public class IMBlock extends Block implements IIMBlock {
	
	public final String name;
	
	/** Generic mod block class. */
	public IMBlock(String name, Material material){
		this(name, material, material.getMaterialMapColor());
	}
	
	public IMBlock(String name, Material material, MapColor mapColor){
		this(name, material, mapColor, 3f, 5f);
	}

	public IMBlock(String name, Material material, MapColor mapColor, float hardness, float resistance){
		super(material, mapColor);
		this.name = name;
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setHardness(hardness);
		this.setResistance(resistance);
		this.setCreativeTab(MainRegistry.tabInvasion);
	}
	
	@Override
	public void registerItemModel(ItemBlock itemBlock){
		MainRegistry.proxy.registerItemRenderer(itemBlock, 0, this.name);
	}

}
