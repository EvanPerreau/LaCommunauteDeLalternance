package fr.evanperreau.lacommunautedelalternance.creativetabs.base;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import fr.evanperreau.lacommunautedelalternance.LaCommunautedeLalternance;
import fr.evanperreau.lacommunautedelalternance.creativetabs.contract.ICreativeTabLoader;
import fr.evanperreau.lacommunautedelalternance.registry.ModCreativeTabs;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;

/**
 * Classe abstraite de base pour les onglets créatifs du mod.
 * Permet d'enregistrer des onglets créatifs dans le registre du jeu.
 */
public abstract class BaseCreativeTab {

    // Map pour stocker les instances par type de classe
    private static final Map<Class<? extends BaseCreativeTab>, BaseCreativeTab> INSTANCES = new HashMap<>();

    private DeferredHolder<CreativeModeTab, CreativeModeTab> deferredCreativeTab;

    /**
     * Retourne le nom d'enregistrement de l'onglet créatif.
     * 
     * @return Le nom d'enregistrement
     */
    public abstract String getRegistryName();

    /**
     * Retourne un fournisseur pour l'icône de l'onglet créatif.
     * 
     * @return Le fournisseur d'icône
     */
    public abstract Supplier<Item> getIcon();

    /**
     * Retourne le loader utilisé pour enregistrer les items dans l'onglet créatif.
     * 
     * @return Le loader utilisé
     */
    protected abstract ICreativeTabLoader getLoader();

    /**
     * Enregistre l'onglet créatif dans le registre du jeu.
     * Cette méthode doit être appelée pendant la phase d'initialisation du mod.
     */
    public void register() {
        // Stocke l'instance dans la Map en utilisant sa classe comme clé
        INSTANCES.put(this.getClass(), this);
        this.deferredCreativeTab = registerDeferredCreativeTab();
    }

    /**
     * Enregistre l'onglet créatif dans le registre différé et retourne l'onglet
     * différé.
     * 
     * @return L'onglet créatif différé enregistré
     */
    private DeferredHolder<CreativeModeTab, CreativeModeTab> registerDeferredCreativeTab() {
        return ModCreativeTabs.getInstance().getCreativeTabs().register(getRegistryName(),
                () -> CreativeModeTab.builder()
                        .title(Component.translatable(
                                "creativetab." + LaCommunautedeLalternance.MODID + "." + getRegistryName()))
                        .icon(() -> new ItemStack(getIcon().get()))
                        .displayItems((itemDisplayParameters, output) -> {
                            getLoader().register(itemDisplayParameters, output);
                        })
                        .build());
    }

    /**
     * Retourne l'onglet créatif différé enregistré.
     * 
     * @return L'onglet créatif différé enregistré
     */
    public DeferredHolder<CreativeModeTab, CreativeModeTab> getDeferredCreativeTab() {
        return this.deferredCreativeTab;
    }

    /**
     * Retourne l'instance d'un type spécifique de BaseCreativeTab.
     * 
     * @param <T>   Le type de BaseCreativeTab
     * @param clazz La classe du type de BaseCreativeTab
     * @return L'instance du type spécifié
     */
    @SuppressWarnings("unchecked")
    public static <T extends BaseCreativeTab> T getInstance(Class<T> clazz) {
        BaseCreativeTab instance = INSTANCES.get(clazz);
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
    public static BaseCreativeTab getInstance() {
        throw new UnsupportedOperationException("Cette méthode doit être surchargée dans les classes concrètes");
    }
}
