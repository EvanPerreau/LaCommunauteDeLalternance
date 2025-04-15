# Guide des Creative Tabs

Ce guide explique comment créer et gérer les onglets créatifs (Creative Tabs) dans le mod LaCommunauteDeLalternance.

## Structure des Creative Tabs

Le mod utilise une architecture orientée objet pour gérer les onglets créatifs, avec les composants suivants :

1. **BaseCreativeTab** : Classe abstraite de base pour tous les onglets créatifs
2. **ModCreativeTabs** : Registre singleton pour gérer l'enregistrement des onglets
3. **ModCreativeTabProvider** : Fournisseur qui initialise tous les onglets créatifs
4. **ICreativeTabLoader** : Interface pour définir comment charger les items dans un onglet

## Création d'un nouvel onglet créatif

Pour créer un nouvel onglet créatif, suivez ces étapes :

### 1. Créer une classe de chargeur (Loader)

Créez une classe qui implémente `ICreativeTabLoader` pour définir quels items seront affichés dans l'onglet :

```java
public class MonOngletLoader implements ICreativeTabLoader {
    @Override
    public void register(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output output) {
        // Ajoutez ici les items à afficher dans l'onglet
        output.accept(MonItem.getInstance().getDeferredItem().get());
        // Vous pouvez ajouter autant d'items que nécessaire
    }
}
```

### 2. Créer une classe d'onglet créatif

Créez une classe qui étend `BaseCreativeTab` :

```java
public class MonOnglet extends BaseCreativeTab {
    @Override
    public String getRegistryName() {
        return "mon_onglet"; // Nom d'enregistrement (utilisé pour les traductions)
    }

    @Override
    public Supplier<Item> getIcon() {
        return () -> MonItem.getInstance().getDeferredItem().get(); // Item utilisé comme icône
    }

    @Override
    protected ICreativeTabLoader getLoader() {
        return new MonOngletLoader(); // Loader créé à l'étape précédente
    }

    // Méthode statique pour obtenir l'instance (pattern singleton)
    public static MonOnglet getInstance() {
        return BaseCreativeTab.getInstance(MonOnglet.class);
    }
}
```

### 3. Enregistrer l'onglet créatif

Modifiez la méthode `register()` dans `ModCreativeTabProvider` pour inclure votre nouvel onglet :

```java
public static void register() {
    new AlternanceFood().register();
    new MonOnglet().register(); // Ajoutez votre nouvel onglet ici
}
```

## Traductions

N'oubliez pas d'ajouter une traduction pour votre onglet dans le fichier `src/main/resources/assets/lacommunautedelalternance/lang/fr_fr.json` :

```json
{
  "creativetab.lacommunautedelalternance.mon_onglet": "Mon Onglet"
}
```

## Architecture

### BaseCreativeTab

La classe `BaseCreativeTab` fournit :

- Une méthode abstraite `getRegistryName()` pour définir le nom d'enregistrement
- Une méthode abstraite `getIcon()` pour définir l'icône de l'onglet
- Une méthode abstraite `getLoader()` pour définir le chargeur d'items
- Une méthode `register()` pour enregistrer l'onglet dans le jeu
- Un système de gestion d'instances pour faciliter l'accès aux onglets

### ModCreativeTabs

La classe `ModCreativeTabs` utilise le pattern singleton pour :

- Gérer un registre différé (`DeferredRegister`) pour les onglets créatifs
- Fournir des méthodes pour enregistrer les onglets auprès du bus d'événements du mod

### ModCreativeTabProvider

La classe `ModCreativeTabProvider` :

- Initialise et enregistre tous les onglets créatifs du mod
- Est appelée pendant la phase d'initialisation du mod

## Bonnes pratiques

- Suivez le principe de responsabilité unique (SRP) en séparant les loaders des onglets
- Limitez la taille des méthodes à 15 lignes maximum
- Documentez toutes les classes et méthodes avec Javadoc
- Utilisez le pattern singleton pour accéder facilement aux instances d'onglets
- Organisez les items dans des onglets logiques et cohérents

## Exemple concret : AlternanceFood

L'onglet `AlternanceFood` est un exemple d'implémentation :

```java
public class AlternanceFood extends BaseCreativeTab {
    @Override
    public String getRegistryName() {
        return "alternance_food";
    }

    @Override
    public Supplier<Item> getIcon() {
        return () -> CrousBurgerKebab.getInstance().getDeferredItem().get();
    }

    @Override
    protected ICreativeTabLoader getLoader() {
        return new AlternanceFoodLoader();
    }

    public static AlternanceFood getInstance() {
        return BaseCreativeTab.getInstance(AlternanceFood.class);
    }
}
```

---

*Retour à la [page principale du guide de développement](index.md)*
