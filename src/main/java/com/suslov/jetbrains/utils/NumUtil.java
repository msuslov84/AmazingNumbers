package com.suslov.jetbrains.utils;

import com.suslov.jetbrains.exceptions.NumException;
import com.suslov.jetbrains.models.NumberType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mikhail Suslov
 */
public class NumUtil {

    private NumUtil() {
    }

    public static boolean numberMatchConditions(long number, List<NumberType> includeProps, List<NumberType> excludeProps) {
        return includeProps.stream().allMatch(p -> numberMatchProperty(number, p)) &&
                excludeProps.stream().noneMatch(p -> numberMatchProperty(number, p));
    }

    public static boolean numberMatchProperty(long number, NumberType property) throws NumException {
        switch (property) {
            case ODD:
                return isOdd(number);
            case EVEN:
                return isEven(number);
            case BUZZ:
                return isBuzz(number);
            case DUCK:
                return isDuck(number);
            case PALINDROMIC:
                return isPalindromic(number);
            case GAPFUL:
                return isGapful(number);
            case SPY:
                return isSpy(number);
            case SUNNY:
                return isSunny(number);
            case SQUARE:
                return isSquare(number);
            case JUMPING:
                return isJumping(number);
            case HAPPY:
                return isHappy(number);
            case SAD:
                return isSad(number);
            default:
                throw new NumException("Incorrect type of property");
        }
    }

    public static boolean isEven(long number) {
        return number % 2 == 0;
    }

    public static boolean isOdd(long number) {
        return number % 2 != 0;
    }

    public static boolean isBuzz(long number) {
        return number % 7 == 0 || number % 10 == 7;
    }

    public static boolean isDuck(long number) {
        boolean leadingZeroes = true;
        for (char letter : String.valueOf(number).toCharArray()) {
            if (letter == '0') {
                if (!leadingZeroes) {
                    return true;
                }
            } else if (leadingZeroes) {
                leadingZeroes = false;
            }
        }

        return false;
    }

    public static boolean isPalindromic(long number) {
        char[] chars = String.valueOf(number).toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {
            if (chars[i] != chars[chars.length - 1 - i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isGapful(long number) {
        int digits = countDigits(number);
        if (digits < 3) {
            return false;
        }
        double zeroes = Math.pow(10, digits - 1);
        int firstDigit = (int) (number / zeroes);
        int lastDigit = (int) (number % 10);

        return number % (firstDigit * 10L + lastDigit) == 0;
    }

    public static boolean isSpy(long number) {
        List<Integer> digits = Arrays.stream(String.valueOf(number).split(""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        int sum = 0;
        int mlp = 1;
        for (Integer digit : digits) {
            sum += digit;
            mlp *= digit;
        }

        return sum == mlp;
    }

    public static boolean isSunny(long number) {
        return isSquare(++number);
    }

    public static boolean isSquare(long number) {
        long sqrt = (long) Math.sqrt(number);
        return sqrt * sqrt == number;
    }

    public static boolean isJumping(long number) {
        char[] chars = String.valueOf(number).toCharArray();
        for (int i = 0; i < chars.length - 1; i++) {
            if (Math.abs((int) chars[i] - (int) chars[i + 1]) != 1) {
                return false;
            }
        }
        return true;
    }

    public static boolean isSad(long number) {
        return !isHappyNumber(number, new ArrayList<>(List.of(number)));
    }

    public static boolean isHappy(long number) {
        return isHappyNumber(number, new ArrayList<>(List.of(number)));
    }

    public static int countDigits(long number) {
        if (number < 10) {
            return 1;
        }
        return 1 + countDigits(number / 10);
    }

    private static boolean isHappyNumber(long currentNum, List<Long> oldNumbers) {
        int digits = countDigits(currentNum);

        long result = 0;
        for (int i = 0; i < digits; i++) {
            int digit;
            if (i == 0) {
                digit = (int) (currentNum % 10);
            } else {
                digit = (int) (currentNum / (long) Math.pow(10, i) % 10);
            }
            result += (long) Math.pow(digit, 2);
        }

        if (result == 1) {
            return true;
        } else if (oldNumbers.contains(result)) {
            return false;
        } else {
            oldNumbers.add(result);
            return isHappyNumber(result, oldNumbers);
        }
    }
}
