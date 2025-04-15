package fr.evanperreau.lacommunautedelalternance.sound.base;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import fr.evanperreau.lacommunautedelalternance.registry.ModSounds;

import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;

/**
 * Classe abstraite de base pour les sons du mod.
 * Permet d'enregistrer des sons dans le registre du jeu.
 */
public abstract class BaseSound {

    // Map pour stocker les instances par type de classe
    private static final Map<Class<? extends BaseSound>, BaseSound> INSTANCES = new HashMap<>();

    private DeferredHolder<SoundEvent, SoundEvent> deferredSound;

    /**
     * Enregistre le son dans le registre du jeu.
     * Cette méthode doit être appelée pendant la phase d'initialisation du mod.
     */
    public void register() {
        // Stocke l'instance dans la Map en utilisant sa classe comme clé
        INSTANCES.put(this.getClass(), this);
        this.deferredSound = registerDeferredSound();
    }

    /**
     * Retourne le son différé enregistré.
     * 
     * @return Le son différé enregistré
     */
    public DeferredHolder<SoundEvent, SoundEvent> getDeferredSound() {
        return this.deferredSound;
    }

    /**
     * Retourne le nom d'enregistrement du son.
     * 
     * @return Le nom d'enregistrement
     */
    public abstract String getRegistryName();

    /**
     * Retourne un fournisseur pour le son.
     * 
     * @return Le fournisseur de son
     */
    protected abstract Supplier<SoundEvent> getSound();

    /**
     * Enregistre le son dans le registre différé et retourne le son différé.
     * 
     * @return Le son différé enregistré
     */
    private DeferredHolder<SoundEvent, SoundEvent> registerDeferredSound() {
        return ModSounds.getInstance().getSounds().register(getRegistryName(),
                () -> getSound().get());
    }

    /**
     * Retourne l'instance d'un type spécifique de BaseSound.
     * 
     * @param <T>   Le type de BaseSound
     * @param clazz La classe du type de BaseSound
     * @return L'instance du type spécifié
     */
    @SuppressWarnings("unchecked")
    public static <T extends BaseSound> T getInstance(Class<T> clazz) {
        BaseSound instance = INSTANCES.get(clazz);
        if (instance == null) {
            throw new RuntimeException("Instance of " + clazz.getSimpleName() + " not initialized");
        }
        return (T) instance;
    }

    /**
     * Méthode à utiliser dans les classes concrètes pour obtenir leur instance.
     * 
     * @return L'instance de la classe concrète
     */
    public static BaseSound getInstance() {
        throw new UnsupportedOperationException("Cette méthode doit être surchargée dans les classes concrètes");
    }
}
