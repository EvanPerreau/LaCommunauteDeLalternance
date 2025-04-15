package fr.evanperreau.lacommunautedelalternance.effect.base;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import fr.evanperreau.lacommunautedelalternance.registry.ModEffects;

import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;

/**
 * Classe abstraite de base pour les effets du mod.
 * Permet d'enregistrer des effets dans le registre du jeu.
 */
public abstract class BaseEffect {

    // Map pour stocker les instances par type de classe
    private static final Map<Class<? extends BaseEffect>, BaseEffect> INSTANCES = new HashMap<>();

    private DeferredHolder<MobEffect, MobEffect> deferredEffect;

    /**
     * Enregistre l'effet dans le registre du jeu.
     * Cette méthode doit être appelée pendant la phase d'initialisation du mod.
     */
    public void register() {
        // Stocke l'instance dans la Map en utilisant sa classe comme clé
        INSTANCES.put(this.getClass(), this);
        this.deferredEffect = registerDeferredEffect();
    }

    /**
     * Retourne l'effet différé enregistré.
     * 
     * @return L'effet différé enregistré
     */
    public DeferredHolder<MobEffect, MobEffect> getDeferredEffect() {
        return this.deferredEffect;
    }

    /**
     * Retourne le nom d'enregistrement de l'effet.
     * 
     * @return Le nom d'enregistrement
     */
    public abstract String getRegistryName();

    /**
     * Retourne un fournisseur pour l'effet.
     * 
     * @return Le fournisseur d'effet
     */
    protected abstract Supplier<MobEffect> getEffect();

    /**
     * Enregistre l'effet dans le registre différé et retourne l'effet différé.
     * 
     * @return L'effet différé enregistré
     */
    private DeferredHolder<MobEffect, MobEffect> registerDeferredEffect() {
        return ModEffects.getInstance().getEffects().register(getRegistryName(),
                () -> getEffect().get());
    }

    /**
     * Retourne l'instance d'un type spécifique de BaseEffect.
     * 
     * @param <T>   Le type de BaseEffect
     * @param clazz La classe du type de BaseEffect
     * @return L'instance du type spécifié
     */
    @SuppressWarnings("unchecked")
    public static <T extends BaseEffect> T getInstance(Class<T> clazz) {
        BaseEffect instance = INSTANCES.get(clazz);
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
    public static BaseEffect getInstance() {
        throw new UnsupportedOperationException("Cette méthode doit être surchargée dans les classes concrètes");
    }
}
