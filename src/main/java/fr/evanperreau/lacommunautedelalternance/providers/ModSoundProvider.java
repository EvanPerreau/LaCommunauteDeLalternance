package fr.evanperreau.lacommunautedelalternance.providers;

import fr.evanperreau.lacommunautedelalternance.sound.PoopSound;

/**
 * Fournisseur de sons pour le mod.
 * Gère l'enregistrement de tous les sons du mod.
 */
public class ModSoundProvider {
    /**
     * Enregistre tous les sons du mod.
     * Cette méthode doit être appelée pendant la phase d'initialisation du mod.
     */
    public static void register() {
        new PoopSound().register();
    }
}
