/*
 * Copyright (c) 2018. Cours Outils de développement intégré, HE-Arc Neuchâtel.
 */

package ch.hearc.ig.odi;

import static java.lang.Integer.parseInt;

public class StringCalculator {

  StringCalculator() {
  }

  public int add(String numbers) {
    int sum = 0;
    if (!numbers.isEmpty()) {
      String[] operands = numbers.split("[,\n]");
      for (String operand : operands) {
        sum += parseInt(operand);
      }
    }
    return sum;
  }

}
