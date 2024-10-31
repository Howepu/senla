package problem_1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;


public class GameLogic {

    private String word;
    private int attemptsLeft;
    private Set<String> guessedLetters;
    private List<Character> displayedChars;
    HelpService helpService;

    public GameLogic(String word, int attemptsLeft) {
        this.word = word.toLowerCase();
        this.attemptsLeft = attemptsLeft;
        this.guessedLetters = new HashSet<>();
        this.displayedChars = new ArrayList<>();
        this.helpService = new HelpService(true);
        for (char c : word.toCharArray()) {
            displayedChars.add('_');
        }
    }

    public String getWord() {
        return word;
    }

    public int getAttemptsLeft() {
        return attemptsLeft;
    }

    public Set<String> getGuessedLetters() {
        return guessedLetters;
    }

    public void setHelpService(HelpService helpService) {
        this.helpService = helpService;
    }

    public void makeGuess(String guess) {
        String normalizedGuess = guess.toLowerCase();
        boolean invalidGuess = !isValidGuess(normalizedGuess);
        boolean alreadyGuessed = guessedLetters.contains(normalizedGuess);

        if (invalidGuess || alreadyGuessed) {
            System.out.println(invalidGuess ? "Введите букву корректно!" : "Эту букву уже гадали!");
            return;
        }

        guessedLetters.add(normalizedGuess);

        if (word.contains(normalizedGuess)) {
            System.out.println("Правильно!");
            updateDisplayedChars(normalizedGuess.charAt(0));
        } else {
            System.out.println("Неправильно!");
            attemptsLeft--;
        }

        helpService.askForHelp(this);
    }

    private boolean isValidGuess(String guess) {
        return !(guess.isEmpty() || guess.length() != 1 || !Pattern.matches("[а-яА-Я]", guess));
    }

    private void updateDisplayedChars(char letter) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                displayedChars.set(i, letter);
            }
        }
    }

    public String getDisplayedChars() {
        StringBuilder displayedWordBuilder = new StringBuilder();
        for (char c : displayedChars) {
            displayedWordBuilder.append(c);
        }
        return displayedWordBuilder.toString();
    }

    public boolean isGameOver() {
        return attemptsLeft == 0 || !displayedChars.contains('_');
    }

    public boolean isWon() {
        return !displayedChars.contains('_');
    }

    public void drawHangman() {
        HangmanDrawer.drawHangman(attemptsLeft);
    }
}
