package com.suslov.jetbrains.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Mikhail Suslov
 */
public enum NumberType {
    EVEN("even"),
    ODD("odd"),
    BUZZ("buzz"),
    DUCK("duck"),
    PALINDROMIC("palindromic"),
    GAPFUL("gapful"),
    SPY("spy"),
    SUNNY("sunny"),
    SQUARE("square"),
    JUMPING("jumping"),
    HAPPY("happy"),
    SAD("sad");

    private final String name;

    NumberType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static String valuesToString() {
        return Arrays.toString(values());
    }

    public static Optional<NumberType> getByName(String name) {
        return Arrays.stream(values()).filter(t -> t.getName().equalsIgnoreCase(name)).findAny();
    }

    public static List<String> findMutuallyExclusiveTypeNames(List<NumberType> inTypes, List<NumberType> exTypes) {
        List<String> resultList = new ArrayList<>();
        resultList.addAll(inTypes.stream()
                .filter(type -> getExclusiveType(type) != type && inTypes.contains(getExclusiveType(type)) || exTypes.contains(type))
                .map(NumberType::getName)
                .collect(Collectors.toList()));
        resultList.addAll(exTypes.stream()
                .filter(type -> getExclusiveType(type) != type && exTypes.contains(getExclusiveType(type)) || inTypes.contains(type))
                .map(type -> "-" + type.getName())
                .collect(Collectors.toList()));
        return resultList;
    }

    static NumberType getExclusiveType(NumberType type) {
        switch (type) {
            case ODD:
                return EVEN;
            case EVEN:
                return ODD;
            case DUCK:
                return SPY;
            case SPY:
                return DUCK;
            case SUNNY:
                return SQUARE;
            case SQUARE:
                return SUNNY;
            case HAPPY:
                return SAD;
            case SAD:
                return HAPPY;
            default:
                return type;
        }
    }
}
