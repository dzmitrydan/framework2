package util;

import org.apache.commons.lang3.StringUtils;

public class StringExtractor {

    public static String deleteTextBeforeColon(String string) {
        String[] words = string.split(":");
        return words[words.length - 1].trim();
    }

    public static String stringCapitalize(String string) {
        return StringUtils.capitalize(string);
    }

    public static double stringToDouble(String number) {
        return Double.parseDouble(number.replaceAll("per.+month|[^\\d.]", ""));
    }

}
