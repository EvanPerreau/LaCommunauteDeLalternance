package fr.evanperreau.lacommunautedelalternance.providers;

import fr.evanperreau.lacommunautedelalternance.item.food.CrousBurgerKebab;

/**
 * Fournisseur d'items pour le mod.
 * Gère l'enregistrement de tous les items du mod.
 */
public class ModItemProvider {
    /**
     * Enregistre tous les items du mod.
     * Cette méthode doit être appelée pendant la phase d'initialisation du mod.
     */
    public static void register() {
        new CrousBurgerKebab().register();
    }
}
