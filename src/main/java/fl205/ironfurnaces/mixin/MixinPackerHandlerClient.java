package fl205.ironfurnaces.mixin;

import fl205.ironfurnaces.IronFurnacesTypeRegistry;
import fl205.ironfurnaces.MixinInterfaces.IEntityPlayer;
import fl205.ironfurnaces.tileEntities.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.net.handler.PacketHandlerClient;
import net.minecraft.core.block.entity.TileEntityFurnace;
import net.minecraft.core.net.packet.PacketContainerOpen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = PacketHandlerClient.class, remap = false)
public class MixinPackerHandlerClient {
	@Final
	@Shadow
	private Minecraft mc;

	@Inject(method = "handleContainerOpen", at = @At("HEAD"), cancellable = true)
	public void injectMethod(PacketContainerOpen packet100openwindow, CallbackInfo info) {
		if (packet100openwindow.inventoryType == PacketContainerOpen.TYPE_FURNACE) {
			IronFurnacesTypeRegistry.FurnaceType type = IronFurnacesTypeRegistry.bySlotsCount(packet100openwindow.slotsCount);
			if (type != null)
				((IEntityPlayer)this.mc.thePlayer).fl205ironfurnaces$displayGUICustomFurnace(type.factory().get());
			else
				this.mc.thePlayer.displayFurnaceScreen(new TileEntityFurnace());
			this.mc.thePlayer.containerMenu.containerId = packet100openwindow.windowId;

			info.cancel();
		}
	}
}
