package fl205.ironfurnaces.mixin;

import fl205.ironfurnaces.IronFurnaces;
import net.minecraft.client.gui.container.ScreenContainerAbstract;
import net.minecraft.client.gui.container.ScreenFurnace;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntityFurnace;
import net.minecraft.core.lang.I18n;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;
import static java.util.Map.entry;

@Mixin(ScreenFurnace.class)
public abstract class ScreenFurnaceMixin extends ScreenContainerAbstract {
	// dummy constructor mixin requires — never actually called
	private ScreenFurnaceMixin() {
		super(null);
	}

	@Final
	@Shadow
	private TileEntityFurnace furnaceInventory;

	@Unique
	private static final Map<Block<?>, String> ironfurnaces$LABEL_KEYS = Map.ofEntries(
		entry(IronFurnaces.furnaceIronIdle,     "gui.ironfurnaces.furnace.iron.label.furnace"),
		entry(IronFurnaces.furnaceIronActive,   "gui.ironfurnaces.furnace.iron.label.furnace"),
		entry(IronFurnaces.furnaceGoldIdle,     "gui.ironfurnaces.furnace.gold.label.furnace"),
		entry(IronFurnaces.furnaceGoldActive,   "gui.ironfurnaces.furnace.gold.label.furnace"),
		entry(IronFurnaces.furnaceSteelIdle,    "gui.ironfurnaces.furnace.steel.label.furnace"),
		entry(IronFurnaces.furnaceSteelActive,  "gui.ironfurnaces.furnace.steel.label.furnace"),
		entry(IronFurnaces.furnaceDiamondIdle,  "gui.ironfurnaces.furnace.diamond.label.furnace"),
		entry(IronFurnaces.furnaceDiamondActive,"gui.ironfurnaces.furnace.diamond.label.furnace")
	);

	@Inject(method = "drawGuiContainerForegroundLayer", at = @At("HEAD"), cancellable = true)
	private void ironfurnaces$drawGuiContainerForegroundLayer(CallbackInfo ci) {
		I18n i18n = I18n.getInstance();

		Block<?> block = furnaceInventory.getBlock();
		String labelKey = ironfurnaces$LABEL_KEYS.get(block);
		String labelFurnaceStr = (labelKey != null)
			? i18n.translateKey(labelKey)
			: i18n.translateKey("gui.furnace.label.furnace");
		int labelFurnaceXPos = 80 - 2 * labelFurnaceStr.length();

		this.drawStringNoShadow(this.fontRenderer, labelFurnaceStr, labelFurnaceXPos, 6, 4210752);
		this.drawStringNoShadow(this.fontRenderer, i18n.translateKey("gui.furnace.label.inventory"), 8, this.ySize - 96 + 2, 4210752);
		ci.cancel();
	}
}
