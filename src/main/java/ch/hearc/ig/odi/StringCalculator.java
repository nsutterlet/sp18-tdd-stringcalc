/*
 * Copyright (c) 2018. Cours Outils de développement intégré, HE-Arc Neuchâtel.
 */

package ch.hearc.ig.odi;

import static java.lang.Integer.parseInt;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

  StringCalculator() {
  }

  public int add(String input) {
    CalcConfig calcConfig = new CalcConfig(input).invoke();
    String numbers = calcConfig.getNumbers();
    String delimiters = calcConfig.getDelimiters();
    return getSum(numbers, delimiters);
  }

  private int getSum(String numbers, String delimiters) throws IllegalArgumentException {
    int sum = 0;
    ArrayList<Integer> negatives = new ArrayList<>();
    if (!numbers.isEmpty()) {
      String[] operands = numbers.split(delimiters);
      for (String operand : operands) {
        int n = parseInt(operand);
        if (n <= 1000) {
          if (n < 0) {
            negatives.add(n);
          }
          sum += n;
        }
      }
      if (!negatives.isEmpty()) {
        throw new IllegalArgumentException("negatives not allowed : " + negatives.toString());
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
        Pattern p2 = Pattern
            .compile("(?<=\\[)(.*)(?=\\])");
        Matcher m2 = p2.matcher(delimiters);
        if (m2.find()) {
          delimiters = escapeSpecialRegexChars(m2.group(1));
        }
      }
      return this;
    }

    /**
     * Escapes all special regex characters
     * REF: https://stackoverflow.com/a/25853507/2195344
     *
     * @param str input
     * @return string with special regex characters escaped
     */
    String escapeSpecialRegexChars(String str) {
      Pattern SPECIAL_REGEX_CHARS = Pattern.compile("[{}()\\[\\].+*?^$\\\\|]");
      return SPECIAL_REGEX_CHARS.matcher(str).replaceAll("\\\\$0");
    }

  }


}
