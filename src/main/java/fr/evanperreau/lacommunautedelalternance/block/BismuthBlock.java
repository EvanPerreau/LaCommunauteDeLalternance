package fr.evanperreau.lacommunautedelalternance.block;

import java.util.function.Supplier;

import fr.evanperreau.lacommunautedelalternance.block.base.BaseBlock;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

/**
 * Block de bismuth pour le mod.
 * Implémente le pattern singleton pour assurer une instance unique.
 */
public class BismuthBlock extends BaseBlock {
    private final Supplier<Block> BLOCK;

    /**
     * Constructeur du bloc de bismuth.
     * Initialise le fournisseur de bloc avec les propriétés appropriées.
     */
    public BismuthBlock() {
        BLOCK = () -> new Block(BlockBehaviour.Properties.of()
                .strength(1.5f)
                .requiresCorrectToolForDrops());
    }
    
    /**
     * Retourne l'instance unique de BismuthBlock.
     * 
     * @return L'instance unique de BismuthBlock
     */
    public static BismuthBlock getInstance() {
        return BaseBlock.getInstance(BismuthBlock.class);
    }

    @Override
    public String getRegistryName() {
        return "bismuth_block";
    }

    @Override
    public Supplier<Block> getBlock() {
        return BLOCK;
    }
}