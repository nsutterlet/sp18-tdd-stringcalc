/*
 * Copyright (c) 2018. Cours Outils de développement intégré, HE-Arc Neuchâtel.
 */

package ch.hearc.ig.odi;

import static java.lang.Integer.parseInt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

  StringCalculator() {
  }

  public int add(String input) {
    int sum = 0;
    CalcConfig calcConfig = new CalcConfig(input).invoke();
    String numbers = calcConfig.getNumbers();
    String delimiters = calcConfig.getDelimiters();
    if (!numbers.isEmpty()) {
      String[] operands = numbers.split(delimiters);
      for (String operand : operands) {
        sum += parseInt(operand);
      }
    }
    return sum;
  }

  private static class CalcConfig {

    private String input;
    private String numbers;
    private String delimiters;

    CalcConfig(String input) {
      this.input = input;
    }

    String getNumbers() {
      return numbers;
    }

    String getDelimiters() {
      return delimiters;
    }

    CalcConfig invoke() {
      delimiters = "[,\n]";
      numbers = input;
      Pattern pattern = Pattern
          .compile("(?<=//)(.*)(?=\n)"); // capture positive lookbehind, positive lookahead
      Matcher m = pattern.matcher(input);
      if (m.find()) {
        delimiters = m.group(1);
        numbers = numbers.substring(m.end(1) + 1);
      }
      return this;
    }
  }
}
