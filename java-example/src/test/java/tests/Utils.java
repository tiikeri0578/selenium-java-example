package tests;

import java.math.BigDecimal;
import java.util.Random;

import static java.lang.Math.abs;
import static java.lang.System.currentTimeMillis;
import static java.math.RoundingMode.FLOOR;

public final class Utils {

    private final static Random random = new Random();

    public static String genENString(int min, int max) {
        return genRandomStringFromSymbols(genInt(min, max),
                "abcdefghijklpnopqrstuvwxyzsdhfkhkqjwahsaisjcoixjdff");
    }

    public static Integer genInt(int min, int max) {
        return min + abs(random.nextInt()) % (max - min + 1);
    }

    public static BigDecimal genSumWithCent(Integer min, Integer max) {
        return new BigDecimal(genInt(min, max - 1) + (abs(random.nextInt()) % 90) / 100.).setScale(2, FLOOR);
    }

    public static String genRequestId(Integer n) {
        return genNumberString(n);
    }

    public static String genNumberString(int count) {
        return genNumberString(count, 10);
    }

    public static String genPhoneNumber(String prefix) {
        return prefix + genRequestId(10);
    }

    public static String genNumberString(int count, int maxNumber) {
        maxNumber = maxNumber + 1;
        if (maxNumber > 10)
            maxNumber = 10;
        String voc1 = "0123456789";
        return genRandomStringFromSymbols(count, voc1.substring(0, maxNumber));
    }

    public static String genEmail() {
        return "test" + currentTimeMillis() + "@smith.me";
    }

    private static String genRandomStringFromSymbols(int len, String allowedSymbols) {
        StringBuilder out = new StringBuilder();
        char[] symb = allowedSymbols.toCharArray();
        int allowedLen = allowedSymbols.length();

        out.append(Character.toUpperCase(symb[abs(random.nextInt()) % allowedLen]));
        for (int i = 1; i < len; ++i) {
            out.append(symb[abs(random.nextInt()) % allowedLen]);
        }

        return out.toString();
    }
}
