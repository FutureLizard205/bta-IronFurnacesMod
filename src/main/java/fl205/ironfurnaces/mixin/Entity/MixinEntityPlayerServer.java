package fl205.ironfurnaces.mixin.Entity;

import fl205.ironfurnaces.IronFurnacesTypeRegistry;
import fl205.ironfurnaces.MixinInterfaces.IEntityPlayer;
import fl205.ironfurnaces.tileEntities.TileEntityCustomFurnace;
import net.minecraft.core.block.entity.TileEntityFurnace;
import net.minecraft.core.net.packet.PacketContainerOpen;
import net.minecraft.core.player.inventory.menu.MenuFurnace;
import net.minecraft.server.entity.player.PlayerServer;
import net.minecraft.server.net.handler.PacketHandlerServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;


@Mixin(value = PlayerServer.class, remap = false)
public class MixinEntityPlayerServer implements IEntityPlayer {
	@Shadow
	private void getNextWindowId() {}
	@Shadow
	private int currentWindowId;
	@Shadow
	public PacketHandlerServer playerNetServerHandler;
	@Unique
	private final PlayerServer thisAs = (PlayerServer)(Object)this;


	/**
	 * @author FutureLizard205
	 * @reason Overwritten to hardcode the packet's slotsCount field to 0 when opening
	 *         a normal (vanilla) furnace screen. This field is not being used for its
	 *         original purpose of a slot count. Iron Furnaces repurposes it as an ID
	 *         to tell the client which furnace tier's GUI to open. See FurnaceTypeRegistry.
	 */
	@Overwrite
	public void displayFurnaceScreen(TileEntityFurnace tileEntity) {
		this.getNextWindowId();
		this.playerNetServerHandler.sendPacket(new PacketContainerOpen(this.currentWindowId, PacketContainerOpen.TYPE_FURNACE, tileEntity.getNameTranslationKey(), 0));
		thisAs.containerMenu.onCraftGuiClosed(thisAs);
		thisAs.containerMenu = new MenuFurnace(thisAs.inventory, tileEntity);
		thisAs.containerMenu.containerId = this.currentWindowId;
		thisAs.containerMenu.addSlotListener(thisAs);
	}

	@Override
	public void fl205ironfurnaces$displayGUICustomFurnace(TileEntityCustomFurnace tileEntity) {
		this.getNextWindowId();
		this.playerNetServerHandler.sendPacket(new PacketContainerOpen(this.currentWindowId, PacketContainerOpen.TYPE_FURNACE, tileEntity.getNameTranslationKey(), IronFurnacesTypeRegistry.byTileEntity(tileEntity).slotsCount()));
		thisAs.containerMenu.onCraftGuiClosed(thisAs);
		thisAs.containerMenu = new MenuFurnace(thisAs.inventory, tileEntity);
		thisAs.containerMenu.containerId = this.currentWindowId;
		thisAs.containerMenu.addSlotListener(thisAs);
	}
}
