package com.suslov.jetbrains.utils;

import com.suslov.jetbrains.models.NumberType;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Mikhail Suslov
 */
public class NumUtilTest {

    @Test
    public void numberMatchConditions() {
        List<NumberType> includeProps = List.of(NumberType.EVEN, NumberType.DUCK);
        List<NumberType> excludeProps = List.of(NumberType.SUNNY, NumberType.SQUARE);

        Assert.assertTrue(NumUtil.numberMatchConditions(10, includeProps, excludeProps));
        Assert.assertFalse(NumUtil.numberMatchConditions(9, includeProps, excludeProps));
        Assert.assertFalse(NumUtil.numberMatchConditions(11, List.of(NumberType.EVEN), List.of(NumberType.EVEN)));
    }

    @Test
    public void numberMatchPropertyOdd() {
        Assert.assertTrue(NumUtil.numberMatchProperty(7L, NumberType.ODD));
        Assert.assertFalse(NumUtil.numberMatchProperty(10L, NumberType.ODD));
    }

    @Test
    public void numberMatchPropertyEven() {
        Assert.assertTrue(NumUtil.numberMatchProperty(14L, NumberType.EVEN));
        Assert.assertFalse(NumUtil.numberMatchProperty(15L, NumberType.EVEN));
    }

    @Test
    public void numberMatchPropertyBuzz() {
        Assert.assertTrue(NumUtil.numberMatchProperty(117L, NumberType.BUZZ));
        Assert.assertFalse(NumUtil.numberMatchProperty(75L, NumberType.BUZZ));
    }

    @Test
    public void numberMatchPropertyDuck() {
        Assert.assertTrue(NumUtil.numberMatchProperty(8050896L, NumberType.DUCK));
        Assert.assertFalse(NumUtil.numberMatchProperty(212L, NumberType.DUCK));
    }

    @Test
    public void numberMatchPropertyPalindromic() {
        Assert.assertTrue(NumUtil.numberMatchProperty(17371L, NumberType.PALINDROMIC));
        Assert.assertFalse(NumUtil.numberMatchProperty(1234L, NumberType.PALINDROMIC));
    }

    @Test
    public void numberMatchPropertyGapful() {
        Assert.assertTrue(NumUtil.numberMatchProperty(7881L, NumberType.GAPFUL));
        Assert.assertFalse(NumUtil.numberMatchProperty(141L, NumberType.GAPFUL));
    }

    @Test
    public void numberMatchPropertySpy() {
        Assert.assertTrue(NumUtil.numberMatchProperty(1124L, NumberType.SPY));
        Assert.assertFalse(NumUtil.numberMatchProperty(11L, NumberType.SPY));
    }

    @Test
    public void numberMatchPropertySunny() {
        Assert.assertTrue(NumUtil.numberMatchProperty(24L, NumberType.SUNNY));
        Assert.assertFalse(NumUtil.numberMatchProperty(101L, NumberType.SUNNY));
    }

    @Test
    public void numberMatchPropertySquare() {
        Assert.assertTrue(NumUtil.numberMatchProperty(25L, NumberType.SQUARE));
        Assert.assertFalse(NumUtil.numberMatchProperty(102L, NumberType.SQUARE));
    }

    @Test
    public void numberMatchPropertyJumping() {
        Assert.assertTrue(NumUtil.numberMatchProperty(434565L, NumberType.JUMPING));
        Assert.assertFalse(NumUtil.numberMatchProperty(10235L, NumberType.JUMPING));
    }

    @Test
    public void numberMatchPropertyHappy() {
        Assert.assertTrue(NumUtil.numberMatchProperty(13L, NumberType.HAPPY));
        Assert.assertFalse(NumUtil.numberMatchProperty(4L, NumberType.HAPPY));
    }

    @Test
    public void numberMatchPropertySad() {
        Assert.assertTrue(NumUtil.numberMatchProperty(4L, NumberType.SAD));
        Assert.assertFalse(NumUtil.numberMatchProperty(13L, NumberType.SAD));
    }

    @Test
    public void isEven() {
        Assert.assertTrue(NumUtil.isEven(14L));
        Assert.assertFalse(NumUtil.isEven(15L));
    }

    @Test
    public void isOdd() {
        Assert.assertTrue(NumUtil.isOdd(7L));
        Assert.assertFalse(NumUtil.isOdd(10L));
    }

    @Test
    public void isBuzz() {
        Assert.assertTrue(NumUtil.isBuzz(14L));
        Assert.assertTrue(NumUtil.isBuzz(117L));
        Assert.assertFalse(NumUtil.isBuzz(75L));
    }

    @Test
    public void isDuck() {
        Assert.assertTrue(NumUtil.isDuck(8050896L));
        Assert.assertFalse(NumUtil.isDuck(212L));
    }

    @Test
    public void isPalindromic() {
        Assert.assertTrue(NumUtil.isPalindromic(17371L));
        Assert.assertTrue(NumUtil.isPalindromic(2442L));
        Assert.assertTrue(NumUtil.isPalindromic(5L));
        Assert.assertFalse(NumUtil.isPalindromic(1234L));
    }

    @Test
    public void isGapful() {
        Assert.assertFalse(NumUtil.isGapful(12L));
        Assert.assertTrue(NumUtil.isGapful(7881L));
        Assert.assertFalse(NumUtil.isGapful(141L));
    }

    @Test
    public void isSpy() {
        Assert.assertTrue(NumUtil.isSpy(1124L));
        Assert.assertTrue(NumUtil.isSpy(9L));
        Assert.assertFalse(NumUtil.isSpy(11L));
    }

    @Test
    public void isSunny() {
        Assert.assertTrue(NumUtil.isSunny(24L));
        Assert.assertFalse(NumUtil.isSunny(101L));
    }

    @Test
    public void isSquare() {
        Assert.assertTrue(NumUtil.isSquare(25L));
        Assert.assertFalse(NumUtil.isSquare(102L));
    }

    @Test
    public void isJumping() {
        Assert.assertTrue(NumUtil.isJumping(4343456L));
        Assert.assertFalse(NumUtil.isJumping(796L));
    }

    @Test
    public void isSad() {
        Assert.assertFalse(NumUtil.isSad(13L));
        Assert.assertTrue(NumUtil.isSad(4L));
    }

    @Test
    public void isHappy() {
        Assert.assertTrue(NumUtil.isHappy(13L));
        Assert.assertFalse(NumUtil.isHappy(4L));
    }

    @Test
    public void countDigits() {
        Assert.assertEquals(4, NumUtil.countDigits(1256L));
        Assert.assertEquals(6, NumUtil.countDigits(998765L));
    }
}