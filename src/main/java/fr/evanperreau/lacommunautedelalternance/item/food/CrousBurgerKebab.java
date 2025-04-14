package fr.evanperreau.lacommunautedelalternance.item.food;

import java.util.function.Supplier;

import fr.evanperreau.lacommunautedelalternance.item.base.BaseItem;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

/**
 * Item de nourriture représentant un burger kebab du CROUS.
 * Implémente le pattern singleton pour assurer une instance unique.
 */
public class CrousBurgerKebab extends BaseItem {
    private final Supplier<Item> ITEM;

    /**
     * Constructeur de l'item CrousBurgerKebab.
     * Initialise le fournisseur d'item avec les propriétés de nourriture
     * appropriées.
     */
    public CrousBurgerKebab() {
        ITEM = () -> new Item(
                new Item.Properties()
                        .food(new FoodProperties.Builder()
                                .nutrition(10)
                                .saturationModifier(0.6f)
                                .effect(() -> new MobEffectInstance(MobEffects.WEAKNESS, 100, 0), 1f)
                                .build()));
    }

    /**
     * Retourne l'instance unique de CrousBurgerKebab.
     * 
     * @return L'instance unique de CrousBurgerKebab
     */
    public static CrousBurgerKebab getInstance() {
        return BaseItem.getInstance(CrousBurgerKebab.class);
    }

    @Override
    public String getRegistryName() {
        return "crous_burger_kebab";
    }

    @Override
    public Supplier<Item> getItem() {
        return ITEM;
    }
}
