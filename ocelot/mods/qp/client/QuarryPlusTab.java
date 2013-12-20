package ocelot.mods.qp.client;

import ocelot.mods.qp.QuarryPlus2;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class QuarryPlusTab extends CreativeTabs
{
	public static final CreativeTabs tab = new QuarryPlusTab("QP2");

	public QuarryPlusTab(String label)
	{
		super(label);
	}

	public QuarryPlusTab(int par1, String par2Str)
	{
		super(par1, par2Str);
	}
	
	@Override
    public ItemStack getIconItemStack() {
		return new ItemStack(QuarryPlus2.blockQuarry);
		}
}
