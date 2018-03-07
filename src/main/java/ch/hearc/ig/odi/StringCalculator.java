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

    static final String MULTIPLE_DELIMITERS_REGEX = "\\[(.*?)\\]";
    static final String CUSTOM_DELIMITER_REGEX = "(?<=//)(.*)(?=\n)";
    static final String STANDARD_DELIMITERS = "[,\n]";
    private String input;
    private String numbers;
    private String delimiters;


    CalcConfig(String input) {
      this.input = input;
      delimiters = STANDARD_DELIMITERS;
    }

    String getNumbers() {
      return numbers;
    }

    String getDelimiters() {
      return delimiters;
    }

    CalcConfig invoke() {
      Pattern customDelimiterPtn = Pattern.compile(CUSTOM_DELIMITER_REGEX);
      Matcher customDelimiterMatcher = customDelimiterPtn.matcher(input);
      numbers = input;
      if (customDelimiterMatcher.find()) {
        delimiters = customDelimiterMatcher.group(1);
        numbers = numbers.substring(customDelimiterMatcher.end(1) + 1);
        Pattern multipleDelimitersPtn = Pattern.compile(MULTIPLE_DELIMITERS_REGEX);
        Matcher multipleDelimitersMatcher = multipleDelimitersPtn.matcher(delimiters);
        StringBuilder b = new StringBuilder();
        while (multipleDelimitersMatcher.find()) {
          if (b.length() > 0) {
            b.append("|");
          }
          b.append("(");
          b.append(escapeSpecialRegexChars(multipleDelimitersMatcher.group(1)));
          b.append(")");
        }
        if (b.length() > 0) {
          delimiters = b.toString();
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
