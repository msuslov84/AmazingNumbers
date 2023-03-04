package com.suslov.jetbrains.models;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Mikhail Suslov
 */
public class NumberTypeTest {

    @Test
    public void getName() {
        Assert.assertEquals("odd", NumberType.ODD.getName());
        Assert.assertEquals("square", NumberType.SQUARE.getName());
    }

    @Test
    public void valuesToString() {
        Assert.assertEquals("[EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SUNNY, SQUARE, JUMPING, HAPPY, SAD]",
                NumberType.valuesToString());
    }

    @Test
    public void getByName() {
        Assert.assertSame(NumberType.EVEN, NumberType.getByName("even").orElse(null));
        Assert.assertSame(NumberType.SUNNY, NumberType.getByName("sunny").orElse(null));
        Assert.assertSame(NumberType.SQUARE, NumberType.getByName("SQuarE").orElse(null));
    }

    @Test
    public void getByIncorrectName() {
        Assert.assertNull(NumberType.getByName("even1").orElse(null));
    }

    @Test
    public void findMutuallyExclusiveTypeNamesWithoutIntersections() {
        List<NumberType> includeList = List.of(NumberType.SUNNY, NumberType.ODD);
        List<NumberType> excludeList = List.of(NumberType.EVEN, NumberType.JUMPING);
        Assert.assertTrue(NumberType.findMutuallyExclusiveTypeNames(includeList, excludeList).isEmpty());
    }

    @Test
    public void findMutuallyExclusiveTypeNamesWithIntersections() {
        List<NumberType> includeList = List.of(NumberType.SUNNY, NumberType.ODD);
        List<NumberType> excludeList = List.of(NumberType.ODD, NumberType.JUMPING, NumberType.SQUARE);
        Assert.assertArrayEquals(List.of("odd", "-odd").toArray(),
                NumberType.findMutuallyExclusiveTypeNames(includeList, excludeList).toArray());
    }

    @Test
    public void getExclusiveType() {
        Assert.assertSame(NumberType.EVEN, NumberType.getExclusiveType(NumberType.ODD));
        Assert.assertSame(NumberType.SAD, NumberType.getExclusiveType(NumberType.HAPPY));
        Assert.assertSame(NumberType.JUMPING, NumberType.getExclusiveType(NumberType.JUMPING));
    }
}