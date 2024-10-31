package problem_1;

import java.util.Scanner;

public class HelpService {
    private static final int ATTEMPTS_TO_HELP = 3;
    private boolean helpUsed = false;
    private boolean helpEnabled; // Новое поле для контроля состояния

    public HelpService(boolean helpEnabled) {
        this.helpEnabled = helpEnabled;
    }

    public boolean isHelpUsed() {
        return helpUsed;
    }

    public void askForHelp(GameLogic gameLogic) {
        if (shouldOfferHelp(gameLogic.getAttemptsLeft())) {
            Scanner scanner = new Scanner(System.in);
            String response;

            while (true) {
                System.out.println("Хотите получить подсказку? (да/нет)");
                response = scanner.nextLine().trim().toLowerCase();

                if ("да".equals(response)) {
                    String help = HelpProvider.getHelp(gameLogic.getWord());
                    System.out.println("Подсказка: " + help);
                    helpUsed = true;
                    break;
                } else if ("нет".equals(response) || "".equals(response)) {
                    System.out.println("Подсказка не была использована.");
                    break;
                } else {
                    System.out.println("Некорректный ввод. Пожалуйста, введите 'да' или 'нет' или оставьте строку пустой.");
                }
            }
        }
    }

    private boolean shouldOfferHelp(int attemptsLeft) {
        return helpEnabled && attemptsLeft <= ATTEMPTS_TO_HELP && !helpUsed && attemptsLeft != 0;
    }
}
