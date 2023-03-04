package com.suslov.jetbrains;

import com.suslov.jetbrains.models.AmazingNumbersTest;
import com.suslov.jetbrains.models.NumberTypeTest;
import com.suslov.jetbrains.utils.NumUtilTest;
import com.suslov.jetbrains.utils.OutputUtilTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author Mikhail Suslov
 */
@RunWith(Suite.class)
@SuiteClasses({AmazingNumbersTest.class,
        NumberTypeTest.class,
        NumUtilTest.class,
        OutputUtilTest.class})
public class CommonTest {
}
