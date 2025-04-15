package fr.evanperreau.lacommunautedelalternance.registry;

import fr.evanperreau.lacommunautedelalternance.LaCommunautedeLalternance;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * Registre des effets du mod.
 * Gère l'enregistrement de tous les effets dans le registre du jeu.
 * Implémente le pattern singleton pour assurer une instance unique.
 */
public class ModEffects {
    private static ModEffects instance;
    private final DeferredRegister<MobEffect> EFFECTS;

    /**
     * Constructeur privé pour le pattern singleton.
     * Initialise le registre différé des effets.
     */
    private ModEffects() {
        EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, LaCommunautedeLalternance.MODID);
    }

    /**
     * Retourne l'instance unique de ModEffects.
     * 
     * @return L'instance unique de ModEffects
     */
    public static ModEffects getInstance() {
        if (instance == null) {
            instance = new ModEffects();
        }
        return instance;
    }

    /**
     * Retourne le registre différé des effets.
     * 
     * @return Le registre différé des effets
     */
    public DeferredRegister<MobEffect> getEffects() {
        return EFFECTS;
    }

    /**
     * Enregistre le registre différé des effets.
     * Cette méthode doit être appelée pendant la phase d'initialisation du mod.
     */
    public void registerEffects(IEventBus modEventBus) {
        EFFECTS.register(modEventBus);
    }
}
