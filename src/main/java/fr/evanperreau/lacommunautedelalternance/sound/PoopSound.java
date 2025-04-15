package fr.evanperreau.lacommunautedelalternance.sound;

import java.util.function.Supplier;

import fr.evanperreau.lacommunautedelalternance.LaCommunautedeLalternance;
import fr.evanperreau.lacommunautedelalternance.sound.base.BaseSound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

/**
 * Son émis lors de l'activation de l'effet BigPoop.
 * Implémente le pattern singleton pour assurer une instance unique.
 */
public class PoopSound extends BaseSound {
    private final Supplier<SoundEvent> SOUND;

    /**
     * Constructeur du son PoopSound.
     * Initialise le fournisseur de son avec les propriétés appropriées.
     */
    public PoopSound() {
        SOUND = () -> SoundEvent.createVariableRangeEvent(
                ResourceLocation.fromNamespaceAndPath(LaCommunautedeLalternance.MODID, getRegistryName()));
    }

    /**
     * Retourne l'instance unique de PoopSound.
     * 
     * @return L'instance unique de PoopSound
     */
    public static PoopSound getInstance() {
        return BaseSound.getInstance(PoopSound.class);
    }

    @Override
    public String getRegistryName() {
        return "poop_sound";
    }

    @Override
    protected Supplier<SoundEvent> getSound() {
        return SOUND;
    }
}
