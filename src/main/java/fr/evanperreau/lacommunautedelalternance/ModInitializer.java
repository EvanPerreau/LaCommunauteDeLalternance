package fr.evanperreau.lacommunautedelalternance;

import fr.evanperreau.lacommunautedelalternance.providers.ModBlockProvider;
import fr.evanperreau.lacommunautedelalternance.providers.ModCreativeTabProvider;
import fr.evanperreau.lacommunautedelalternance.providers.ModEffectProvider;
import fr.evanperreau.lacommunautedelalternance.providers.ModItemProvider;
import fr.evanperreau.lacommunautedelalternance.providers.ModSoundProvider;
import fr.evanperreau.lacommunautedelalternance.registry.ModBlock;
import fr.evanperreau.lacommunautedelalternance.registry.ModCreativeTabs;
import fr.evanperreau.lacommunautedelalternance.registry.ModEffects;
import fr.evanperreau.lacommunautedelalternance.registry.ModItems;
import fr.evanperreau.lacommunautedelalternance.registry.ModSounds;

import net.neoforged.bus.api.IEventBus;

public class ModInitializer {
    public static void init(IEventBus modEventBus) {
        ModItemProvider.register();
        ModItems.getInstance().registerItems(modEventBus);
        ModBlockProvider.register();
        ModBlock.getInstance().registerBlocks(modEventBus);
        ModCreativeTabProvider.register();
        ModCreativeTabs.getInstance().registerCreativeTabs(modEventBus);
        ModEffectProvider.register();
        ModEffects.getInstance().registerEffects(modEventBus);
        ModSoundProvider.register();
        ModSounds.getInstance().registerSounds(modEventBus);
    }
}
