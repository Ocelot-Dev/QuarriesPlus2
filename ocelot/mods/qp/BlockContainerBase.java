package ocelot.mods.qp;

import ocelot.mods.qp.client.QuarryPlusTab;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class BlockContainerBase extends BlockContainer
{

	public BlockContainerBase(int par1, Material par2Material)
	{
		super(par1, par2Material);
		this.setCreativeTab(QuarryPlusTab.tab);
	}
}
