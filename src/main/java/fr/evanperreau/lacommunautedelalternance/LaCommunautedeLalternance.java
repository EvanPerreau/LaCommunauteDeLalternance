package fr.evanperreau.lacommunautedelalternance;

import com.mojang.logging.LogUtils;

import org.slf4j.Logger;

import fr.evanperreau.lacommunautedelalternance.block.BismuthBlock;
import fr.evanperreau.lacommunautedelalternance.item.food.CrousBurgerKebab;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(LaCommunautedeLalternance.MODID)
public class LaCommunautedeLalternance {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "lacommunautedelalternance";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister
            .create(Registries.CREATIVE_MODE_TAB, LaCommunautedeLalternance.MODID);

    // The constructor for the mod class is the first code that is run when your mod
    // is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and
    // pass them in automatically.
    public LaCommunautedeLalternance(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Initialize mod elements
        ModInitializer.init(modEventBus);

        CREATIVE_MODE_TABS.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config
        // file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    /**
     * Ajoute les items et blocks du mod aux onglets créatifs appropriés.
     * 
     * @param event L'événement de construction des onglets créatifs
     */
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            // Utilisation de l'objet enregistré via le fournisseur
            event.accept(CrousBurgerKebab.getInstance().getDeferredItem());
            event.accept(BismuthBlock.getInstance().getDeferredBlock());
        }
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Initializing mod: {}", MODID);
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Server starting for mod: {}", MODID);
    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            LOGGER.info("Client setup for mod: {}", MODID);
        }
    }
}
