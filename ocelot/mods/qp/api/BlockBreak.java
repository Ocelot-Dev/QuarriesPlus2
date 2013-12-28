package ocelot.mods.qp.api;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;

public class BlockBreak
{
	public List<ItemStack> returnStacks;
	public float energyToUse;
	public int ticksToWait;
	
	public BlockBreak(List<ItemStack> stacks, float energy, int ticks)
	{
		this.returnStacks = stacks;
		this.energyToUse = energy;
		this.ticksToWait = ticks;
	}

}
