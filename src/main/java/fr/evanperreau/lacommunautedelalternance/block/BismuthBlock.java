package fr.evanperreau.lacommunautedelalternance.block;

import java.util.function.Supplier;

import fr.evanperreau.lacommunautedelalternance.block.base.BaseBlock;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

/**
 * Block de bismuth pour le mod.
 */
public class BismuthBlock extends BaseBlock {
    private final Supplier<Block> BLOCK;

    public BismuthBlock() {
        BLOCK = () -> new Block(BlockBehaviour.Properties.of()
                .strength(1.5f)
                .requiresCorrectToolForDrops());
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