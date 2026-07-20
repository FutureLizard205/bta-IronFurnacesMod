package fl205.ironfurnaces;

import fl205.ironfurnaces.tileEntities.TileEntityDiamondFurnace;
import fl205.ironfurnaces.tileEntities.TileEntityGoldFurnace;
import fl205.ironfurnaces.tileEntities.TileEntityIronFurnace;
import fl205.ironfurnaces.tileEntities.TileEntitySteelFurnace;
import net.minecraft.core.util.collection.NamespaceID;
import turniplabs.halplibe.helper.EntityHelper;

import static fl205.ironfurnaces.IronFurnaces.MOD_ID;

public class IronFurnacesEntities {
	public static void beforeGameStart() {
		// Tile Entities
		EntityHelper.addMapping(TileEntityIronFurnace.class,  new NamespaceID(MOD_ID, "furnace_iron"));
		EntityHelper.addMapping(TileEntityGoldFurnace.class, new NamespaceID(MOD_ID, "furnace_gold"));
		EntityHelper.addMapping(TileEntityDiamondFurnace.class, new NamespaceID(MOD_ID, "furnace_diamond"));
		EntityHelper.addMapping(TileEntitySteelFurnace.class, new NamespaceID(MOD_ID, "furnace_steel"));
	}
}
