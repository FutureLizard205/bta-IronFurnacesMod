package fl205.ironfurnaces.tileEntities;

import fl205.ironfurnaces.blocks.SteelFurnace;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntityFurnace;
import net.minecraft.core.crafting.LookupFuelFurnace;
import net.minecraft.core.crafting.recipe.RecipesFurnace;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;

import static fl205.ironfurnaces.IronFurnaces.config;

public class TileEntitySteelFurnace extends TileEntityFurnace {

	protected final int speedModifier = config.getInt("speed.steelFurnace");
	protected final int fuelYieldModifier = (100 * config.getInt("fuelYield.steelFurnace")) / speedModifier;


	public int maxCookTime = 20000 / speedModifier;

	public String getInvName() {
		return "Steel Furnace";
	}

	public void updateEntity() {
		boolean isBurnTimeHigherThan0 = this.currentBurnTime > 0;
		boolean furnaceUpdated = false;
		if (this.currentBurnTime > 0) {
			--this.currentBurnTime;
		}

		if (!this.worldObj.isClientSide) {
			if (this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord) == config.getInt("ids.steelFurnaceIdleID") && this.currentBurnTime == 0 && this.furnaceItemStacks[0] == null && this.furnaceItemStacks[1] != null && this.furnaceItemStacks[1].itemID == Block.netherrack.id) {
				--this.furnaceItemStacks[1].stackSize;
				if (this.furnaceItemStacks[1].stackSize <= 0) {
					this.furnaceItemStacks[1] = null;
				}

				SteelFurnace.updateFurnaceBlockState(true, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
				furnaceUpdated = true;
			}

			if (this.currentBurnTime == 0 && this.canSmelt()) {
				this.maxBurnTime = this.currentBurnTime = this.getBurnTimeFromItem(this.furnaceItemStacks[1]);
				if (this.currentBurnTime > 0) {
					furnaceUpdated = true;
					if (this.furnaceItemStacks[1] != null) {
						if (this.furnaceItemStacks[1].getItem() == Item.bucketLava) {
							this.furnaceItemStacks[1] = new ItemStack(Item.bucket);
						} else {
							--this.furnaceItemStacks[1].stackSize;
							if (this.furnaceItemStacks[1].stackSize <= 0) {
								this.furnaceItemStacks[1] = null;
							}
						}
					}
				}
			}

			if (this.isBurning() && this.canSmelt()) {
				++this.currentCookTime;
				if (this.currentCookTime == this.maxCookTime) {
					this.currentCookTime = 0;
					this.smeltItem();
					furnaceUpdated = true;
				}
			} else {
				this.currentCookTime = 0;
			}

			if (isBurnTimeHigherThan0 != this.currentBurnTime > 0) {
				furnaceUpdated = true;
				this.updateFurnace();
			}
		}

		if (furnaceUpdated) {
			this.onInventoryChanged();
		}

	}

	private boolean canSmelt() {
		if (this.furnaceItemStacks[0] == null) {
			return false;
		} else {
			ItemStack itemstack = RecipesFurnace.smelting().getSmeltingResult(this.furnaceItemStacks[0].getItem().id);
			if (itemstack == null) {
				return false;
			} else if (this.furnaceItemStacks[2] == null) {
				return true;
			} else if (!this.furnaceItemStacks[2].isItemEqual(itemstack)) {
				return false;
			} else if (this.furnaceItemStacks[2].stackSize < this.getInventoryStackLimit() && this.furnaceItemStacks[2].stackSize < this.furnaceItemStacks[2].getMaxStackSize()) {
				return true;
			} else {
				return this.furnaceItemStacks[2].stackSize < itemstack.getMaxStackSize();
			}
		}
	}

	protected void updateFurnace() {
		SteelFurnace.updateFurnaceBlockState(this.currentBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
	}

	private int getBurnTimeFromItem(ItemStack itemStack) {
		return itemStack == null ? 0 : ((fuelYieldModifier * (LookupFuelFurnace.instance.getFuelYield(itemStack.getItem().id)))/100);
	}

	@Override
	public int getCookProgressScaled(int i) {
		return (super.getCookProgressScaled(i) * speedModifier)/100;
	}
}