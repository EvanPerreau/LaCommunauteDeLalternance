package fr.evanperreau.lacommunautedelalternance.providers;

import fr.evanperreau.lacommunautedelalternance.effect.BigPoopEffect;

/**
 * Fournisseur d'effets pour le mod.
 * Gère l'enregistrement de tous les effets du mod.
 */
public class ModEffectProvider {
    /**
     * Enregistre tous les effets du mod.
     * Cette méthode doit être appelée pendant la phase d'initialisation du mod.
     */
    public static void register() {
        new BigPoopEffect().register();
    }
}
