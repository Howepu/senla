package problem_3;

import java.util.Random;
import java.util.Scanner;

public class PasswordGenerator {
    // Наборы символов для генерации пароля
    private static final String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_+=<>?";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите длину пароля (от 8 до 12 символов): ");
        int length = scanner.nextInt();

        // Проверка длины пароля
        if (length < 8 || length > 12) {
            System.out.println("Длина пароля должна быть от 8 до 12 символов.");
        } else {
            String password = generatePassword(length);
            System.out.println("Сгенерированный пароль: " + password);
        }

        scanner.close();
    }

    public static String generatePassword(int length) {
        Random random = new Random();
        StringBuilder password = new StringBuilder(length);

        // Сначала добавляем по одному символу из каждой группы для безопасности
        password.append(UPPER_CASE.charAt(random.nextInt(UPPER_CASE.length())));
        password.append(LOWER_CASE.charAt(random.nextInt(LOWER_CASE.length())));
        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        password.append(SPECIAL_CHARACTERS.charAt(random.nextInt(SPECIAL_CHARACTERS.length())));

        // Добавляем оставшиеся символы случайным образом из всех групп
        String allCharacters = UPPER_CASE + LOWER_CASE + DIGITS + SPECIAL_CHARACTERS;
        for (int i = 4; i < length; i++) {
            password.append(allCharacters.charAt(random.nextInt(allCharacters.length())));
        }

        // Перемешиваем символы для дополнительной безопасности
        return shuffleString(password.toString());
    }

    // Метод для перемешивания символов в строке
    private static String shuffleString(String input) {
        StringBuilder shuffled = new StringBuilder(input.length());
        Random random = new Random();

        while (input.length() > 0) {
            int index = random.nextInt(input.length());
            shuffled.append(input.charAt(index));
            input = input.substring(0, index) + input.substring(index + 1);
        }

        return shuffled.toString();
    }
}
