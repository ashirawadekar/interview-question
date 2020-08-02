package com.backbase.tinyexpression.util;

import java.util.regex.Pattern;

/**
 * Util to validate urls.
 */
public class URLValidator {

    private static String REGEX= "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
    private static Pattern PATTERN = Pattern.compile(REGEX);

    /**
     * Checks if a given url is valid.
     * @param url the url
     * @return valid true/false.
     */
    public static boolean isValidURL(String url) {
        return PATTERN.matcher(url).matches();
    }
}
