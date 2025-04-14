# Guide d'ajout de blocs et d'items

Ce guide vous explique en détail comment ajouter de nouveaux blocs et items à LaCommunauteDeLalternance.

## Ajout d'un nouveau bloc

L'ajout d'un bloc se fait en plusieurs étapes :

1. Création de la classe Java du bloc
2. Ajout des fichiers de ressources (modèles, textures, blockstates)
3. Enregistrement du bloc dans le registre du mod

### 1. Création de la classe Java du bloc

Créez une nouvelle classe Java dans le package `fr.evanperreau.lacommunautedelalternance.block` qui étend la classe `BaseBlock` :

```java
package fr.evanperreau.lacommunautedelalternance.block;

import java.util.function.Supplier;

import fr.evanperreau.lacommunautedelalternance.block.base.BaseBlock;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

/**
 * Block de [nom] pour le mod.
 */
public class MonNouveauBlock extends BaseBlock {
    private final Supplier<Block> BLOCK;

    public MonNouveauBlock() {
        BLOCK = () -> new Block(BlockBehaviour.Properties.of()
                .strength(1.5f) // Résistance du bloc
                .requiresCorrectToolForDrops()); // Nécessite un outil pour être miné
    }

    @Override
    public String getRegistryName() {
        return "mon_nouveau_block"; // Nom d'enregistrement (en minuscules et avec des underscores)
    }

    @Override
    public Supplier<Block> getBlock() {
        return BLOCK;
    }
}
```

### 2. Ajout des fichiers de ressources

#### 2.1 Création du blockstate

Créez un fichier JSON dans `src/main/resources/assets/lacommunautedelalternance/blockstates/mon_nouveau_block.json` :

```json
{
    "variants": {
        "": {
            "model": "lacommunautedelalternance:block/mon_nouveau_block"
        }
    }
}
```

#### 2.2 Création du modèle du bloc

Créez un fichier JSON dans `src/main/resources/assets/lacommunautedelalternance/models/block/mon_nouveau_block.json` :

```json
{
    "parent": "minecraft:block/cube_all",
    "textures": {
        "all": "lacommunautedelalternance:block/mon_nouveau_block"
    }
}
```

#### 2.3 Création du modèle de l'item du bloc

Créez un fichier JSON dans `src/main/resources/assets/lacommunautedelalternance/models/item/mon_nouveau_block.json` :

```json
{
    "parent": "lacommunautedelalternance:block/mon_nouveau_block"
}
```

#### 2.4 Ajout de la texture

Ajoutez votre texture au format PNG dans `src/main/resources/assets/lacommunautedelalternance/textures/block/mon_nouveau_block.png`.

### 3. Enregistrement du bloc

Modifiez la classe `ModBlockProvider` pour enregistrer votre nouveau bloc :

```java
// Dans la méthode registerBlocks() de la classe ModBlock
new MonNouveauBlock().register();
```

## Ajout d'un nouvel item

L'ajout d'un item se fait en plusieurs étapes :

1. Création de la classe Java de l'item
2. Ajout des fichiers de ressources (modèles, textures)
3. Enregistrement de l'item dans le registre du mod

### 1. Création de la classe Java de l'item

Créez une nouvelle classe Java dans le package `fr.evanperreau.lacommunautedelalternance.item` qui étend la classe `BaseItem` :

```java
package fr.evanperreau.lacommunautedelalternance.item;

import java.util.function.Supplier;

import fr.evanperreau.lacommunautedelalternance.item.base.BaseItem;

import net.minecraft.world.item.Item;

/**
 * Item de [nom] pour le mod.
 */
public class MonNouvelItem extends BaseItem {
    private final Supplier<Item> ITEM;

    public MonNouvelItem() {
        ITEM = () -> new Item(new Item.Properties());
    }

    @Override
    public String getRegistryName() {
        return "mon_nouvel_item"; // Nom d'enregistrement (en minuscules et avec des underscores)
    }

    @Override
    public Supplier<Item> getItem() {
        return ITEM;
    }
}
```

### 2. Ajout des fichiers de ressources

#### 2.1 Création du modèle de l'item

Créez un fichier JSON dans `src/main/resources/assets/lacommunautedelalternance/models/item/mon_nouvel_item.json` :

```json
{
    "parent": "minecraft:item/generated",
    "textures": {
        "layer0": "lacommunautedelalternance:item/mon_nouvel_item"
    }
}
```

#### 2.2 Ajout de la texture

Ajoutez votre texture au format PNG dans `src/main/resources/assets/lacommunautedelalternance/textures/item/mon_nouvel_item.png`.

### 3. Enregistrement de l'item

Modifiez la classe `ModItemProvider` pour enregistrer votre nouvel item :

```java
// Dans la méthode registerItems() de la classe ModItems
new MonNouvelItem().register();
```

## Types de blocs spéciaux

Pour créer des blocs avec des comportements spéciaux (comme des escaliers, des dalles, des portes, etc.), vous devrez utiliser les classes spécifiques fournies par Minecraft :

