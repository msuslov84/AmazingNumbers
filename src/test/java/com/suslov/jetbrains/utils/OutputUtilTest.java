package com.suslov.jetbrains.utils;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Mikhail Suslov
 */
public class OutputUtilTest {

    @Test
    public void representProperties() {
        Assert.assertEquals("is odd, buzz, palindromic, spy, jumping, happy", OutputUtil.representProperties(7L));
        Assert.assertEquals("is even, duck, jumping, happy", OutputUtil.representProperties(10L));
    }
}