package fr.evanperreau.lacommunautedelalternance.registry;

import fr.evanperreau.lacommunautedelalternance.LaCommunautedeLalternance;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * Registre des onglets créatifs du mod.
 * Gère l'enregistrement de tous les onglets créatifs dans le registre du jeu.
 * Implémente le pattern singleton pour assurer une instance unique.
 */
public class ModCreativeTabs {
    private static ModCreativeTabs instance;
    private final DeferredRegister<CreativeModeTab> CREATIVE_TABS;

    /**
     * Constructeur privé pour le pattern singleton.
     * Initialise le registre différé des onglets créatifs.
     */
    private ModCreativeTabs() {
        CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, LaCommunautedeLalternance.MODID);
    }

    /**
     * Retourne l'instance unique de ModCreativeTabs.
     * 
     * @return L'instance unique de ModCreativeTabs
     */
    public static ModCreativeTabs getInstance() {
        if (instance == null) {
            instance = new ModCreativeTabs();
        }
        return instance;
    }

    /**
     * Retourne le registre différé des onglets créatifs.
     * 
     * @return Le registre différé des onglets créatifs
     */
    public DeferredRegister<CreativeModeTab> getCreativeTabs() {
        return CREATIVE_TABS;
    }

    /**
     * Enregistre le registre différé des onglets créatifs.
     * Cette méthode doit être appelée pendant la phase d'initialisation du mod.
     */
    public void registerCreativeTabs(IEventBus modEventBus) {
        CREATIVE_TABS.register(modEventBus);
    }
}
