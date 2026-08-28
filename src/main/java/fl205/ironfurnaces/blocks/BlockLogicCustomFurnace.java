package fl205.ironfurnaces.blocks;

import fl205.ironfurnaces.MixinInterfaces.IEntityPlayer;
import fl205.ironfurnaces.tileEntities.TileEntityCustomFurnace;
import net.minecraft.core.Global;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.BlockLogicRotatable;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.entity.TileEntityFurnace;
import net.minecraft.core.block.material.Materials;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.core.world.pos.TilePos;
import net.minecraft.core.world.pos.TilePosc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Random;

import static fl205.ironfurnaces.IronFurnaces.*;

public abstract class BlockLogicCustomFurnace extends BlockLogicRotatable {
	protected final boolean isActive;
	protected final int idleID;

	public BlockLogicCustomFurnace(Block<?> block, boolean isActive, int idleID) {
		super(block, Materials.METAL);
		this.isActive = isActive;
		this.idleID = idleID;
	}

	public ItemStack[] getBreakResult(@NotNull World world, @NotNull EnumDropCause dropCause, int data, @Nullable TileEntity tileEntity) {
		switch (dropCause) {
			case PICK_BLOCK:
			case EXPLOSION:
			case PROPER_TOOL:
			case SILK_TOUCH:
			case PISTON_CRUSH:
				return new ItemStack[]{new ItemStack(Blocks.getBlock(idleID))};
			default:
				return null;
		}
	}

	public void animationTick(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Random rand) {
		if (this.isActive) {
			double poxX = (double)tilePos.x() + (double)0.5F;
			double posY = (double)tilePos.y() + (double)0.0F + (double)(rand.nextFloat() * 6.0F / 16.0F);
			double posZ = (double)tilePos.z() + (double)0.5F;
			double f3 = (double)0.52F;
			double f4 = (double)(rand.nextFloat() * 0.6F - 0.3F);
			switch (BlockLogicRotatable.getDirectionFromMeta(world.getBlockData(tilePos))) {
				case WEST:
					world.spawnParticle("smoke", poxX - f3, posY, posZ + f4, (double)0.0F, (double)0.0F, (double)0.0F, 0, false);
					world.spawnParticle("flame", poxX - f3, posY, posZ + f4, (double)0.0F, (double)0.0F, (double)0.0F, 0, false);
					break;
				case EAST:
					world.spawnParticle("smoke", poxX + f3, posY, posZ + f4, (double)0.0F, (double)0.0F, (double)0.0F, 0, false);
					world.spawnParticle("flame", poxX + f3, posY, posZ + f4, (double)0.0F, (double)0.0F, (double)0.0F, 0, false);
					break;
				case NORTH:
					world.spawnParticle("smoke", poxX + f4, posY, posZ - f3, (double)0.0F, (double)0.0F, (double)0.0F, 0, false);
					world.spawnParticle("flame", poxX + f4, posY, posZ - f3, (double)0.0F, (double)0.0F, (double)0.0F, 0, false);
					break;
				case SOUTH:
					world.spawnParticle("smoke", poxX + f4, posY, posZ + f3, (double)0.0F, (double)0.0F, (double)0.0F, 0, false);
					world.spawnParticle("flame", poxX + f4, posY, posZ + f3, (double)0.0F, (double)0.0F, (double)0.0F, 0, false);
			}

		}
	}

	public boolean onInteracted(@NotNull World world, @NotNull TilePosc tilePos, @NotNull Player player, @Nullable Side side, double xHit, double yHit) {
		if (!world.isClientSide) {
			TileEntity tileEntity = world.getTileEntity(tilePos);
			if (tileEntity instanceof TileEntityCustomFurnace furnace) {
				((IEntityPlayer)player).fl205ironfurnaces$displayGUICustomFurnace(furnace);
			}
		}

		return true;
	}

	public static void updateFurnaceBlockState(@NotNull World world, @NotNull TilePos tilePos, boolean lit, int idleID) {
		if (!(world.getTileEntity(tilePos) instanceof TileEntityFurnace)) {
			String msg = "Custom Furnace is missing Tile Entity at " + String.valueOf(tilePos) + ", block will be removed!";
			if (Global.BUILD_CHANNEL.isUnstableBuild()) {
				throw new RuntimeException(msg);
			} else {
				world.setBlockTypeNotify(tilePos, Blocks.AIR);
				LOGGER.warn(msg);
			}
		} else {
			int meta = world.getBlockData(tilePos);
			Block<? extends BlockLogic> block = lit ? Blocks.getBlock(idleID+1) : Blocks.getBlock(idleID);
			world.setBlockTypeDataRaw(tilePos, block, meta);
			world.notifyBlockChange(tilePos, block);
		}
	}
}
