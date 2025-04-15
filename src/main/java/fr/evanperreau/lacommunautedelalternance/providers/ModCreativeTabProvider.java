package fr.evanperreau.lacommunautedelalternance.providers;

import fr.evanperreau.lacommunautedelalternance.creativetabs.AlternanceFood;

/**
 * Fournisseur d'onglets créatifs pour le mod.
 * Gère l'enregistrement de tous les onglets créatifs du mod.
 */
public class ModCreativeTabProvider {
    /**
     * Enregistre tous les onglets créatifs du mod.
     * Cette méthode doit être appelée pendant la phase d'initialisation du mod.
     */
    public static void register() {
        new AlternanceFood().register();
    }
}
