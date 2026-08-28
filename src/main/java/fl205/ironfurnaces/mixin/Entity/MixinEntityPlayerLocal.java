package fl205.ironfurnaces.mixin.Entity;

import fl205.ironfurnaces.MixinInterfaces.IEntityPlayer;
import fl205.ironfurnaces.gui.ScreenCustomFurnace;
import fl205.ironfurnaces.tileEntities.TileEntityCustomFurnace;
import net.minecraft.client.Minecraft;

import net.minecraft.client.entity.player.PlayerLocal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value = PlayerLocal.class, remap = false)
public class MixinEntityPlayerLocal implements IEntityPlayer {
	@Unique
	private final PlayerLocal thisAs = (PlayerLocal)(Object)this;
	@Shadow
	protected Minecraft mc;

	@Override
	public void fl205ironfurnaces$displayGUICustomFurnace(TileEntityCustomFurnace tileEntity) {
		this.mc.displayScreen(new ScreenCustomFurnace(thisAs.inventory, tileEntity));
	}
}
