package fr.evanperreau.lacommunautedelalternance.block.base;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import fr.evanperreau.lacommunautedelalternance.registry.ModBlock;
import fr.evanperreau.lacommunautedelalternance.registry.ModItems;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

/**
 * Classe de base pour les blocks du mod.
 * Permet d'enregistrer des blocks dans le registre du jeu.
 */
public abstract class BaseBlock {
    // Map pour stocker les instances par type de classe
    private static final Map<Class<? extends BaseBlock>, BaseBlock> INSTANCES = new HashMap<>();

    private DeferredBlock<Block> deferredBlock;

    /**
     * Retourne un fournisseur pour le block.
     * 
     * @return Le fournisseur de block
     */
    protected abstract Supplier<Block> getBlock();

    /**
     * Retourne le nom d'enregistrement du block.
     * 
     * @return Le nom d'enregistrement
     */
    protected abstract String getRegistryName();

    /**
     * Enregistre le block dans le registre du jeu.
     * Cette méthode doit être appelée pendant la phase d'initialisation du mod.
     */
    public void register() {
        // Stocke l'instance dans la Map en utilisant sa classe comme clé
        INSTANCES.put(this.getClass(), this);
        this.deferredBlock = registerDeferredBlock();
        registerBlockItem();
    }

    /**
     * Retourne le block différé enregistré.
     * 
     * @return Le block différé enregistré
     */
    public DeferredBlock<Block> getDeferredBlock() {
        return this.deferredBlock;
    }

    /**
     * Retourne l'instance d'un type spécifique de BaseBlock.
     * 
     * @param <T> Le type de BaseBlock
     * @param clazz La classe du type de BaseBlock
     * @return L'instance du type spécifié
     */
    @SuppressWarnings("unchecked")
    public static <T extends BaseBlock> T getInstance(Class<T> clazz) {
        BaseBlock instance = INSTANCES.get(clazz);
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
    public static BaseBlock getInstance() {
        throw new UnsupportedOperationException("Cette méthode doit être surchargée dans les classes concrètes");
    }

    /**
     * Enregistre le block dans le registre différé.
     * 
     * @return Le block différé enregistré
     */
    private DeferredBlock<Block> registerDeferredBlock() {
        return ModBlock.getInstance().getBlocks().register(getRegistryName(),
                properties -> getBlock().get());
    }

    /**
     * Enregistre l'item du block dans le registre des items.
     */
    private void registerBlockItem() {
        ModItems.getInstance().getItems().registerItem(getRegistryName(),
                properties -> new BlockItem(getDeferredBlock().get(), properties));
    }
}
