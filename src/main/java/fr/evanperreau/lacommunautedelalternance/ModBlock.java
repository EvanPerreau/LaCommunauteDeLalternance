package fr.evanperreau.lacommunautedelalternance;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlock {
    private static ModBlock INSTANCE;

    private DeferredRegister.Blocks BLOCKS;

    public static ModBlock getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ModBlock();
        }
        return INSTANCE;
    }

    public DeferredRegister.Blocks getBlocks() {
        return BLOCKS;
    }

    private ModBlock() {
        BLOCKS = DeferredRegister.createBlocks(LaCommunautedeLalternance.MODID);
    }

    public void registerBlocks(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
    }
}
