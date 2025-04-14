package fr.evanperreau.lacommunautedelalternance.block.base;

import java.util.function.Supplier;

import fr.evanperreau.lacommunautedelalternance.ModBlock;
import fr.evanperreau.lacommunautedelalternance.ModItems;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;

/**
 * Classe de base pour les blocks du mod.
 * Permet d'enregistrer des blocks dans le registre du jeu.
 */
public abstract class BaseBlock {
    private static BaseBlock INSTANCE;

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
        INSTANCE = this;
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
     * Retourne l'instance de BaseBlock.
     * 
     * @return L'instance de BaseBlock
     */
    public static BaseBlock getInstance() {
        if (INSTANCE == null) {
            throw new RuntimeException("BaseBlock instance not initialized");
        }
        return INSTANCE;
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
