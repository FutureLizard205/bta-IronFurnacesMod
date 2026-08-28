package fl205.ironfurnaces.mixin.Entity;

import fl205.ironfurnaces.MixinInterfaces.IEntityPlayer;

import fl205.ironfurnaces.tileEntities.TileEntityCustomFurnace;
import net.minecraft.core.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = Player.class, remap = false)
public class MixinEntityPlayer implements IEntityPlayer {
	@Override
	public void fl205ironfurnaces$displayGUICustomFurnace(TileEntityCustomFurnace tileEntity) {}
}
