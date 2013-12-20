package ocelot.mods.qp;

import ocelot.mods.qp.client.QuarryPlusTab;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBase extends Block
{

	public BlockBase(int par1, Material par2Material)
	{
		super(par1, par2Material);
		this.setCreativeTab(QuarryPlusTab.tab);
	}

}
