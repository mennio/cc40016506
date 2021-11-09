# MAPC - Sujet CC n°1 - 2021-2022 (4 heures)

**Tous documents autorisés**

## Consignes

1. Pour répondre à ce sujet vous créerez un projet java + gradle dont le nom est `cc` suivi de votre numéro d'étudiant (ex : `cc123456`), par exemple de la façon suivante

    ```sh
    mkdir cc123456
    cd cc123456
    gradle init --type java-application
    ```

2. Pour chaque question vous donnerez :

    - le nom des patrons à utiliser et la raison de ce choix dans un unique fichier markdown nommé `reponses.md` à la racine de votre projet (`cc123456/reponses.md`) ;

    - le modèle UML (diagramme de classe) correspondant à l'architecture logicielle proposée dans un fichier au format `PNG` portant le nom de la question, à la racine de votre projet (`cc123456/q1.png`, `cc123456/q2.png`, etc), vérifiez la lisibilité de vos quatre diagrammes ;

    - le code source de la réalisation de vos architectures logicielles dans des répertoires et paquetages correspondant aux questions (donc par exemple le paquetage `cc123456.q1` sera dans `cc123456/.../main/java/cc123456/q1`, le paquetage `cc123456.q2` dans `cc123456/.../main/java/cc123456/q2`, etc.). L'abréviation `...` désigne par exemple `src` ou `app/src` selon votre version de gradle et l'initialisation que vous avez faite ;

    - une illustration (usage client) soit dans le code d'application (`App::main`) soit sous forme de tests est demandée pour les questions 2, 3 et 4.
    
3. Vous rendrez à la fin de la séance une archive `ZIP` de votre projet. **Il n'y aura aucune extension de temps, et si votre travail n'est pas soumis en temps et en heure, la note sera de 0/20**. Si votre fichier `ZIP` est trop gros pensez à faire par exemple un `gradle clean` avant de le créer.

4. Une attention particulière sera portée au plagiat : vous ne devez pas recopier de contenus trouvés sur internet ; 
l’évaluation de votre copie se fera uniquement en fonction du contenu du cours et de réflexions et démarche de résolution personnelles.

5. Le travail doit être personnel et individuel. Aucune communication n’est autorisée et contrevenir à cette consigne vous expose à des sanctions.

**Le non-respect de l'ensemble de ces consignes (y compris si vous ne nommez pas le fichier de réponses comme il faut ou bien qu'il n'est pas au bon endroit) entrainera le retrait de deux points sur la note.**

Les quatre questions sont indépendantes et peuvent être réalisées dans l'ordre que vous souhaitez.

## Question 1

