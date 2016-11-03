package com.invasion.items;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class IMItems {
	
	public static final IMItem itemPhaseCrystal = register("phaseCrystal");
	public static final IMItem itemRiftFlux = register("riftFlux");
	public static final IMItem itemSmallRemnants = register("smallRemnants");
	public static final IMItem itemNexusCatalyst = register("nexusCatalyst");
	public static final IMItem itemInfusedSword = register("infusedSword");
	public static final IMItem itemIMTrap = register("IMTrap");
	public static final IMItem itemSearingBow = register("searingBow");
	public static final IMItem itemCatalystMixture = register("catalystMixture");
	public static final IMItem itemStableCatalystMixture = register("stableCatalystMixture");
	public static final IMItem itemStableNexusCatalyst = register("stableNexusCatalyst");
	public static final IMItem itemDampingAgent = register("dampingAgent");
	public static final IMItem itemStrongDampingAgent = register("strongDampingAgent");
	public static final IMItem itemStrangeBone = register("strangeBone");
	public static final IMItem itemProbe = register("probe");
	public static final IMItem itemStrongCatalyst = register("strongCatalyst");
	public static final IMItem itemEngyHammer = register("engyHammer");
	
	private static IMItem register(String name){
		return register(new IMItem(name));
	}
	
	private static <T extends Item> T register(T item){
		GameRegistry.register(item);
		if(item instanceof IMItem) ((IMItem) item).registerItemModel();
		return item;
	}
	
}
