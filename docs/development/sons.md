# Guide des Sons

Ce guide explique comment créer et gérer les sons dans le mod LaCommunauteDeLalternance.

## Structure des Sons

Le mod utilise une architecture orientée objet pour gérer les sons, avec les composants suivants :

1. **BaseSound** : Classe abstraite de base pour tous les sons
2. **ModSounds** : Registre singleton pour gérer l'enregistrement des sons
3. **ModSoundProvider** : Fournisseur qui initialise tous les sons du mod

## Création d'un nouveau son

Pour créer un nouveau son, suivez ces étapes :

### 1. Ajouter le fichier audio

Ajoutez votre fichier audio au format OGG dans `src/main/resources/assets/lacommunautedelalternance/sounds/`. Le nom du fichier doit correspondre au nom d'enregistrement que vous utiliserez.

### 2. Créer le fichier de définition des sons

Modifiez ou créez le fichier `src/main/resources/assets/lacommunautedelalternance/sounds.json` pour inclure votre nouveau son :

```json
{
  "poop_sound": {
    "sounds": [
      "lacommunautedelalternance:poop_sound"
    ],
    "subtitle": "subtitles.lacommunautedelalternance.poop_sound"
  },
  "mon_nouveau_son": {
    "sounds": [
      "lacommunautedelalternance:mon_nouveau_son"
    ],
    "subtitle": "subtitles.lacommunautedelalternance.mon_nouveau_son"
  }
}
```

### 3. Créer une classe Java pour le son

Créez une nouvelle classe Java dans le package `fr.evanperreau.lacommunautedelalternance.sound` qui étend la classe `BaseSound` :

```java
package fr.evanperreau.lacommunautedelalternance.sound;

import java.util.function.Supplier;

import fr.evanperreau.lacommunautedelalternance.LaCommunautedeLalternance;
import fr.evanperreau.lacommunautedelalternance.sound.base.BaseSound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

/**
 * Son de [description] pour le mod.
 * Implémente le pattern singleton pour assurer une instance unique.
 */
public class MonNouveauSon extends BaseSound {
    private final Supplier<SoundEvent> SOUND;

    /**
     * Constructeur du son MonNouveauSon.
     * Initialise le fournisseur de son avec les propriétés appropriées.
     */
    public MonNouveauSon() {
        SOUND = () -> SoundEvent.createVariableRangeEvent(
                ResourceLocation.fromNamespaceAndPath(LaCommunautedeLalternance.MODID, getRegistryName()));
    }

    /**
     * Retourne l'instance unique de MonNouveauSon.
     * 
     * @return L'instance unique de MonNouveauSon
     */
    public static MonNouveauSon getInstance() {
        return BaseSound.getInstance(MonNouveauSon.class);
    }

    @Override
    public String getRegistryName() {
        return "mon_nouveau_son";
    }

    @Override
    protected Supplier<SoundEvent> getSound() {
        return SOUND;
    }
}
```

### 4. Enregistrer le son

Modifiez la méthode `register()` dans `ModSoundProvider` pour inclure votre nouveau son :

```java
public static void register() {
    new PoopSound().register();
    new MonNouveauSon().register(); // Ajoutez votre nouveau son ici
}
```

## Utilisation d'un son

Pour jouer un son dans votre code, utilisez le code suivant :

```java
// Jouer le son pour tous les joueurs proches
player.level().playSound(null, player, MonNouveauSon.getInstance().getDeferredSound().get(),
        SoundSource.PLAYERS, 1.0f, 1.0f);

// Jouer le son uniquement pour un joueur spécifique
player.playSound(MonNouveauSon.getInstance().getDeferredSound().get(), SoundSource.PLAYERS, 1.0f, 1.0f);
```

Les paramètres sont :
- `SoundSource` : La catégorie de son (PLAYERS, BLOCKS, AMBIENT, etc.)
- Premier `float` : Volume (0.0f à 1.0f)
- Second `float` : Pitch (0.5f à 2.0f)

## Traductions

N'oubliez pas d'ajouter une traduction pour le sous-titre de votre son dans le fichier `src/main/resources/assets/lacommunautedelalternance/lang/fr_fr.json` :

```json
{
  "subtitles.lacommunautedelalternance.mon_nouveau_son": "Description du son"
}
```

## Architecture

### BaseSound

La classe `BaseSound` fournit :

- Une méthode abstraite `getRegistryName()` pour définir le nom d'enregistrement
- Une méthode abstraite `getSound()` pour définir le fournisseur de son
- Une méthode `register()` pour enregistrer le son dans le jeu
- Un système de gestion d'instances pour faciliter l'accès aux sons

### ModSounds

La classe `ModSounds` utilise le pattern singleton pour :

- Gérer un registre différé (`DeferredRegister`) pour les sons
- Fournir des méthodes pour enregistrer les sons auprès du bus d'événements du mod

### ModSoundProvider

La classe `ModSoundProvider` :

- Initialise et enregistre tous les sons du mod
- Est appelée pendant la phase d'initialisation du mod

## Bonnes pratiques

- Suivez le principe de responsabilité unique (SRP) en créant une classe par son
- Limitez la taille des méthodes à 15 lignes maximum
- Documentez toutes les classes et méthodes avec Javadoc
- Utilisez le pattern singleton pour accéder facilement aux instances de sons
- Utilisez des fichiers audio de qualité suffisante mais optimisés pour le jeu
- Respectez les formats supportés par Minecraft (OGG Vorbis)

## Exemple concret : PoopSound

Le son `PoopSound` est un exemple d'implémentation :

```java
public class PoopSound extends BaseSound {
    private final Supplier<SoundEvent> SOUND;

    public PoopSound() {
        SOUND = () -> SoundEvent.createVariableRangeEvent(
                ResourceLocation.fromNamespaceAndPath(LaCommunautedeLalternance.MODID, getRegistryName()));
    }

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
```

---

*Retour à la [page principale du guide de développement](index.md)*
