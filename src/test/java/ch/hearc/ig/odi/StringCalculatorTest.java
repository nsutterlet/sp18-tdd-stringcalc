/*
 * Copyright (c) 2018. Cours Outils de développement intégré, HE-Arc Neuchâtel.
 */

package ch.hearc.ig.odi;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * test class using parameterized tests
 * REF: https://junit.org/junit4/javadoc/4.12/org/junit/runners/Parameterized.html
 */
@RunWith(Parameterized.class)
public class StringCalculatorTest {

  private String calcInput;
  private int expected;

  public StringCalculatorTest(String calcInput, int calcOutput) {
    this.calcInput = calcInput;
    this.expected = calcOutput;
  }

  @Parameters(name = "{index}: sum[{0}]={1}")
  public static Iterable<Object[]> data() {
    return Arrays.asList(new Object[][]{
        {"", 0}, // emptyString
        {"1", 1}, // addASingleNumber
        {"1,2", 3}, // addTwoNumbers
    });
  }

  @Test
  public void test() {
    // Arrange / Build
    StringCalculator calc = new StringCalculator();
    // Act / Operate
    int actual = calc.add(calcInput);
    // Assert / Check
    assertEquals(expected, actual);
  }

}