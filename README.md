# [LaCommunauteDeLalternance](https://evanperreau.github.io/LaCommunauteDeLalternance/)

## Présentation

LaCommunauteDeLalternance est un mod communautaire développé pour le serveur Minecraft des alternants. Ce projet permet à tous les alternants de contribuer et d'enrichir l'expérience de jeu en proposant leurs propres fonctionnalités et améliorations.

## Comment contribuer

### Prérequis
- Un compte GitHub
- Git installé sur votre machine
- Java Development Kit (JDK) 17 ou supérieur
- Un IDE (IntelliJ IDEA, Eclipse, VS Code...)

### Étapes pour contribuer
1. **Fork** : Créez une copie du dépôt sur votre compte GitHub
2. **Clone** : Téléchargez votre fork sur votre machine locale
   ```
   git clone https://github.com/EvanPerreau/LaCommunauteDeLalternance.git
   ```
3. **Créez une branche** pour votre fonctionnalité
   ```
   git checkout -b ma-nouvelle-fonctionnalite
   ```
4. **Développez** votre fonctionnalité ou correction
5. **Testez** vos modifications
6. **Commit** de vos changements avec le format Conventional Commits (voir section dédiée)
   ```
   git commit -m "feat: ajout de ma nouvelle fonctionnalité"
   ```
7. **Push** vers votre fork
   ```
   git push origin ma-nouvelle-fonctionnalite
   ```
8. **Créez une Pull Request** sur GitHub pour proposer vos modifications

## Conventional Commits

Nous suivons les normes [Conventional Commits](https://www.conventionalcommits.org/) pour tous les messages de commit. Cette convention rend l'historique du projet plus lisible et facilite la génération automatique de changelogs.

### Format de base
```
<type>[portée optionnelle]: <description>

[corps optionnel]

[pied de page optionnel]
```

### Types principaux
- **feat**: Ajout d'une nouvelle fonctionnalité
- **fix**: Correction d'un bug
- **docs**: Modifications de la documentation
- **style**: Changements qui n'affectent pas le sens du code (espaces, formatage, etc.)
- **refactor**: Modification du code qui ne corrige pas un bug et n'ajoute pas de fonctionnalité
- **perf**: Amélioration des performances
- **test**: Ajout ou correction de tests
- **build**: Changements affectant le système de build ou les dépendances externes
- **ci**: Changements aux fichiers de configuration CI/CD
- **chore**: Autres changements qui ne modifient ni src ni les tests

### Exemples
```
feat(blocks): ajouter un nouveau bloc d'alternance
fix(crafting): corriger la recette du bloc alternatif
docs(readme): mettre à jour les instructions d'installation
refactor(gui): réorganiser les éléments de l'interface
```

### Breaking Changes
Pour les changements incompatibles, ajoutez `!` après le type/portée ou incluez `BREAKING CHANGE:` dans le corps du commit :
```
feat!: supprimer l'ancienne API de téléportation
```
ou
```
feat: mettre à jour le système de spawn

BREAKING CHANGE: la configuration de spawn a changé, les anciens fichiers ne fonctionneront plus
```

## Directives de contribution

- Assurez-vous que votre code est bien documenté
- Respectez les conventions de nommage et le style de code existant
- Testez vos fonctionnalités avant de soumettre une PR
- Décrivez clairement vos modifications dans la description de la PR
- Soyez respectueux et constructif dans vos commentaires

## Installation

1. Téléchargez la dernière version du mod depuis la section Releases
2. Placez le fichier .jar dans le dossier `mods` de votre installation Minecraft
3. Assurez-vous d'avoir Forge/Fabric installé (version compatible indiquée dans les releases)
4. Lancez Minecraft et connectez-vous au serveur des alternants

## Fonctionnalités

Le mod inclut actuellement:
- (Liste des fonctionnalités principales)
- (À compléter au fur et à mesure des contributions)

## Idées de contribution

Si vous ne savez pas par où commencer, voici quelques idées:
- Ajout de nouveaux blocs ou items thématiques
- Création de structures générées
- Implémentation de mécaniques de jeu spécifiques
- Amélioration de l'interface utilisateur
- Correction de bugs existants

## Environnement de développement

### Structure du projet
- `src/main/java` : Code source Java
- `src/main/resources` : Ressources (textures, sons, fichiers de configuration)
- `src/test` : Tests unitaires

### Refresh dependencies

```
./gradlew --refresh-dependencies
```

### Run le client de dev

```
./gradlew runClient
```

### Run le serveur de dev

```
./gradlew runServer
```

## Licence

Ce projet est sous licence [insérer type de licence]. Voir le fichier LICENSE pour plus de détails.

## Contact

Pour toute question ou suggestion concernant le mod, n'hésitez pas à:
- Créer une issue sur GitHub
- Contacter les administrateurs du serveur