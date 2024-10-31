package problem_1;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

@SuppressWarnings("uncommentedmain")
public class HangmanGame {
    private static final int MAX_ATTEMPTS = 6;

    private HangmanGame() {
        throw new AssertionError("Не удается создать экземпляр служебного класса");
    }

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Игра Виселица");

        Category category = null;
        Difficulty difficulty = null;

        while (category == null) {
            System.out.println("Выберите категорию (животные/фрукты/города) или оставьте пустым для случайного выбора:");
            String categoryInput = console.nextLine().trim().toLowerCase();

            if (categoryInput.isEmpty()) {
                category = WordSelector.chooseRandomCategory(random);
            } else {
                category = Arrays.stream(Category.values())
                        .filter(c -> c.getName().equals(categoryInput))
                        .findFirst()
                        .orElse(null);
            }

            if (category == null) {
                System.out.println("Неверная категория. Попробуйте снова");
            }
        }

        while (difficulty == null) {
            System.out.println("Выберите уровень сложности (лёгкий/средний/сложный) или оставьте пустым для случайного выбора:");
            String difficultyInput = console.nextLine().trim().toLowerCase();

            if (difficultyInput.isEmpty()) {
                difficulty = WordSelector.chooseRandomDifficulty(random);
            } else {
                difficulty = Arrays.stream(Difficulty.values())
                        .filter(d -> d.getName().equals(difficultyInput))
                        .findFirst()
                        .orElse(null);
            }

            if (difficulty == null) {
                System.out.println("Неверная сложность. Попробуйте снова");
            }
        }

        String word = WordSelector.chooseRandomWord(random, category, difficulty);
        GameLogic game = new GameLogic(word, MAX_ATTEMPTS);
        game.drawHangman();

        while (!game.isGameOver()) {
            System.out.println("Слово: " + game.getDisplayedChars());
            if (game.getAttemptsLeft() >= MAX_ATTEMPTS - 1) {
                System.out.println("У вас осталось " + game.getAttemptsLeft() + " попыток!");
            } else if (game.getAttemptsLeft() >= 2) {
                System.out.println("У вас осталось " + game.getAttemptsLeft() + " попытки!");
            } else if (game.getAttemptsLeft() == 1) {
                System.out.println("У вас осталась " + game.getAttemptsLeft() + " попытка!");
            }
            System.out.println("Введите букву: ");
            String guess = console.nextLine().trim().toLowerCase();
            game.makeGuess(guess);
            game.drawHangman();
        }

        if (game.isWon()) {
            System.out.println("Поздравляем! Вы угадали слово: " + game.getWord());
        } else {
            System.out.println("Вы проиграли! Загаданное слово было: " + game.getWord());
        }

        console.close();
    }
}
