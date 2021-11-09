package question2;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

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
