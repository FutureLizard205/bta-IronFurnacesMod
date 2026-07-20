package fl205.ironfurnaces;

import net.minecraft.core.block.Blocks;
import net.minecraft.core.item.Items;
import turniplabs.halplibe.helper.recipeBuilders.RecipeBuilderShaped;

import static fl205.ironfurnaces.IronFurnaces.*;

public class IronFurnacesRecipes {
	public static void onRecipesReady() {
		// Template
		RecipeBuilderShaped furnace = new RecipeBuilderShaped(MOD_ID, "AAA", "AFA", "AAA");
		// Iron Furnace
		furnace.addInput('A', Items.INGOT_IRON).addInput('F', Blocks.FURNACE_STONE_IDLE).create("furnace_iron", furnaceIronIdle.getDefaultStack());
		// Gold Furnace
		furnace.addInput('A', Items.INGOT_GOLD).addInput('F', furnaceIronIdle).create("furnace_gold", furnaceGoldIdle.getDefaultStack());
		// Diamond Furnace
		furnace.addInput('A', Items.DIAMOND).addInput('F', furnaceGoldIdle).create("furnace_diamond", furnaceDiamondIdle.getDefaultStack());
		// Steel Furnace
		furnace.addInput('A', Items.INGOT_STEEL).addInput('F', furnaceGoldIdle).create("furnace_steel", furnaceSteelIdle.getDefaultStack());
	}
}
