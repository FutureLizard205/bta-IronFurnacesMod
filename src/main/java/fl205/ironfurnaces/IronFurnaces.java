package fl205.ironfurnaces;

import fl205.ironfurnaces.blocks.BlockLogicDiamondFurnace;
import fl205.ironfurnaces.blocks.BlockLogicGoldFurnace;
import fl205.ironfurnaces.blocks.BlockLogicSteelFurnace;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.sound.BlockSounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.HalpLibe;
import turniplabs.halplibe.event.defs.CommonEvents;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.creativeInventory.CreativeInventoryCategory;
import turniplabs.halplibe.helper.creativeInventory.CreativeInventoryPlacement;
import turniplabs.halplibe.util.TomlConfigHandler;
import turniplabs.halplibe.util.dependency.Key;
import turniplabs.halplibe.util.toml.Toml;

import fl205.ironfurnaces.blocks.BlockLogicIronFurnace;


public class IronFurnaces implements ModInitializer {
	public static final String MOD_ID = HalpLibe.registerMod("ironfurnaces", true);
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	// Config TOML file manager

	public static final TomlConfigHandler config;
	static {
		// Config
		Toml toml = new Toml("Iron Furnaces Mod Config\nMore info at https://github.com/FutureLizard205/bta-IronFurnacesMod");

		toml.addCategory("IDs")
			.addEntry("ironFurnaceIdleID", 664)
			.addEntry("goldFurnaceIdleID", 666)
			.addEntry("diamondFurnaceIdleID", 668)
			.addEntry("steelFurnaceIdleID", 674);

		toml.addCategory( "Speed")
			.addEntry("ironFurnace", 125)
			.addEntry("goldFurnace", 160)
			.addEntry("diamondFurnace", 200)
			.addEntry("steelFurnace", 100);

		toml.addCategory("Fuel Yield")
			.addEntry("ironFurnace", 125)
			.addEntry("goldFurnace", 80)
			.addEntry("diamondFurnace", 150)
			.addEntry("steelFurnace", 250);

		config = new TomlConfigHandler(MOD_ID, toml);
	}


	// Blocks

	public static Block<?> furnaceIronIdle;
	public static Block<?> furnaceIronActive;
	public static Block<?> furnaceGoldIdle;
	public static Block<?> furnaceGoldActive;
	public static Block<?> furnaceDiamondIdle;
	public static Block<?> furnaceDiamondActive;
	public static Block<?> furnaceSteelIdle;
	public static Block<?> furnaceSteelActive;

	@Override
    public void onInitialize() {
		CommonEvents.BEFORE_GAME_START.listen(Key.of(MOD_ID), IronFurnacesEntities::beforeGameStart);
		CommonEvents.RECIPES_NAMESPACE_INIT.listen(Key.of(MOD_ID), IronFurnacesRecipes::initNamespaces);
		CommonEvents.RECIPES_READY.listen(Key.of(MOD_ID), IronFurnacesRecipes::onRecipesReady);

		BlockBuilder idleFurnaceBuilder = new BlockBuilder(MOD_ID)
			.setBlockSound(BlockSounds.METAL)
			.setHardness(5.0F)
			.setResistance(10.0F)
			//.setImmovable()
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			.setCreativeInventoryPlacement(new CreativeInventoryPlacement.Category(CreativeInventoryCategory.WORKBENCHES));

		BlockBuilder activeFurnaceBuilder = new BlockBuilder(MOD_ID)
			.setBlockSound(BlockSounds.METAL)
			.setHardness(5.0F)
			.setResistance(10.0F)
			.setLuminance(13)
			//.setImmovable()
			.setTags(BlockTags.NOT_IN_CREATIVE_MENU, BlockTags.MINEABLE_BY_PICKAXE);


		furnaceIronIdle = idleFurnaceBuilder
			.build("furnace.iron.idle", "furnace_iron_idle", config.getInt("IDs.ironFurnaceIdleID"), b -> new BlockLogicIronFurnace(b, false));

		furnaceIronActive = activeFurnaceBuilder
			.build("furnace.iron.active", "furnace_iron_active", furnaceIronIdle.id() + 1, b -> new BlockLogicIronFurnace(b, true));

		furnaceGoldIdle = idleFurnaceBuilder
			.build("furnace.gold.idle", "furnace_gold_idle", config.getInt("IDs.goldFurnaceIdleID"), b -> new BlockLogicGoldFurnace(b, false));

		furnaceGoldActive = activeFurnaceBuilder
			.build("furnace.gold.active", "furnace_gold_active", furnaceGoldIdle.id() + 1, b -> new BlockLogicGoldFurnace(b, true));

		furnaceDiamondIdle = idleFurnaceBuilder
			.build("furnace.diamond.idle", "furnace_diamond_idle", config.getInt("IDs.diamondFurnaceIdleID"), b -> new BlockLogicDiamondFurnace(b, false));

		furnaceDiamondActive = activeFurnaceBuilder
			.build("furnace.diamond.active", "furnace_diamond_active", furnaceDiamondIdle.id() + 1, b -> new BlockLogicDiamondFurnace(b, true));

		furnaceSteelIdle = idleFurnaceBuilder
			.setResistance(2000.0F)
			.build("furnace.steel.idle", "furnace_steel_idle", config.getInt("IDs.steelFurnaceIdleID"), b -> new BlockLogicSteelFurnace(b, false));

		furnaceSteelActive = activeFurnaceBuilder
			.setResistance(2000.0F)
			.build("furnace.steel.active", "furnace_steel_active", furnaceSteelIdle.id() + 1, b -> new BlockLogicSteelFurnace(b, true));

		LOGGER.info("IronFurnaces mod initialized.");
	}
}
