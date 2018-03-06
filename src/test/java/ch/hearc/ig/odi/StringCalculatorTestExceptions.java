/*
 * Copyright (c) 2018. Cours Outils de développement intégré, HE-Arc Neuchâtel.
 */

package ch.hearc.ig.odi;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StringCalculatorTestExceptions {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void negativeNumberRaisesException() {
    // Arrange / Build
    StringCalculator calc = new StringCalculator();
    String calcInput = "-1";
    thrown.expect(IllegalArgumentException.class);
    thrown.expectMessage("-1");
    // Act / Operate
    calc.add(calcInput);
    // Assert / Check
  }

}