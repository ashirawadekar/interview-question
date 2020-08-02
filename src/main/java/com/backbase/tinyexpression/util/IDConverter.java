package com.backbase.tinyexpression.util;

/**
 * A Util to convert a given id to base 62 expression.
 */
public class IDConverter {

    /**
     * Helper method to get short expression from id.
     * @param id the id.
     * @return the tiny expression.
     */
    public static String idToTinyExpression(int id) {

        char map[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

        StringBuilder shortURL = new StringBuilder();

        while(id > 0) {
            shortURL.append(map[id % 62]);
            id = id / 62;
        }
        return shortURL.reverse().toString();
    }

    /**
     * Helper method to get the id using the tiny expression.
     * @param tinyExpression
     * @return
     */
    public static int tinyExpressionToID(String tinyExpression) {
        int id = 0;

        for(char ch : tinyExpression.toCharArray()) {
            if('a' <= ch && ch <= 'z'){
                id = id * 62 + ch - 'a';
            }
            if('A' <= ch && ch <= 'Z') {
                id = id * 62 + ch - 'A' + 26;
            }
            if('0' <= ch && ch <= '9') {
                id = id * 62 + ch - '0' + 52;
            }
        }
        return id;
    }
}
