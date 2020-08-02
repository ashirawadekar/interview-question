package com.backbase.tinyexpression.util;

import java.util.HashMap;
import java.util.Map;

import com.backbase.tinyexpression.exception.InvalidTinyExpressionException;

/**
 * A data store for saving the long urls.
 */
public class IDToURLData {
    private static int id = 1;
    private static Map<Integer, String> data = new HashMap<>();

    /**
     * Saves the long url to the data map.
     *
     * @param url the long url.
     * @return the tiny expression.
     */
    public static String saveLongURL(String url) {
        data.put(id, url);
        return IDConverter.idToTinyExpression(id++);
    }

    /**
     * Finds the long url using the tiny expression.
     *
     * @param tinyExpression the tiny expression.
     * @return the long url.
     */
    public static String findLongURL(String tinyExpression) {
        String longURL = data.get(IDConverter.tinyExpressionToID(tinyExpression));

        if(null != longURL) {
            return longURL;
        }
        throw new InvalidTinyExpressionException();
    }
}
