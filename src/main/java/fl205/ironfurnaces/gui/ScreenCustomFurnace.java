package fl205.ironfurnaces.gui;

import fl205.ironfurnaces.IronFurnacesTypeRegistry;
import fl205.ironfurnaces.tileEntities.TileEntityCustomFurnace;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.container.ScreenFurnace;
import net.minecraft.core.lang.I18n;
import net.minecraft.core.player.inventory.container.ContainerInventory;

@Environment(EnvType.CLIENT)
public class ScreenCustomFurnace extends ScreenFurnace {
	TileEntityCustomFurnace tileEntity;
	public ScreenCustomFurnace(ContainerInventory inventory, TileEntityCustomFurnace tileEntity) {
		super(inventory, tileEntity);
		this.tileEntity = tileEntity;
	}

	@Override
	protected void drawGuiContainerForegroundLayer() {
		I18n i18n = I18n.getInstance();

		String labelFurnaceStr;
		String labelKey = IronFurnacesTypeRegistry.byTileEntity(tileEntity).labelKey();
		labelFurnaceStr = (labelKey != null)
			? i18n.translateKey(labelKey)
			: i18n.translateKey("gui.furnace.label.furnace");

		int labelFurnaceXPos = 80 - 2 * labelFurnaceStr.length();

		this.drawStringNoShadow(this.fontRenderer, labelFurnaceStr, labelFurnaceXPos, 6, 4210752);
		this.drawStringNoShadow(this.fontRenderer, i18n.translateKey("gui.furnace.label.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}
}
