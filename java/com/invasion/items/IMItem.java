package com.invasion.items;

import com.invasion.MainRegistry;

import net.minecraft.item.Item;

public class IMItem extends Item {
	
	public final String name;

	public IMItem(String name) {
		this.name = name;
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
		this.setCreativeTab(MainRegistry.tabInvasion);
	}
	
	public void registerItemModel(){
		MainRegistry.proxy.registerItemRenderer(this, 0, this.name);
	}

}
