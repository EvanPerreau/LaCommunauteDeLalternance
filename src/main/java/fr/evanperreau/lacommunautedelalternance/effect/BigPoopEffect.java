package fr.evanperreau.lacommunautedelalternance.effect;

import java.util.function.Supplier;

import org.joml.Vector3f;

import fr.evanperreau.lacommunautedelalternance.effect.base.BaseEffect;
import fr.evanperreau.lacommunautedelalternance.sound.PoopSound;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

/**
 * Effet qui permet au joueur de sauter plus haut lorsqu'il est accroupi.
 * Implémente le pattern singleton pour assurer une instance unique.
 */
public class BigPoopEffect extends BaseEffect {
    private final Supplier<MobEffect> EFFECT;

    /**
     * Constructeur de l'effet BigPoopEffect.
     * Initialise le fournisseur d'effet avec les propriétés appropriées.
     */
    public BigPoopEffect() {
        EFFECT = () -> new BigPoopMobEffect(MobEffectCategory.NEUTRAL, 0x663300);
    }

    /**
     * Retourne l'instance unique de BigPoopEffect.
     * 
     * @return L'instance unique de BigPoopEffect
     */
    public static BigPoopEffect getInstance() {
        return BaseEffect.getInstance(BigPoopEffect.class);
    }

    @Override
    public String getRegistryName() {
        return "big_poop_effect";
    }

    @Override
    public Supplier<MobEffect> getEffect() {
        return EFFECT;
    }

    /**
     * Classe interne qui définit le comportement de l'effet.
     */
    private static class BigPoopMobEffect extends MobEffect {
        public BigPoopMobEffect(MobEffectCategory category, int color) {
            super(category, color);
        }

        @Override
        @SuppressWarnings("null")
        public boolean applyEffectTick(LivingEntity entity, int amplifier) {
            if (entity instanceof Player player) {
                if (checkAndApplyPoopEffect(player)) {
                    return true;
                }
            }
            return super.applyEffectTick(entity, amplifier);
        }

        private boolean checkAndApplyPoopEffect(Player player) {
            if (player.isShiftKeyDown()) {
                removeBedrock(player);
                applyJumpEffect(player);
                player.level().playSound(null, player, PoopSound.getInstance().getDeferredSound().get(),
                        SoundSource.PLAYERS, 5.0f, 1.0f);
                applyParticles(player);
                removeEffect(player);
                return true;
            }
            return false;
        }

        /**
         * Applique l'effet de saut au joueur et génère des particules marrons en
         * dessous.
         * 
         * @param player Le joueur sur lequel appliquer l'effet
         */
        private void applyJumpEffect(Player player) {
            // Obtenir le mouvement initial du joueur
            Vec3 initialMotion = player.getDeltaMovement();

            // Calculer la nouvelle vélocité pour le saut
            Vec3 jumpVec3 = new Vec3(initialMotion.x, initialMotion.y + 1.5, initialMotion.z);
            player.setDeltaMovement(jumpVec3);

        }

        @Override
        public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
            return true;
        }

        private void removeBedrock(Player player) {
            if (player.getBlockStateOn() != null) {
                if (player.getBlockStateOn().is(Blocks.BEDROCK)) {
                    player.level().setBlock(player.blockPosition().below(),
                            Blocks.AIR.defaultBlockState(), 3);
                }
            }
        }

        private void removeEffect(Player player) {
            Thread removeEffect = new Thread(() -> {
                try {
                    Thread.sleep(10);
                    player.removeEffect(BigPoopEffect.getInstance().getDeferredEffect());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            removeEffect.start();
        }

        private void applyParticles(Player player) {
            // Créer une particule de poussière marron
            ParticleOptions brownParticle = new DustParticleOptions(new Vector3f(0.4f,
                    0.2f, 0.0f), 1.0f);

            // Démarrer un thread pour générer des particules pendant toute la hauteur du
            // saut
            Thread particleThread = new Thread(() -> {
                try {
                    // Suivre la position initiale du joueur
                    double startY = player.getY();
                    double maxHeight = startY + 3.0; // Estimation de la hauteur maximale du saut

                    // Générer des particules jusqu'à ce que le joueur redescende
                    while (player.getY() >= startY && player.getY() <= maxHeight) {
                        // Position des particules (légèrement en dessous du joueur)
                        double x = player.getX();
                        double y = player.getY() - 0.2;
                        double z = player.getZ();

                        // Ajouter beaucoup plus de particules avec dispersion aléatoire
                        for (int i = 0; i < 50; i++) {
                            // Dispersion plus large pour couvrir une zone plus grande
                            double offsetX = (Math.random() - 0.5) * 1.5;
                            double offsetZ = (Math.random() - 0.5) * 1.5;
                            // Variation de hauteur pour créer un effet de cascade
                            double offsetY = (Math.random() - 0.5) * 0.8;

                            // Ajouter la particule au monde
                            player.level().addParticle(
                                    brownParticle,
                                    x + offsetX, y + offsetY, z + offsetZ,
                                    0, -0.05, 0); // Légère vitesse vers le bas
                        }

                        // Attendre un court instant avant la prochaine vague de particules
                        // Intervalle plus court pour générer plus de particules pendant le saut
                        Thread.sleep(20);
                    }
                } catch (InterruptedException e) {
                    // Ignorer l'interruption
                }
            });

            // Démarrer le thread avec une faible priorité
            particleThread.setPriority(Thread.MIN_PRIORITY);
            particleThread.start();
        }
    }
}
