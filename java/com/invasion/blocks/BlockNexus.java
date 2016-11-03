package com.invasion.blocks;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;

import com.invasion.MainRegistry;
import com.invasion.ModInfo;
import com.invasion.client.gui.GuiHandler;
import com.invasion.items.IMItems;
import com.invasion.tileentity.TileEntityNexus;

public class BlockNexus extends BlockContainer implements IIMBlock {
	
	public static final PropertyBool ACTIVE = PropertyBool.create("active");
	
	public BlockNexus(){
		super(Material.ROCK, MapColor.BLACK);
		this.setUnlocalizedName("blockNexus").setRegistryName("blockNexus");
		this.setDefaultState(this.blockState.getBaseState().withProperty(ACTIVE, Boolean.valueOf(false)));
		this.setHardness(20.0F).setResistance(Float.MAX_VALUE);
	}
	
	@Override
	public void registerItemModel(ItemBlock itemBlock){
		//MainRegistry.proxy.registerItemRenderer(itemBlock, 0, "blockNexus");
		MainRegistry.print("Loading block nexus model ....");
		ModelLoader.setCustomModelResourceLocation(itemBlock, 0, new ModelResourceLocation(ModInfo.MODID + ":blockstates/blockNexus.json", "active"));
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityNexus(worldIn);
	}
	
	@Override
	public BlockStateContainer createBlockState(){
		return new BlockStateContainer(this, ACTIVE);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ){
		if(worldIn.isRemote) return true;
		boolean usingProbe;
		if(heldItem == null){
			usingProbe = false;
		} else {
			usingProbe = heldItem.getItem() == IMItems.itemProbe;
		}
		if(!usingProbe){
			TileEntity tileEntity = worldIn.getTileEntity(pos);
			if(tileEntity != null) if(tileEntity instanceof TileEntityNexus){
				playerIn.openGui(MainRegistry.instance, GuiHandler.ID_NEXUS, worldIn, pos.getX(), pos.getY(), pos.getZ());
			}
		}
		return true;
	}
	
	@Override
	public int getMetaFromState(IBlockState state){
		if(state != null){
			if(state.getValue(ACTIVE)){
				return 1;
			} else {
				return 0;
			}
		}
		//something definitely went wrong if state == null...
		return -1;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta){
		switch(meta){
		case 0:
			return this.getDefaultState().withProperty(ACTIVE, false);
		case 1:
			return this.getDefaultState().withProperty(ACTIVE, true);
		default:
			return null;
		}
	}
	
	@Override
	public int damageDropped(IBlockState state){
		return 0;
	}
	
	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
		list.add(new ItemStack(itemIn, 1, 0));
	}
	
}
