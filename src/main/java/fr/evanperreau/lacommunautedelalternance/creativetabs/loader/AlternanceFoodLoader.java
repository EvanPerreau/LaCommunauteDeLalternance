package fr.evanperreau.lacommunautedelalternance.creativetabs.loader;

import fr.evanperreau.lacommunautedelalternance.creativetabs.contract.ICreativeTabLoader;
import fr.evanperreau.lacommunautedelalternance.item.food.CrousBurgerKebab;

import net.minecraft.world.item.CreativeModeTab.ItemDisplayParameters;
import net.minecraft.world.item.CreativeModeTab.Output;

public class AlternanceFoodLoader implements ICreativeTabLoader {
    @Override
    public void register(ItemDisplayParameters itemDisplayParameters, Output ctx) {
        ctx.accept(CrousBurgerKebab.getInstance().getDeferredItem().get());
    }
}
