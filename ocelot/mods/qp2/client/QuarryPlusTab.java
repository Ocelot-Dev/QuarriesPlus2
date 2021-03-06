/*
 * Copyright (C) 2012,2013 yogpstop, Reworked by Werl and Snipe
 * This program is free software: you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the
 * GNU Lesser General Public License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 */

package ocelot.mods.qp2.client;

import ocelot.mods.qp2.QuarryPlus2;
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
