/*
 * Copyright (C) 2012,2013 yogpstop: Updated by Werl and Snipe
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

package ocelot.mods.qp;

import ocelot.mods.qp.client.QuarryPlusTab;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBase extends Block
{

	public BlockBase(int ID, Material par2Material)
	{
		super(ID, par2Material);
		this.setCreativeTab(QuarryPlusTab.tab);
	}

}
