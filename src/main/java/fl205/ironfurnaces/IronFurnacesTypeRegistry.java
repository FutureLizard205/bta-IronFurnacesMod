package fl205.ironfurnaces;

import fl205.ironfurnaces.tileEntities.TileEntityCustomFurnace;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;


/**
 * Central registry of all Iron Furnaces furnace types (iron, gold, diamond, steel, etc).
 * <p>
 * <u>Why this exists</u>
 * <p>
 * When the server opens a furnace GUI, it sends the client a PacketContainerOpen packet.
 * That packet has a "slotsCount" field, which normally  is just the container's slot count,
 * but furnaces don't use, so it has been repurposed it to tell the client which
 * furnace tier to open (and which label to show). Despite the name, the "slotsCount"
 * value here has nothing to do with slots, think of it as a "furnaceTypeId" instead.
 * <p>
 * <u>How to add a new furnace type</u>
 * <p>
 * No need to touch this class, or use any mixin, whether you're
 * extending this mod or adding a furnace from a separate one. Just call
 * FurnaceTypeRegistry.register(...) once during your mod's init, with:
 * <p>
 *   - a unique "slotsCount" id (any positive int not already used by another registered type)
 * <p>
 *   - the TileEntityCustomFurnace subclass for your furnace
 * <p>
 *   - a translation key for its GUI label
 * <p>
 *   - a Supplier that creates a new instance of your tile entity
 * <p>
 * Example:
 * <p>
 *   FurnaceTypeRegistry.register(5, TileEntityPrismarineFurnace.class,
 *       "gui.mymod.furnace.prismarine.label.furnace", TileEntityPrismarineFurnace::new);
 */
public class IronFurnacesTypeRegistry {
	public record FurnaceType(int slotsCount,
							  Class<? extends TileEntityCustomFurnace> tileEntityClass,
	                          String labelKey,
							  Supplier<TileEntityCustomFurnace> factory) {}

	private static final Map<Integer, FurnaceType> BY_SLOTS_COUNT = new LinkedHashMap<>();
	private static final Map<Class<?>, FurnaceType> BY_CLASS = new LinkedHashMap<>();

	public static void register(int slotsCount,
								Class<? extends TileEntityCustomFurnace> tileEntityClass,
	                            String labelKey,
								Supplier<TileEntityCustomFurnace> factory) {
		FurnaceType type = new FurnaceType(slotsCount, tileEntityClass, labelKey, factory);
		BY_SLOTS_COUNT.put(slotsCount, type);
		BY_CLASS.put(tileEntityClass, type);
	}

	public static FurnaceType bySlotsCount(int slotsCount) {
		return BY_SLOTS_COUNT.get(slotsCount);
	}

	public static FurnaceType byTileEntity(TileEntityCustomFurnace tileEntity) {
		return BY_CLASS.get(tileEntity.getClass());
	}
}
