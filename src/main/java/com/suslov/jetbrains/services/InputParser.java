package com.suslov.jetbrains.services;

import com.suslov.jetbrains.exceptions.NumException;
import com.suslov.jetbrains.models.NumberType;
import com.suslov.jetbrains.utils.NumUtil;
import com.suslov.jetbrains.utils.OutputUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author Mikhail Suslov
 */
public class InputParser {
    private static final String INCORRECT_INPUT_DATA = "Incorrect input data";
    private static final String INVALID_INT_ERROR = "The %s parameter should be a natural number or zero.\n";
    private static final String INVALID_PROPERTY_ERROR = "The property %s is wrong.\n" +
            "Available properties: %s";
    private static final String INVALID_PROPERTIES_ERROR = "The properties %s are wrong.\n" +
            "Available properties: %s";
    private static final String EXCLUSIVE_PROPERTIES_ERROR = "The request contains mutually exclusive properties: " +
            "%s\n" + "There are no numbers with these properties.";

    private InputParser() {
    }

    public static boolean parseInputLineAndFinish(String inputLine) throws NumException {
        String[] split = inputLine.split(" ");
        if (split.length == 0) {
            throw new NumException(INCORRECT_INPUT_DATA);
        }

        if (split.length == 1) {
            long number = InputParser.parseLongNumber(split[0], "first");
            if (number == 0) {
                return true;
            }
            OutputUtil.numberPropertiesToList(number);
        } else if (split.length == 2) {
            long offset = InputParser.parseLongNumber(split[0], "first");
            int count = InputParser.parseIntNumber(split[1], "second");

            System.out.println();
            for (long i = offset; i < offset + count; i++) {
                OutputUtil.numberPropertiesToLine(i);
            }
        } else {
            long offset = InputParser.parseLongNumber(split[0], "first");
            int count = InputParser.parseIntNumber(split[1], "second");
            List<NumberType> inProps = InputParser.parseIncludeProperties(Arrays.copyOfRange(split, 2, split.length));
            List<NumberType> exProps = InputParser.parseExcludeProperties(Arrays.copyOfRange(split, 2, split.length));

            List<String> mutuallyExclusiveTypes = NumberType.findMutuallyExclusiveTypeNames(inProps, exProps);
            if (!mutuallyExclusiveTypes.isEmpty()) {
                throw new NumException(String.format(EXCLUSIVE_PROPERTIES_ERROR, mutuallyExclusiveTypes));
            }

            System.out.println();
            while (count > 0) {
                if (NumUtil.numberMatchConditions(offset, inProps, exProps)) {
                    OutputUtil.numberPropertiesToLine(offset);
                    count--;
                }
                offset++;
            }
        }

        return false;
    }

    public static long parseLongNumber(String longNumber, String orderInWord) throws NumException {
        try {
            long number = Long.parseLong(longNumber);
            if (number < 0) {
                throw new NumException(String.format(INVALID_INT_ERROR, orderInWord));
            }
            return number;
        } catch (NumberFormatException exp) {
            throw new NumException(String.format(INVALID_INT_ERROR, orderInWord), exp);
        }
    }

    public static int parseIntNumber(String intNumber, String orderInWord) throws NumException {
        try {
            int number = Integer.parseInt(intNumber);
            if (number < 0) {
                throw new NumException(String.format(INVALID_INT_ERROR, orderInWord));
            }
            return number;
        } catch (NumberFormatException exp) {
            throw new NumException(String.format(INVALID_INT_ERROR, orderInWord), exp);
        }
    }

    public static List<NumberType> parseIncludeProperties(String... properties) throws NumException {
        List<String> invalidProps = new ArrayList<>();
        List<NumberType> listProps = new ArrayList<>();
        for (String property : properties) {
            if (property.startsWith("-")) {
                continue;
            }
            Optional<NumberType> numberType = NumberType.getByName(property);
            if (numberType.isEmpty()) {
                invalidProps.add(property.toUpperCase());
            } else {
                listProps.add(numberType.get());
            }
        }
        if (invalidProps.size() > 0) {
            String message = invalidProps.size() == 1 ? INVALID_PROPERTY_ERROR : INVALID_PROPERTIES_ERROR;
            throw new NumException(String.format(message, invalidProps, NumberType.valuesToString()));
        }

        return listProps;
    }

    public static List<NumberType> parseExcludeProperties(String... properties) throws NumException {
        List<String> invalidProps = new ArrayList<>();
        List<NumberType> listProps = new ArrayList<>();
        for (String property : properties) {
            if (property.startsWith("-")) {
                Optional<NumberType> numberType = NumberType.getByName(property.substring(1));
                if (numberType.isEmpty()) {
                    invalidProps.add(property.toUpperCase());
                } else {
                    listProps.add(numberType.get());
                }
            }
        }
        if (invalidProps.size() > 0) {
            String message = invalidProps.size() == 1 ? INVALID_PROPERTY_ERROR : INVALID_PROPERTIES_ERROR;
            throw new NumException(String.format(message, invalidProps, NumberType.valuesToString()));
        }

        return listProps;
    }
}
