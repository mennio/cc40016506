package question2;

import java.util.List;

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
