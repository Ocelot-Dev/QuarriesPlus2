/*
 * Copyright (C) 2012,2013 yogpstop, reworked by werl2 and snipe2701
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

import ocelot.mods.qp2.CommonProxy;
import ocelot.mods.qp2.EntityMechanicalArm;
import ocelot.mods.qp2.TileRefineryPlus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import buildcraft.core.render.RenderVoid;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.fml.relauncher.Side;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
	@Override
	public void registerTextures() {
		RenderingRegistry.registerEntityRenderingHandler(EntityMechanicalArm.class, new RenderVoid());
		ClientRegistry.bindTileEntitySpecialRenderer(TileRefineryPlus.class, RenderRefinery.INSTANCE);
		RenderingRegistry.registerBlockHandler(RenderRefinery.INSTANCE);
	}

	@Override
	public void removeEntity(Entity e) {
		e.worldObj.removeEntity(e);
		if (e.worldObj.isRemote) ((WorldClient) e.worldObj).removeEntityFromWorld(e.entityId);
	}

	@Override
	public World getClientWorld() {
		return Minecraft.getMinecraft().theWorld;
	}
}