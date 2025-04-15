package fr.evanperreau.lacommunautedelalternance.creativetabs.contract;

import net.minecraft.world.item.CreativeModeTab.ItemDisplayParameters;
import net.minecraft.world.item.CreativeModeTab.Output;

/**
 * Interface contractuelle pour les loaders des onglets créatifs.
 * 
 * @author Evan Perreau
 */
public interface ICreativeTabLoader {
    /**
     * Enregistre les items dans l'onglet créatif.
     * 
     * @param itemDisplayParameters Les paramètres d'affichage des items
     * @param ctx                   Le contexte de l'onglet créatif
     */
    void register(ItemDisplayParameters itemDisplayParameters, Output ctx);
}
