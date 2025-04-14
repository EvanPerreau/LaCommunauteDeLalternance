package fr.evanperreau.lacommunautedelalternance;

import fr.evanperreau.lacommunautedelalternance.providers.ModBlockProvider;
import fr.evanperreau.lacommunautedelalternance.providers.ModItemProvider;

import net.neoforged.bus.api.IEventBus;

public class ModInitializer {
    public static void init(IEventBus modEventBus) {
        ModItemProvider.register();
        ModItems.getInstance().registerItems(modEventBus);
        ModBlockProvider.register();
        ModBlock.getInstance().registerBlocks(modEventBus);
    }
}
