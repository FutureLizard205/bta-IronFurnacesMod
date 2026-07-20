package fl205.ironfurnaces.modded.BTWaila;

import fl205.ironfurnaces.tileEntities.TileEntityDiamondFurnace;
import fl205.ironfurnaces.tileEntities.TileEntityGoldFurnace;
import fl205.ironfurnaces.tileEntities.TileEntityIronFurnace;
import fl205.ironfurnaces.tileEntities.TileEntitySteelFurnace;
import org.slf4j.Logger;
import net.minecraft.core.block.entity.TileEntityFurnace;
import toufoumaster.btwaila.entryplugins.waila.BTWailaCustomTooltipPlugin;
import toufoumaster.btwaila.entryplugins.waila.BTWailaPlugin;
import toufoumaster.btwaila.tooltips.TileTooltip;
import toufoumaster.btwaila.tooltips.TooltipRegistry;

public class WailaPlugin implements BTWailaCustomTooltipPlugin {
	@Override
	public void initializePlugin(TooltipRegistry tooltipRegistry, Logger logger) {
		TileTooltip<TileEntityFurnace> inventory = BTWailaPlugin.FURNACE;
		inventory.addClass(TileEntityIronFurnace.class);
		inventory.addClass(TileEntityGoldFurnace.class);
		inventory.addClass(TileEntityDiamondFurnace.class);
		inventory.addClass(TileEntitySteelFurnace.class);
	}
}