On souhaite fournir une API permettant de sérialiser des chaines de caractères. Pour cela, il y aura une interface `Serializer` et deux réalisations concrètes de cette interface, `FileSerializer` (utilisation d'un fichier) et `ScreenSerializer` (utilisation de la sortie écran). Les opérations permises par l'API sont :

- `name` pour obtenir le nom associé au sérialiseur (ce nom, donné à la création d'un sérialiseur et non modifiable, servira de nom de fichier pour la première réalisation et de préfixe d'affichage à l'écran pour la seconde)
- `size` pour connaitre la taille de ce qui a été sérialisé (c'est le nombre de caractères du fichier pour la première réalisation et le nombre de chaines de caractères envoyées à l'écran pour la seconde)
- `write` pour sérialiser une chaine de caractères (rajoutée au fichier pour la première réalisation et envoyée à l'écran pour la seconde)

Une contrainte supplémentaire cruciale est rajoutée. Ce n'est pas le client qui choisit le type de sérialiseur qu'il va utiliser. Il en demande la création et il est possible au maximum d'avoir deux sérialiseurs dans des fichiers, ensuite le client obtiendra systématiquement des sérialiseurs à l'écran. La contrainte est valable quelque soit le nombre de clients, c'est-à-dire qu'il n'est pas possible d'avoir plus deux sérialiseurs dans des fichiers quelque soit le nombre de clients.

## Question 2

On veut mettre en place un système permettant de récupérer des recettes de cuisine. Pour cela on dispose d'une classe `CookingReference` et d'une interface `CookingReferenceRetriever` dotée d'une opération `Map<String, CookingReference> retrieveFromIngredient(String ingredient)`.

```java
public class CookingReference {
    private String id;
    private List<String> requirements;
    private String instructions;

    public CookingReference(String id, List<String> requirements, String instructions) {
        this.id = id;
        this.requirements = requirements;
        this.instructions = instructions;
    }

    public String id() {
        return id;
    }

    public List<String> requirements() {
        return requirements;
    }

    public String instructions() {
        return instructions;
    }
}
```

```java
public interface CookingReferenceRetriever {
    public Map<String, CookingReference> retrieveFromIngredient(String ingredient);
}
```

Le client peut rechercher des recettes en utilisant une réalisation de `CookingReferenceRetriever` comme suit :

```java
CookingReferenceRetriever retriever = ... // ici une instance de réalisation de l'interface
Map<String, CookingReference> = retriever.retrieveFromIngredient("tomate");
```

On dispose par ailleurs déjà de deux classes permettant de récupérer des recettes de cuisine sur deux sites en ligne (dans le code donné, c'est simulé par des variables statiques) : `Marmitor` (qui permet de récupérer des `Recette`) et `GrossBouf` (qui permet de récupérer des `Recipe`).

```java
public class Recette {
    private String nom;
    private Set<String> ingredients;
    private String description;

    public Recette(String nom, Set<String> ingredients, String description) {
        this.nom = nom;
        this.ingredients = ingredients;
        this.description = description;
    }

    public String nom() {
        return nom;
    }

    public Set<String> ingredients() {
        return ingredients;
    }

    public String description() {
        return description;
    }
}
```

```java
public class Recipe {
    private String identifier;
    private Map<String, Integer> ingredients;
    private List<String> steps;

    public Recipe(String identifier, Map<String, Integer> ingredients, List<String> steps) {
        this.identifier = identifier;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public String identifier() {
        return identifier;
    }

    public Map<String, Integer> ingredients() {
        return ingredients;
    }

    public List<String> steps() {
        return steps;
    }
}
```

```java
public class Marmitor {
    //
    // éléments statiques pour simuler l'accès à un site Web
    //
    private static Recette r1 = new Recette("Salade de tomates", Set.of("tomate", "oignon", "vinaigrette"),
            "Couper deux tomates en rondelles." + //
                    "Emincer l'oignon. Répartir l'oignon sur les tomates." + //
                    "Verser la vinaigrette.");
    private static Recette r2 = new Recette("Steak frites", Set.of("viande de boeuf", "pomme de terre", "huile"),
            "Couper les pommes de terre en bâtonnets." + //
                    "Les faire frire dans l'huile." + //
                    "Faire cuire la viande dans une poëlle." + //
                    "Servir.");
    private static Recette r3 = new Recette("Tomates farcies", Set.of("tomate", "farce", "sel", "poivre"),
            "Saler et poivrer la farce." + //
                    "Farcir les tomates avec la farce." + //
                    "Faire cuire en cocotte.");
    private static Map<String, Recette> recettes = List.of(r1, r2, r3).stream()
            .collect(Collectors.toMap(Recette::nom, Function.identity()));

    //
    // recherche de recettes par ingrédient
    // (renvoie la liste des recettes qui contiennent un ingrédient donné)
    //
    public List<Recette> rechercher(String ingredient) {
        return recettes.values().stream().filter(r -> r.ingredients().contains(ingredient)).toList();
    }
}

```

```java
public class GrossBouf {
        //
        // éléments statiques pour simuler l'accès à un site Web
        //
        private static Recipe r1 = new Recipe("Houmous", Map.of("pois-chiche", 200, "sesame", 10, "citron", 10),
                        List.of("Mixer les pois-chiche.", "Ajouter le sesame et le citron."));
        private static Recipe r2 = new Recipe("Ketchup maison",
                        Map.of("tomate", 100, "vinaigre", 10, "sucre", 10),
                        List.of("Mettre tous les ingrédients dans un bol et mixer."));
        private static Map<String, Recipe> references = List.of(r1, r2).stream()
                        .collect(Collectors.toMap(Recipe::identifier, Function.identity()));

        //
        // recherche de references par ingrédient
        // (renvoie la liste des references qui contiennent un ingrédient donné)
        //
        public List<Recipe> find(String ingredient) {
                return references.values().stream().filter(r -> r.ingredients().keySet().contains(ingredient)).toList();
        }
}
```

Comment est il possible de réutiliser ce code légataire pour obtenir deux réalisations de `CookingReferenceRetriever` ?

Illustrez en récupérant les recettes à la tomate des deux sites (deux appels clients).

**Important :** aucune des interfaces ou classes données ci-dessus ne sont modifiables.

## Question 3

On désire permettre la création et l'impression (à l'écran, avec `toString`) de mémoires de MIAGE.
Un mémoire est constitué des parties suivantes (représentées par des chaines de caractères) : un titre, un auteur, un résumé, des remerciements, une introduction, des chapitres, une conclusion et une bibliographie. Le titre, l'auteur, l'introduction et la conclusion sont obligatoires. Le reste est facultatif. Les chapitres sont numérotés automatiquement par le système. Comment permettre aux étudiants de réaliser leurs mémoires tout en respectant les règles de la filière ?

Illustrez avec les mémoires suivants.

```
My life at Bikini Bottom
Sponge Bob
Introduction
Conclusion
```

```
Mon mémoire de M2 MIAGE
John Doe
Ceci est le résumé
Merci tout le monde
Introduction
Chapitre 1 - Présentation du problème
Chapitre 2 - Etat de l'art
Chapitre 3 - Proposition
Chapitre 4 - Evaluation
Conclusion
Des références
```

## Question 4

On souhaite représenter un réseau d'acheminement électrique et pouvoir développer des algorithmes d'analyse dessus. Les éléments de réseau permettent les opérations suivantes (les kWh sont des entiers) :

-  `id` pour obtenir l'identification unique métier de l'élément (c'est une chaine de caractères)
- `abonnement` pour obtenir l'abonnement (consommation maximum théorique) de l'élément en kWh
- `addConsommation` pour ajouter une information de consommation en kWh
- `consommation` pour récupérer la consommation totale de l'élément en kWh
- `reset` pour réinitialiser la consommation de l'élément (typiquement utilisé en début de mois)

En pratique les éléments sont soit des points de livraison soit des zones de livraison. Une zone de livraison est constituée d'un ensemble d'éléments de réseau (donc de zones ou de points de livraisons). En ce qui concerne les opérations des éléments de réseau, pour les zones de livraison on procède comme suit :

- `id` : les zones de livraison ont des identifiants
- `abonnement` : c'est la somme des abonnements des éléments de la zone
- `addConsommation` : consiste à ajouter la consommation à tous les éléments de la zone
- `consommation` : c'est la somme des consommations de la zone
- `reset` : réinitialiser tous les éléments de la zone

Par ailleurs on souhaite mettre en place le moyen de réaliser des algorithmes d'analyse sur le réseau. Le premier (et seul pour le sujet, mais prévoir qu'il serait possible d'en rajouter facilement sans modifier le code réalisé jusqu'ici) consiste à parcourir le réseau pour obtenir la liste des identifiants des points de livraison dont la consommation dépasse l'abonnement prévu.

Illustrez avec le réseau suivant (où les dépassements concernent la zone xxB).

```
PDL 001 : abonnement 370, consommations de {100, 100, 100, 100}
PDL 002 : abonnement 500, consommations de {100, 100, 100, 100}
PDL 003 : abonnement 500, consommations de {100, 100, 100, 100, 100, 100}
Zone xxA : {PDL 001, PDL 002}
Zone xxB : {Zone xxA, PDL 003}

Dépassements : {PDL 001, PDL 003}
```

