package fl205.ironfurnaces;

import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.block.model.BlockModelHorizontalRotation;
import net.minecraft.core.util.helper.Side;

import static fl205.ironfurnaces.IronFurnaces.*;

public class IronFurnacesModels {
	public void initBlockModels(BlockModelDispatcher dispatcher) {
		dispatcher.addDispatch(furnaceIronIdle, new BlockModelHorizontalRotation<>(furnaceIronIdle).setTex("ironfurnaces:block/ironfurnacetop", Side.TOP)
			.setTex("ironfurnaces:block/ironfurnacebottom", Side.BOTTOM)
			.setTex("ironfurnaces:block/ironfurnaceside", Side.WEST, Side.EAST, Side.SOUTH)
			.setTex("ironfurnaces:block/ironfurnaceidlefront", Side.NORTH));

		dispatcher.addDispatch(furnaceIronActive, new BlockModelHorizontalRotation<>(furnaceIronActive)
			.setTex("ironfurnaces:block/ironfurnacetop", Side.TOP)
			.setTex("ironfurnaces:block/ironfurnacebottom", Side.BOTTOM)
			.setTex("ironfurnaces:block/ironfurnaceside", Side.WEST, Side.EAST, Side.SOUTH)
			.setTex("ironfurnaces:block/ironfurnaceactivefront", Side.NORTH));

		dispatcher.addDispatch(furnaceGoldIdle, new BlockModelHorizontalRotation<>(furnaceGoldIdle)
			.setTex("ironfurnaces:block/goldfurnacetop", Side.TOP)
			.setTex("ironfurnaces:block/goldfurnacebottom", Side.BOTTOM)
			.setTex("ironfurnaces:block/goldfurnaceside", Side.WEST, Side.EAST, Side.SOUTH)
			.setTex("ironfurnaces:block/goldfurnaceidlefront", Side.NORTH));

		dispatcher.addDispatch(furnaceGoldActive, new BlockModelHorizontalRotation<>(furnaceGoldActive)
			.setTex("ironfurnaces:block/goldfurnacetop", Side.TOP)
			.setTex("ironfurnaces:block/goldfurnacebottom", Side.BOTTOM)
			.setTex("ironfurnaces:block/goldfurnaceside", Side.WEST, Side.EAST, Side.SOUTH)
			.setTex("ironfurnaces:block/goldfurnaceactivefront", Side.NORTH));

		dispatcher.addDispatch(furnaceDiamondIdle, new BlockModelHorizontalRotation<>(furnaceDiamondIdle)
			.setTex("ironfurnaces:block/diamondfurnacetop", Side.TOP)
			.setTex("ironfurnaces:block/diamondfurnacebottom", Side.BOTTOM)
			.setTex("ironfurnaces:block/diamondfurnaceside", Side.WEST, Side.EAST, Side.SOUTH)
			.setTex("ironfurnaces:block/diamondfurnaceidlefront", Side.NORTH));

		dispatcher.addDispatch(furnaceDiamondActive, new BlockModelHorizontalRotation<>(furnaceDiamondActive)
			.setTex("ironfurnaces:block/diamondfurnacetop", Side.TOP)
			.setTex("ironfurnaces:block/diamondfurnacebottom", Side.BOTTOM)
			.setTex("ironfurnaces:block/diamondfurnaceside", Side.WEST, Side.EAST, Side.SOUTH)
			.setTex("ironfurnaces:block/diamondfurnaceactivefront", Side.NORTH));

		dispatcher.addDispatch(furnaceSteelIdle, new BlockModelHorizontalRotation<>(furnaceSteelIdle)
			.setTex("ironfurnaces:block/steelfurnacetop", Side.TOP)
			.setTex("ironfurnaces:block/steelfurnacebottom", Side.BOTTOM)
			.setTex("ironfurnaces:block/steelfurnaceside", Side.WEST, Side.EAST, Side.SOUTH)
			.setTex("ironfurnaces:block/steelfurnaceidlefront", Side.NORTH));

		dispatcher.addDispatch(furnaceSteelActive, new BlockModelHorizontalRotation<>(furnaceSteelActive)
			.setTex("ironfurnaces:block/steelfurnacetop", Side.TOP)
			.setTex("ironfurnaces:block/steelfurnacebottom", Side.BOTTOM)
			.setTex("ironfurnaces:block/steelfurnaceside", Side.WEST, Side.EAST, Side.SOUTH)
			.setTex("ironfurnaces:block/steelfurnaceactivefront", Side.NORTH));
	}
}
