package lebo_production.currencyexchange;

import java.util.Random;

public class BO_RandomGenerator {
    private static String randomPassword;

    public static void setRandomPassword(String randomPassword) {
        BO_RandomGenerator.randomPassword = randomPassword;
    }

    public static String generateRandomPassword(int min, int max) {
        if (randomPassword != null) {
            return randomPassword;
        }
        int random = new Random().nextInt((max - min) + 1) + min;
        return String.valueOf(random);
    }
}
