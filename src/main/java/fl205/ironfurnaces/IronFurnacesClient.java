package fl205.ironfurnaces;

import net.fabricmc.api.ClientModInitializer;
import turniplabs.halplibe.event.defs.ClientEvents;
import turniplabs.halplibe.util.dependency.Key;

import static fl205.ironfurnaces.IronFurnaces.MOD_ID;

public class IronFurnacesClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ClientEvents.BLOCK_MODEL_RELOAD.listen(Key.of(MOD_ID), (t) -> new IronFurnacesModels().initBlockModels(t));
	}
}
