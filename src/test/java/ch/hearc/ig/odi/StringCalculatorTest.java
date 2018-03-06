/*
 * Copyright (c) 2018. Cours Outils de développement intégré, HE-Arc Neuchâtel.
 */

package ch.hearc.ig.odi;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringCalculatorTest {

  @Test
  public void emptyString() {
    // Arrange / Build
    StringCalculator calc = new StringCalculator();
    int expected = 0;
    // Act / Operate
    int actual = calc.add("");
    // Assert / Check
    assertEquals(expected, actual);
  }

  @Test
  public void addASingleNumber() {
    // Arrange / Build
    StringCalculator calc = new StringCalculator();
    int expected = 1;
    // Act / Operate
    int actual = calc.add("1");
    // Assert / Check
    assertEquals(expected, actual);
  }

}