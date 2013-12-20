package ocelot.mods.qp.api;

public interface IDrillHead
{
	public float drillSpeed();
	
	public float baseEfficentcy();
	
	public int miningLevel();
	
	public int maxUses();
	
	public int usesLeft();
	
	public byte efficencyEnchant();
	
	public byte unbreakingEnchant();
	
	public byte fortuneEnchant();
	
	public boolean silkEnchant();
	
	public String textureLocation();
}