```java
// Exemple pour un bloc d'escalier
BLOCK = () -> new StairBlock(
    () -> Blocks.STONE.defaultBlockState(),
    BlockBehaviour.Properties.of()
        .strength(1.5f)
        .requiresCorrectToolForDrops()
);
```

## Types d'items spéciaux

Pour créer des items avec des comportements spéciaux (comme des outils, des armes, de la nourriture, etc.), vous devrez utiliser les classes spécifiques fournies par Minecraft :

```java
// Exemple pour un item de nourriture
ITEM = () -> new Item(new Item.Properties()
    .food(new FoodProperties.Builder()
        .nutrition(4)  // Points de nourriture
        .saturationModifier(0.3f)  // Saturation
        .build())
);
```

## Système de Singleton

Le mod utilise un système de singleton pour les items et les blocs, ce qui garantit qu'une seule instance de chaque classe existe. Cela permet d'éviter les doublons et d'accéder facilement aux instances depuis n'importe quel point du code.

### Comment fonctionne le système de singleton

1. Chaque classe d'item ou de bloc doit implémenter une méthode statique `getInstance()` qui retourne l'instance unique de la classe.

```java
public static MonNouvelItem getInstance() {
    return BaseItem.getInstance(MonNouvelItem.class);
}
```

2. L'instance est stockée dans une Map statique dans la classe de base (`BaseItem` ou `BaseBlock`).
3. Lors de l'enregistrement avec la méthode `register()`, l'instance est automatiquement ajoutée à cette Map.

### Utilisation du singleton dans le code

Pour utiliser un item ou un bloc ailleurs dans le code, utilisez la méthode `getInstance()` :

```java
// Pour accéder à l'instance de l'item
MonNouvelItem.getInstance().getDeferredItem();

// Pour ajouter l'item à un onglet créatif
event.accept(MonNouvelItem.getInstance().getDeferredItem());
```

## Ajout de recettes de craft

Les recettes permettent aux joueurs de créer vos items et blocs dans le jeu. Minecraft propose plusieurs types de recettes que vous pouvez utiliser.

### 1. Création d'une recette simple (shapeless)

Les recettes sans forme (shapeless) permettent de combiner des ingrédients sans ordre spécifique. Créez un fichier JSON dans `src/main/resources/data/lacommunautedelalternance/recipes/mon_nouvel_item.json` :

```json
{
    "type": "minecraft:crafting_shapeless",
    "category": "misc",
    "ingredients": [
        {
            "item": "minecraft:stick"
        },
        {
            "item": "minecraft:iron_ingot"
        }
    ],
    "result": {
        "id": "lacommunautedelalternance:mon_nouvel_item",
        "count": 1
    }
}
```

### 2. Création d'une recette avec forme (shaped)

Les recettes avec forme (shaped) nécessitent que les ingrédients soient placés dans un ordre spécifique. Créez un fichier JSON dans `src/main/resources/data/lacommunautedelalternance/recipes/mon_nouveau_block.json` :

```json
{
    "type": "minecraft:crafting_shaped",
    "category": "building",
    "pattern": [
        "###",
        "###",
        "###"
    ],
    "key": {
        "#": {
            "item": "lacommunautedelalternance:mon_nouvel_item"
        }
    },
    "result": {
        "id": "lacommunautedelalternance:mon_nouveau_block",
        "count": 1
    }
}
```

### 3. Recette de cuisson (furnace)

Les recettes de cuisson permettent de transformer un item en un autre via un four. Créez un fichier JSON dans `src/main/resources/data/lacommunautedelalternance/recipes/mon_nouvel_item_cuit.json` :

```json
{
    "type": "minecraft:smelting",
    "category": "misc",
    "cookingtime": 200,
    "experience": 0.5,
    "ingredient": {
        "item": "lacommunautedelalternance:mon_nouvel_item"
    },
    "result": "lacommunautedelalternance:mon_nouvel_item_cuit"
}
```

### 4. Autres types de recettes

Minecraft propose d'autres types de recettes comme :
- `stonecutting` : pour le tailleur de pierre
- `smithing` : pour la table de forgeron
- `campfire_cooking` : pour la cuisson au feu de camp
- `smoking` : pour le fumoir
- `blasting` : pour le haut fourneau

Consultez la documentation officielle de Minecraft pour plus de détails sur ces types de recettes.

## Bonnes pratiques

1. **Nommage** : Utilisez des noms descriptifs et suivez les conventions de nommage
   - Classes Java : PascalCase (ex: `MonNouveauBlock`)
   - Noms d'enregistrement : snake_case (ex: `mon_nouveau_block`)
   - Fichiers de ressources : snake_case (ex: `mon_nouveau_block.json`)

2. **Organisation** : Gardez une structure de code cohérente
   - Un bloc ou item par classe
   - Respectez la structure des packages

3. **Singletons** : Assurez-vous que chaque classe d'item ou de bloc implémente correctement la méthode `getInstance()`

4. **Documentation** : Documentez vos classes et méthodes avec Javadoc

5. **Tests** : Testez vos blocs et items en jeu avant de soumettre une Pull Request
