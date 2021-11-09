package question2;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
