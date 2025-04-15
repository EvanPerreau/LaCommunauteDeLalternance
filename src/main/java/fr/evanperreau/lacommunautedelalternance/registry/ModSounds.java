package fr.evanperreau.lacommunautedelalternance.registry;

import fr.evanperreau.lacommunautedelalternance.LaCommunautedeLalternance;

import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * Registre des sons du mod.
 * Gère l'enregistrement de tous les sons dans le registre du jeu.
 * Implémente le pattern singleton pour assurer une instance unique.
 */
public class ModSounds {
    private static ModSounds instance;
    private final DeferredRegister<SoundEvent> SOUNDS;

    /**
     * Constructeur privé pour le pattern singleton.
     * Initialise le registre différé des sons.
     */
    private ModSounds() {
        SOUNDS = DeferredRegister.create(Registries.SOUND_EVENT, LaCommunautedeLalternance.MODID);
    }

    /**
     * Retourne l'instance unique de ModSounds.
     * 
     * @return L'instance unique de ModSounds
     */
    public static ModSounds getInstance() {
        if (instance == null) {
            instance = new ModSounds();
        }
        return instance;
    }

    /**
     * Retourne le registre différé des sons.
     * 
     * @return Le registre différé des sons
     */
    public DeferredRegister<SoundEvent> getSounds() {
        return SOUNDS;
    }

    /**
     * Enregistre le registre différé des sons.
     * Cette méthode doit être appelée pendant la phase d'initialisation du mod.
     * 
     * @param modEventBus Le bus d'événements du mod
     */
    public void registerSounds(IEventBus modEventBus) {
        SOUNDS.register(modEventBus);
    }
}
