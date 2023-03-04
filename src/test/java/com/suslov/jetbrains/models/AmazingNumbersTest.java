package com.suslov.jetbrains.models;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * @author Mikhail Suslov
 */
public class AmazingNumbersTest {

    @Test
    public void launchForSingleNumbers() {
        Scanner scanner = new Scanner("1\n5\n0");
        AmazingNumbers amazingNumbers = new AmazingNumbers(scanner);
        amazingNumbers.launch();
        Assert.assertTrue(amazingNumbers.isEnd);
    }

    @Test
    public void launchForListOfNumbersWithoutProperties() {
        Scanner scanner = new Scanner("1 5\n10 2\n0");
        AmazingNumbers amazingNumbers = new AmazingNumbers(scanner);
        amazingNumbers.launch();
        Assert.assertTrue(amazingNumbers.isEnd);
    }

    @Test
    public void launchForListOfNumbersWithProperties() {
        Scanner scanner = new Scanner("1 5 even sunny happy -duck -gapful\n0");
        AmazingNumbers amazingNumbers = new AmazingNumbers(scanner);
        amazingNumbers.launch();
        Assert.assertTrue(amazingNumbers.isEnd);
    }

    @Test
    public void launchForListOfNumbersWithMutuallyExcludeProperties() {
        Scanner scanner = new Scanner("1 5 sunny square\n0");
        AmazingNumbers amazingNumbers = new AmazingNumbers(scanner);
        amazingNumbers.launch();
        Assert.assertTrue(amazingNumbers.isEnd);
    }
}