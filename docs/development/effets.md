# Guide des Effets

Ce guide explique comment créer et gérer les effets de potions dans le mod LaCommunauteDeLalternance.

## Structure des Effets

Le mod utilise une architecture orientée objet pour gérer les effets, avec les composants suivants :

1. **BaseEffect** : Classe abstraite de base pour tous les effets
2. **ModEffects** : Registre singleton pour gérer l'enregistrement des effets
3. **ModEffectProvider** : Fournisseur qui initialise tous les effets du mod

## Création d'un nouvel effet

Pour créer un nouvel effet, suivez ces étapes :

### 1. Créer une classe Java pour l'effet

Créez une nouvelle classe Java dans le package `fr.evanperreau.lacommunautedelalternance.effect` qui étend la classe `BaseEffect` :

```java
package fr.evanperreau.lacommunautedelalternance.effect;

import java.util.function.Supplier;

import fr.evanperreau.lacommunautedelalternance.effect.base.BaseEffect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

/**
 * Effet qui [description] pour le mod.
 * Implémente le pattern singleton pour assurer une instance unique.
 */
public class MonNouvelEffet extends BaseEffect {
    private final Supplier<MobEffect> EFFECT;

    /**
     * Constructeur de l'effet MonNouvelEffet.
     * Initialise le fournisseur d'effet avec les propriétés appropriées.
     */
    public MonNouvelEffet() {
        EFFECT = () -> new MonNouvelMobEffect(MobEffectCategory.BENEFICIAL, 0xFF5500);
    }

    /**
     * Retourne l'instance unique de MonNouvelEffet.
     * 
     * @return L'instance unique de MonNouvelEffet
     */
    public static MonNouvelEffet getInstance() {
        return BaseEffect.getInstance(MonNouvelEffet.class);
    }

    @Override
    public String getRegistryName() {
        return "mon_nouvel_effet";
    }

    @Override
    protected Supplier<MobEffect> getEffect() {
        return EFFECT;
    }

    /**
     * Classe interne qui définit le comportement de l'effet.
     */
    private static class MonNouvelMobEffect extends MobEffect {
        public MonNouvelMobEffect(MobEffectCategory category, int color) {
            super(category, color);
        }

        @Override
        public boolean applyEffectTick(LivingEntity entity, int amplifier) {
            // Implémentez ici le comportement de l'effet
            // Cette méthode est appelée à chaque tick tant que l'effet est actif
            
            return true; // Retourne true si l'effet a été appliqué
        }

        @Override
        public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
            // Contrôle la fréquence d'application de l'effet
            // Par défaut, les effets sont appliqués tous les 20 ticks (1 seconde)
            return duration % 20 == 0;
        }
    }
}
```

### 2. Enregistrer l'effet

Modifiez la méthode `register()` dans `ModEffectProvider` pour inclure votre nouvel effet :

```java
public static void register() {
    new BigPoopEffect().register();
    new MonNouvelEffet().register(); // Ajoutez votre nouvel effet ici
}
```

### 3. Ajouter une icône pour l'effet (optionnel)

Ajoutez une icône au format PNG dans `src/main/resources/assets/lacommunautedelalternance/textures/mob_effect/mon_nouvel_effet.png`. L'icône doit avoir une taille de 18x18 pixels.

## Utilisation d'un effet

Pour appliquer un effet à une entité dans votre code, utilisez le code suivant :

```java
// Appliquer l'effet pendant 200 ticks (10 secondes) avec un niveau d'amplification de 0
player.addEffect(new MobEffectInstance(MonNouvelEffet.getInstance().getDeferredEffect().get(), 200, 0));

// Vérifier si une entité a un effet spécifique
boolean hasEffect = player.hasEffect(MonNouvelEffet.getInstance().getDeferredEffect().get());

// Supprimer un effet
player.removeEffect(MonNouvelEffet.getInstance().getDeferredEffect().get());
```

## Types d'effets

Minecraft propose plusieurs catégories d'effets :

- `MobEffectCategory.BENEFICIAL` : Effets positifs (vert)
- `MobEffectCategory.NEUTRAL` : Effets neutres (blanc)
- `MobEffectCategory.HARMFUL` : Effets négatifs (rouge)

## Traductions

N'oubliez pas d'ajouter une traduction pour votre effet dans le fichier `src/main/resources/assets/lacommunautedelalternance/lang/fr_fr.json` :

```json
{
  "effect.lacommunautedelalternance.mon_nouvel_effet": "Nom de l'effet"
}
```

## Architecture

### BaseEffect

La classe `BaseEffect` fournit :

- Une méthode abstraite `getRegistryName()` pour définir le nom d'enregistrement
- Une méthode abstraite `getEffect()` pour définir le fournisseur d'effet
- Une méthode `register()` pour enregistrer l'effet dans le jeu
- Un système de gestion d'instances pour faciliter l'accès aux effets

### ModEffects

La classe `ModEffects` utilise le pattern singleton pour :

- Gérer un registre différé (`DeferredRegister`) pour les effets
- Fournir des méthodes pour enregistrer les effets auprès du bus d'événements du mod

### ModEffectProvider

La classe `ModEffectProvider` :

- Initialise et enregistre tous les effets du mod
- Est appelée pendant la phase d'initialisation du mod

## Bonnes pratiques

- Suivez le principe de responsabilité unique (SRP) en créant une classe par effet
- Limitez la taille des méthodes à 15 lignes maximum
- Documentez toutes les classes et méthodes avec Javadoc
- Utilisez le pattern singleton pour accéder facilement aux instances d'effets
- Divisez la logique complexe en méthodes plus petites pour maintenir la lisibilité
- Utilisez des classes internes pour encapsuler le comportement spécifique de l'effet

## Exemple concret : BigPoopEffect

L'effet `BigPoopEffect` est un exemple d'implémentation :

```java
public class BigPoopEffect extends BaseEffect {
    private final Supplier<MobEffect> EFFECT;

    public BigPoopEffect() {
        EFFECT = () -> new BigPoopMobEffect(MobEffectCategory.NEUTRAL, 0x663300);
    }

    public static BigPoopEffect getInstance() {
        return BaseEffect.getInstance(BigPoopEffect.class);
    }

    @Override
    public String getRegistryName() {
        return "big_poop_effect";
    }

    @Override
    protected Supplier<MobEffect> getEffect() {
        return EFFECT;
    }

    private static class BigPoopMobEffect extends MobEffect {
        public BigPoopMobEffect(MobEffectCategory category, int color) {
            super(category, color);
        }

        @Override
        public boolean applyEffectTick(LivingEntity entity, int amplifier) {
            if (entity instanceof Player player) {
                if (player.isShiftKeyDown()) {
                    // Applique l'effet lorsque le joueur s'accroupit
                    applyJumpEffect(player);
                    player.level().playSound(null, player, 
                        PoopSound.getInstance().getDeferredSound().get(),
                        SoundSource.PLAYERS, 5.0f, 1.0f);
                    applyParticles(player);
                    removeEffect(player);
                    return true;
                }
            }
            return false;
        }

        // Autres méthodes d'implémentation...
    }
}
```

---

*Retour à la [page principale du guide de développement](index.md)*
