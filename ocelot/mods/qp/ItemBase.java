package ocelot.mods.qp;

import ocelot.mods.qp.client.QuarryPlusTab;
import net.minecraft.item.Item;

public class ItemBase extends Item
{

	public ItemBase(int par1)
	{
		super(par1);
		this.setCreativeTab(QuarryPlusTab.tab);
	}

}
