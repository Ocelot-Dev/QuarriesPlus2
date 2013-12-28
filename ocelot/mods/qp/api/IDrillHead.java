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

package ocelot.mods.qp.api;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public interface IDrillHead
{
	public float drillSpeed();
	
	public float baseEfficentcy();
	
	public int miningLevel();
	
	public int maxUses();
	
	public int usesLeft();

	public BlockBreak tryBreakBlock(Block block, World world, int x, int y, int z);
	
	public BlockBreak breakBlock(Block block, World world, int x, int y, int z);
	
	public String textureLocation();
}
