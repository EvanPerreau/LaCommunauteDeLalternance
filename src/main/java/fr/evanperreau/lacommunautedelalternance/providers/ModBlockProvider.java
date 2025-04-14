package fr.evanperreau.lacommunautedelalternance.providers;

import fr.evanperreau.lacommunautedelalternance.block.BismuthBlock;

/**
 * Fournisseur de blocks pour le mod.
 * Gère l'enregistrement de tous les blocks du mod.
 */
public class ModBlockProvider {
    /**
     * Enregistre tous les blocks du mod.
     * Cette méthode doit être appelée pendant la phase d'initialisation du mod.
     */
    public static void register() {
        new BismuthBlock().register();
    }
}
