package com.invasion;

import com.invasion.blocks.IMBlocks;
import com.invasion.items.IMItem;
import com.invasion.items.IMItems;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CraftingAndSmelting {
	
	public static void mainRegistry(){
		crafting();
		smelting();
	}
	
	//Note: Recipes are still shaped, but shapes can be placed anywhere on the table.
	//For example, the catalyst mixture can be crafted using the top two rows or the bottom two rows of the crafting table.
	//The Infused Sword can be crafted with the rift flux in either the left or right column or the top or bottom row.
	private static void crafting(){
		
		//Nexus
		GameRegistry.addRecipe(new ItemStack(IMBlocks.blockNexus, 1), new Object[]{
			" X ", "#D#", " # ",
			Character.valueOf('X'), IMItems.itemPhaseCrystal,
			Character.valueOf('#'), Items.REDSTONE,
			Character.valueOf('D'), Blocks.OBSIDIAN
		});
		
		//Phase Crystal
		GameRegistry.addRecipe(new ItemStack(IMItems.itemPhaseCrystal, 1), new Object[]{
			" X ", "#D#", " X ",
			Character.valueOf('X'), new ItemStack(Items.DYE, 1, 4),
			Character.valueOf('#'), Items.REDSTONE,
			Character.valueOf('D'), Items.DIAMOND
		});
		GameRegistry.addRecipe(new ItemStack(IMItems.itemPhaseCrystal, 1), new Object[]{
			" X ", "#D#", " X ",
			Character.valueOf('X'), Items.REDSTONE,
			Character.valueOf('#'), new ItemStack(Items.DYE, 1, 4),
			Character.valueOf('D'), Items.DIAMOND
		});
		
		//Rift Flux
		GameRegistry.addRecipe(new ItemStack(IMItems.itemRiftFlux, 1), new Object[]{"XXX", "XXX", "XXX", Character.valueOf('X'), IMItems.itemSmallRemnants});
		
		//Infused Sword
		GameRegistry.addRecipe(new ItemStack(IMItems.itemInfusedSword, 1), new Object[]{
			"X ", "X#", "X ",
			Character.valueOf('X'), IMItems.itemRiftFlux,
			Character.valueOf('#'), new ItemStack(Items.DIAMOND_SWORD, 1, 32767)
		});
		GameRegistry.addRecipe(new ItemStack(IMItems.itemInfusedSword, 1), new Object[]{
			" X", "#X", " X",
			Character.valueOf('X'), IMItems.itemRiftFlux,
			Character.valueOf('#'), new ItemStack(Items.DIAMOND_SWORD, 1, 32767)
		});
		GameRegistry.addRecipe(new ItemStack(IMItems.itemInfusedSword, 1), new Object[]{
			"XXX", " # ",
			Character.valueOf('X'), IMItems.itemRiftFlux,
			Character.valueOf('#'), new ItemStack(Items.DIAMOND_SWORD, 1, 32767)
		});
		GameRegistry.addRecipe(new ItemStack(IMItems.itemInfusedSword, 1), new Object[]{
			" # ", "XXX",
			Character.valueOf('X'), IMItems.itemRiftFlux,
			Character.valueOf('#'), new ItemStack(Items.DIAMOND_SWORD, 1, 32767)
		});
		
		//Catalyst Mixture
		GameRegistry.addRecipe(new ItemStack(IMItems.itemCatalystMixture, 1), new Object[]{
			"D#H", " X ",
			Character.valueOf('X'), Items.BOWL,
			Character.valueOf('#'), Items.REDSTONE,
			Character.valueOf('D'), Items.ROTTEN_FLESH,
			Character.valueOf('H'), Items.BONE
		});
		GameRegistry.addRecipe(new ItemStack(IMItems.itemCatalystMixture, 1), new Object[]{
			"H#D", " X ",
			Character.valueOf('X'), Items.BOWL,
			Character.valueOf('#'), Items.REDSTONE,
			Character.valueOf('D'), Items.BONE,
			Character.valueOf('H'), Items.ROTTEN_FLESH
		});
		
		//Stable Catalyst Mixture
		GameRegistry.addRecipe(new ItemStack(IMItems.itemStableCatalystMixture, 1), new Object[]{
			"D#D", " X ",
			Character.valueOf('X'), Items.BOWL,
			Character.valueOf('#'), Items.COAL,
			Character.valueOf('D'), Items.BONE
		});
		
		//Damping Agent
		GameRegistry.addRecipe(new ItemStack(IMItems.itemDampingAgent, 1), new Object[]{
			"#X#", 
			Character.valueOf('X'), new ItemStack(IMItems.itemRiftFlux, 1),
			Character.valueOf('#'), new ItemStack(Items.DYE, 1, 4)
		});
		
		//Strong Damping Agent
		GameRegistry.addRecipe(new ItemStack(IMItems.itemStrongDampingAgent, 1), new Object[]{"X", "X", "X", Character.valueOf('X'), IMItems.itemDampingAgent});
		GameRegistry.addRecipe(new ItemStack(IMItems.itemStrongDampingAgent, 1), new Object[]{"XXX", Character.valueOf('X'), IMItems.itemDampingAgent});
		
		//Strange Bone
		GameRegistry.addRecipe(new ItemStack(IMItems.itemStrangeBone, 1), new Object[]{
			"X#X",
			Character.valueOf('X'), new ItemStack(IMItems.itemRiftFlux, 1),
			Character.valueOf('#'), Items.BONE
		});
		
		//Searing Bow
		GameRegistry.addRecipe(new ItemStack(IMItems.itemSearingBow, 1), new Object[]{
			"XXX", "X# ", "X  ",
			Character.valueOf('X'), new ItemStack(IMItems.itemRiftFlux, 1),
			Character.valueOf('#'), new ItemStack((Item)Items.BOW, 1, 32767)
		});
		GameRegistry.addRecipe(new ItemStack(IMItems.itemSearingBow, 1), new Object[]{
			"X  ", "X# ", "XXX",
			Character.valueOf('X'), new ItemStack(IMItems.itemRiftFlux, 1),
			Character.valueOf('#'), new ItemStack((Item)Items.BOW, 1, 32767)
		});
		GameRegistry.addRecipe(new ItemStack(IMItems.itemSearingBow, 1), new Object[]{
			"XXX", " #X", "  X",
			Character.valueOf('X'), new ItemStack(IMItems.itemRiftFlux, 1),
			Character.valueOf('#'), new ItemStack((Item)Items.BOW, 1, 32767)
		});
		GameRegistry.addRecipe(new ItemStack(IMItems.itemSearingBow, 1), new Object[]{
			"  X", " #X", "XXX",
			Character.valueOf('X'), new ItemStack(IMItems.itemRiftFlux, 1),
			Character.valueOf('#'), new ItemStack((Item)Items.BOW, 1, 32767)
		});
		
		//Vanilla items
		GameRegistry.addRecipe(new ItemStack(Items.GUNPOWDER, 16), new Object[]{"X", "X", "X", Character.valueOf('X'), new ItemStack(IMItems.itemRiftFlux, 1)});
		GameRegistry.addRecipe(new ItemStack(Items.GUNPOWDER, 16), new Object[]{"XXX", Character.valueOf('X'), new ItemStack(IMItems.itemRiftFlux, 1)});
		GameRegistry.addRecipe(new ItemStack(Items.DIAMOND, 1), new Object[]{" X ", "X X", " X ", Character.valueOf('X'), new ItemStack(IMItems.itemRiftFlux, 1)});
		GameRegistry.addRecipe(new ItemStack(Items.IRON_INGOT, 4), new Object[]{"X", Character.valueOf('X'), new ItemStack(IMItems.itemRiftFlux, 1)});
		GameRegistry.addRecipe(new ItemStack(Items.REDSTONE, 24), new Object[]{"X X", Character.valueOf('X'), new ItemStack(IMItems.itemRiftFlux, 1)});
		GameRegistry.addRecipe(new ItemStack(Items.DYE, 12, 4), new Object[]{" X ", "   ", " X ", Character.valueOf('X'), new ItemStack(IMItems.itemRiftFlux, 1)});
		
		//Empty Trap
		GameRegistry.addRecipe(new ItemStack(IMItems.itemIMTrap, 1, 0), new Object[]{" X ", "X#X", " X ",
			Character.valueOf('X'), Items.IRON_INGOT,
			Character.valueOf('#'), new ItemStack(IMItems.itemRiftFlux, 1)
		});
		
		//Flame Trap
		GameRegistry.addRecipe(new ItemStack(IMItems.itemIMTrap, 1, 2), new Object[]{
			"#", "X",
			Character.valueOf('X'), new ItemStack(IMItems.itemIMTrap, 1, 0),
			Character.valueOf('#'), Items.LAVA_BUCKET
		});
		
		//Nexus Adjuster
		GameRegistry.addRecipe(new ItemStack(IMItems.itemProbe, 1, 0), new Object[]{" X", "XX", "XX", Character.valueOf('X'), Items.IRON_INGOT});
		GameRegistry.addRecipe(new ItemStack(IMItems.itemProbe, 1, 0), new Object[]{"X ", "XX", "XX", Character.valueOf('X'), Items.IRON_INGOT});
		
		//Material Probe
		GameRegistry.addRecipe(new ItemStack(IMItems.itemProbe, 1, 1), new Object[]{
			"D", "#", "X",
			Character.valueOf('X'), Items.STICK,
			Character.valueOf('#'), IMItems.itemPhaseCrystal,
			Character.valueOf('D'), new ItemStack(IMItems.itemProbe, 1, 0)
		});
	}
	
	private static void smelting(){
		GameRegistry.addSmelting(IMItems.itemCatalystMixture, new ItemStack(IMItems.itemNexusCatalyst), 1.0f);
		GameRegistry.addSmelting(IMItems.itemStableCatalystMixture, new ItemStack(IMItems.itemStableNexusCatalyst), 1.0f);
	}
	
}
