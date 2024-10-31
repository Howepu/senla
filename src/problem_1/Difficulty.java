package problem_1;

public enum Difficulty {
    EASY("лёгкий"),
    MEDIUM("средний"),
    HARD("сложный");

    private final String name;

    Difficulty(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
