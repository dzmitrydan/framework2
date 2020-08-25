package util;

import org.apache.commons.lang3.StringUtils;

public class StringExtractor {

    public static String deleteTextBeforeColon(String string) {
        String[] words = string.split(":");
        return words[words.length - 1].trim();
    }

    public static String deleteTextInTheBrackets(String string) {
        return string.replaceAll("\\([^()]*\\)", "").trim();
    }

    public static String stringCapitalize(String string) {
        return StringUtils.capitalize(string);
    }

}
