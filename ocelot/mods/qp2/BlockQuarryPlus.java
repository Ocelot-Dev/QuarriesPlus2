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

package ocelot.mods.qp2;

import java.util.ArrayList;

import buildcraft.api.tools.IToolWrench;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet3Chat;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockQuarryPlus extends BlockContainer {
	Icon textureTop, textureFront, texBB, texNNB, texMF;

	public BlockQuarryPlus(int i) {
		super(i, Material.iron);
		setHardness(1.5F);
		setResistance(10F);
		setStepSound(soundStoneFootstep);
		setUnlocalizedName("QuarryPlus");
	}

	private final ArrayList<ItemStack> drop = new ArrayList<ItemStack>();

	@Override
	public void breakBlock(World world, int x, int y, int z, int id, int meta) {
		this.drop.clear();
		TileQuarryPlus tile = (TileQuarryPlus) world.getBlockTileEntity(x, y, z);
		if (world.isRemote || tile == null) return;
		int count = quantityDropped(meta, 0, world.rand);
		int id1 = idDropped(meta, world.rand, 0);
		if (id1 > 0) {
			for (int i = 0; i < count; i++) {
				ItemStack is = new ItemStack(id1, 1, damageDropped(meta));
				EnchantmentHelper.enchantmentToIS(tile, is);
				this.drop.add(is);
			}
		}
		super.breakBlock(world, x, y, z, id, meta);
	}

	@Override
	public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune) {
		return this.drop;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTexture(IBlockAccess ba, int x, int y, int z, int side) {
		TileEntity tile = ba.getBlockTileEntity(x, y, z);
		if (tile instanceof TileQuarryPlus) {
			if (side == 1) {
				switch (((TileQuarryPlus) tile).G_getNow()) {
				case TileQuarryPlus.BREAKBLOCK:
				case TileQuarryPlus.MOVEHEAD:
					return this.texBB;
				case TileQuarryPlus.MAKEFRAME:
					return this.texMF;
				case TileQuarryPlus.NOTNEEDBREAK:
					return this.texNNB;
				}
			}
		}
		return super.getBlockTexture(ba, x, y, z, side);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int i, int j) {
		if (j == 0 && i == 3) return this.textureFront;

		if (i == j) return this.textureFront;

		switch (i) {
		case 1:
			return this.textureTop;
		default:
			return this.blockIcon;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.blockIcon = par1IconRegister.registerIcon("qp2:quarry");
		this.textureTop = par1IconRegister.registerIcon("qp2:quarry_top");
		this.textureFront = par1IconRegister.registerIcon("qp2:quarry_front");
		this.texBB = par1IconRegister.registerIcon("qp2:quarry_top_bb");
		this.texNNB = par1IconRegister.registerIcon("qp2:quarry_top_nnb");
		this.texMF = par1IconRegister.registerIcon("qp2:quarry_top_mf");
	}

	@Override
	public TileEntity createNewTileEntity(World w) {
		return new TileQuarryPlus();
	}

	@Override
	public void onBlockPlacedBy(World w, int x, int y, int z, EntityLivingBase el, ItemStack is) {
		super.onBlockPlacedBy(w, x, y, z, el, is);
		ForgeDirection orientation = get2dOrientation(el.posX, el.posZ, x, z);
		w.setBlockMetadataWithNotify(x, y, z, orientation.getOpposite().ordinal(), 1);
		((TileQuarryPlus) w.getBlockTileEntity(x, y, z)).requestTicket();
		EnchantmentHelper.init((IEnchantableTile) w.getBlockTileEntity(x, y, z), is.getEnchantmentTagList());
	}

	private static ForgeDirection get2dOrientation(double x1, double z1, double x2, double z2) {
		double Dx = x1 - x2;
		double Dz = z1 - z2;
		double angle = Math.atan2(Dz, Dx) / Math.PI * 180 + 180;

		if (angle < 45 || angle > 315) return ForgeDirection.EAST;
		else if (angle < 135) return ForgeDirection.SOUTH;
		else if (angle < 225) return ForgeDirection.WEST;
		else return ForgeDirection.NORTH;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer ep, int par6, float par7, float par8, float par9) {
		Item equipped = ep.getCurrentEquippedItem() != null ? ep.getCurrentEquippedItem().getItem() : null;
		if (equipped instanceof IToolWrench && ((IToolWrench) equipped).canWrench(ep, x, y, z) && ep.isSneaking()) {
			((TileQuarryPlus) world.getBlockTileEntity(x, y, z)).G_reinit();
			((IToolWrench) equipped).wrenchUsed(ep, x, y, z);
			return true;
		}
		if (equipped instanceof ItemTool && ep.getCurrentEquippedItem().getItemDamage() == 0 && ep.isSneaking()) {
			if (world.isRemote) return true;
			for (String s : EnchantmentHelper.getEnchantmentsChat((IEnchantableTile) world.getBlockTileEntity(x, y, z)))
				PacketDispatcher.sendPacketToPlayer(new Packet3Chat(ChatMessageComponent.createFromText(s)), (Player) ep);
			return true;
		}
		if(ep.isSneaking())
			return false;
		else
			ep.openGui(QuarryPlus2.instance, QuarryPlus2.guiIdQuarry, world, x, y, z);
		return true;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int blockId) {
		if (!world.isRemote) ((TileBasic) world.getBlockTileEntity(x, y, z)).G_renew_powerConfigure();
	}
}