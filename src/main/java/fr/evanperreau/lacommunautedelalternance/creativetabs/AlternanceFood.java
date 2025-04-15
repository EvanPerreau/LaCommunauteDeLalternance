package fr.evanperreau.lacommunautedelalternance.creativetabs;

import java.util.function.Supplier;

import fr.evanperreau.lacommunautedelalternance.creativetabs.base.BaseCreativeTab;
import fr.evanperreau.lacommunautedelalternance.creativetabs.contract.ICreativeTabLoader;
import fr.evanperreau.lacommunautedelalternance.creativetabs.loader.AlternanceFoodLoader;
import fr.evanperreau.lacommunautedelalternance.item.food.CrousBurgerKebab;

import net.minecraft.world.item.Item;

public class AlternanceFood extends BaseCreativeTab {

    @Override
    public String getRegistryName() {
        return "alternance_food";
    }

    @Override
    public Supplier<Item> getIcon() {
        return () -> CrousBurgerKebab.getInstance().getDeferredItem().get();
    }

    @Override
    protected ICreativeTabLoader getLoader() {
        return new AlternanceFoodLoader();
    }

    public static AlternanceFood getInstance() {
        return BaseCreativeTab.getInstance(AlternanceFood.class);
    }
}
