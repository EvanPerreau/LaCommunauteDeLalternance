package fr.evanperreau.lacommunautedelalternance.registry;

import fr.evanperreau.lacommunautedelalternance.LaCommunautedeLalternance;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    private static ModItems INSTANCE;
    private DeferredRegister.Items ITEMS;

    public static ModItems getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ModItems();
        }
        return INSTANCE;
    }

    private ModItems() {
        ITEMS = DeferredRegister.createItems(LaCommunautedeLalternance.MODID);
    }

    public DeferredRegister.Items getItems() {
        return ITEMS;
    }

    public void registerItems(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}
