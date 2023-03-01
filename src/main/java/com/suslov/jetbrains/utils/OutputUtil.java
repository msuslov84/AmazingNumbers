package com.suslov.jetbrains.utils;

import com.suslov.jetbrains.models.NumberType;

import java.util.Locale;

/**
 * @author Mikhail Suslov
 */
public class OutputUtil {

    private OutputUtil() {
    }

    public static void numberPropertiesToList(long number) {
        System.out.printf(Locale.US, "\nProperties of %,d\n", number);
        for (NumberType property : NumberType.values()) {
            System.out.printf("\t%11s : %b\n", property.getName(), NumUtil.numberMatchProperty(number, property));
        }
    }

    public static void numberPropertiesToLine(long number) {
        System.out.printf(Locale.US, "\t%,11d %s\n", number, representProperties(number));
    }

    static String representProperties(long number) {
        StringBuilder str = new StringBuilder();
        for (NumberType property : NumberType.values()) {
            if (NumUtil.numberMatchProperty(number, property)) {
                str.append(str.length() == 0 ? "" : ", ").append(property.getName());
            }
        }
        if (str.length() != 0) {
            str.insert(0, "is ");
        }
        return str.toString();
    }
}
