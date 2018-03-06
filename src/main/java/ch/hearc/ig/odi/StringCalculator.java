/*
 * Copyright (c) 2018. Cours Outils de développement intégré, HE-Arc Neuchâtel.
 */

package ch.hearc.ig.odi;

import static java.lang.Integer.parseInt;

public class StringCalculator {

  StringCalculator() {
  }

  public int add(String numbers) {
    if (numbers.isEmpty()) {
      return 0;
    }
    if (numbers.contains(",")) {
      return parseInt(numbers.split(",")[0]) + parseInt(numbers.split(",")[1]);
    }
    return parseInt(numbers);
  }

}
