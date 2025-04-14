package fr.evanperreau.lacommunautedelalternance.item.base;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import fr.evanperreau.lacommunautedelalternance.ModItems;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

/**
 * Classe abstraite de base pour les items du mod.
 * Permet d'enregistrer des items dans le registre du jeu.
 */
public abstract class BaseItem {

    // Map pour stocker les instances par type de classe
    private static final Map<Class<? extends BaseItem>, BaseItem> INSTANCES = new HashMap<>();

    private DeferredItem<Item> deferredItem;

    /**
     * Enregistre l'item dans le registre du jeu.
     * Cette méthode doit être appelée pendant la phase d'initialisation du mod.
     */
    public void register() {
        // Stocke l'instance dans la Map en utilisant sa classe comme clé
        INSTANCES.put(this.getClass(), this);
        this.deferredItem = registerDeferredItem();
    }

    /**
     * Retourne l'item différé enregistré.
     * 
     * @return L'item différé enregistré
     */
    public DeferredItem<Item> getDeferredItem() {
        return this.deferredItem;
    }

    /**
     * Retourne le nom d'enregistrement de l'item.
     * 
     * @return Le nom d'enregistrement
     */
    public abstract String getRegistryName();

    /**
     * Retourne un fournisseur pour l'item.
     * 
     * @return Le fournisseur d'item
     */
    protected abstract Supplier<Item> getItem();

    /**
     * Enregistre l'item dans le registre différé et retourne l'item différé.
     * 
     * @return L'item différé enregistré
     */
    private DeferredItem<Item> registerDeferredItem() {
        return ModItems.getInstance().getItems().registerItem(getRegistryName(),
                properties -> getItem().get());
    }

    /**
     * Retourne l'instance d'un type spécifique de BaseItem.
     * 
     * @param <T> Le type de BaseItem
     * @param clazz La classe du type de BaseItem
     * @return L'instance du type spécifié
     */
    @SuppressWarnings("unchecked")
    public static <T extends BaseItem> T getInstance(Class<T> clazz) {
        BaseItem instance = INSTANCES.get(clazz);
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
    public static BaseItem getInstance() {
        throw new UnsupportedOperationException("Cette méthode doit être surchargée dans les classes concrètes");
    }
}
