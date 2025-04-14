# Guide du développeur

Ce guide vous aidera à contribuer au développement du mod LaCommunauteDeLalternance en suivant les bonnes pratiques et conventions du projet.

## Prérequis

Avant de commencer à développer, assurez-vous d'avoir :

- Un compte GitHub
- Git installé sur votre machine
- Java Development Kit (JDK) 17 ou supérieur
- Un IDE (IntelliJ IDEA, Eclipse, VS Code...)
- Connaissances de base en Java et en développement de mods Minecraft

## Configuration de l'environnement de développement

1. **Fork** : Créez une copie du dépôt sur votre compte GitHub
2. **Clone** : Téléchargez votre fork sur votre machine locale
   ```bash
   git clone https://github.com/VotreNomUtilisateur/LaCommunauteDeLalternance.git
   ```
3. **Importez le projet** dans votre IDE
4. **Rafraîchissez les dépendances** pour télécharger toutes les librairies nécessaires
   ```bash
   ./gradlew --refresh-dependencies
   ```
5. **Vérifiez que tout fonctionne** en exécutant le client de développement
   ```bash
   ./gradlew runClient
   ```

## Structure du projet

- `src/main/java` : Code source Java
  - Organisation des packages (à détailler)
- `src/main/resources` : Ressources (textures, sons, fichiers de configuration)
  - Organisation des ressources (à détailler)
- `src/test` : Tests unitaires

## Guides spécifiques

- [Guide d'ajout de blocs et d'items](ajout-blocs-items.md)

## Principes de développement

### Principes SOLID

Nous suivons les principes SOLID pour garantir un code maintenable et extensible :

1. **S**ingle Responsibility Principle : Chaque classe doit avoir une seule responsabilité
2. **O**pen/Closed Principle : Les entités doivent être ouvertes à l'extension mais fermées à la modification
3. **L**iskov Substitution Principle : Les objets d'une classe dérivée doivent pouvoir remplacer les objets de la classe de base
4. **I**nterface Segregation Principle : Plusieurs interfaces spécifiques valent mieux qu'une interface générale
5. **D**ependency Inversion Principle : Dépendre des abstractions, pas des implémentations

### Conventions de codage

- **Nommage** : Utilisez des noms descriptifs et suivez les conventions Java
  - Classes : PascalCase
  - Méthodes et variables : camelCase
  - Constantes : SNAKE_CASE_MAJUSCULE
- **Documentation** : Documentez toutes les classes et méthodes publiques avec Javadoc
- **Formatage** : Utilisez un formatage cohérent (indentation de 4 espaces)
- **Longueur des méthodes** : Limitez les méthodes à 15 lignes maximum
- **Gestion des erreurs** : Utilisez des exceptions appropriées et gérez-les correctement

## Workflow de développement

1. **Créez une branche** pour votre fonctionnalité
   ```bash
   git checkout -b ma-nouvelle-fonctionnalite
   ```
2. **Développez** votre fonctionnalité ou correction
3. **Testez** vos modifications
   ```bash
   ./gradlew test
   ./gradlew runClient
   ```
4. **Commit** de vos changements avec le format Conventional Commits
   ```bash
   git commit -m "feat: ajout de ma nouvelle fonctionnalité"
   ```
5. **Push** vers votre fork
   ```bash
   git push origin ma-nouvelle-fonctionnalite
   ```
6. **Créez une Pull Request** sur GitHub

## Conventional Commits

Nous utilisons le format [Conventional Commits](https://www.conventionalcommits.org/) pour tous les messages de commit :

```
<type>[portée optionnelle]: <description>

[corps optionnel]

[pied de page optionnel]
```

### Types principaux
- **feat**: Ajout d'une nouvelle fonctionnalité
- **fix**: Correction d'un bug
- **docs**: Modifications de la documentation
- **style**: Changements qui n'affectent pas le sens du code
- **refactor**: Modification du code qui ne corrige pas un bug et n'ajoute pas de fonctionnalité
- **perf**: Amélioration des performances
- **test**: Ajout ou correction de tests
- **build**: Changements affectant le système de build ou les dépendances
- **ci**: Changements aux fichiers de configuration CI/CD
- **chore**: Autres changements qui ne modifient ni src ni les tests

## Tests

Avant de soumettre une Pull Request, assurez-vous que :

1. Vos modifications n'introduisent pas de régression
2. Vous avez écrit des tests unitaires pour votre code
3. Tous les tests passent
   ```bash
   ./gradlew test
   ```

## Ressources pour les développeurs

- [Documentation de l'API NeoForge](https://docs.neoforged.net/)
- [Tutoriels de développement de mods](https://www.youtube.com/playlist?list=PLKGarocXCE1G6CQOoiYdMVx-E1d9F_itF)
- [Documentation de l'API du mod](../api/index.md)

---

*Retour à la [page principale](../index.md)*
