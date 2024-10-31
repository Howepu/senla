package problem_2;

import java.util.Scanner;


public class CurrencyConverter {
    private static final double USD_TO_EUR = 0.85;
    private static final double USD_TO_RUB = 76.45;
    private static final double USD_TO_JPY = 113.5;
    private static final double USD_TO_GBP = 0.75;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите сумму в долларах США (USD): ");
        double amountInUsd = scanner.nextDouble();

        double amountInEur = convert(amountInUsd, USD_TO_EUR);
        double amountInRub = convert(amountInUsd, USD_TO_RUB);
        double amountInJpy = convert(amountInUsd, USD_TO_JPY);
        double amountInGbp = convert(amountInUsd, USD_TO_GBP);

        System.out.printf("Сумма в евро (EUR): %.2f\n", amountInEur);
        System.out.printf("Сумма в рублях (RUB): %.2f\n", amountInRub);
        System.out.printf("Сумма в йенах (JPY): %.2f\n", amountInJpy);
        System.out.printf("Сумма в фунтах (GBP): %.2f\n", amountInGbp);
    }

    private static double convert(double amount, double rate) {
        return amount * rate;
    }
}
