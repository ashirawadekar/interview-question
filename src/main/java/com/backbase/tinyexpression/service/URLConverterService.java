package com.backbase.tinyexpression.service;

/**
 * Operations supported by URL converter service.
 */
public interface URLConverterService {

    /**
     * Converts a given long url to tiny expression.
     *
     * @param url the long url to convert
     * @return the tiny expression
     */
    String convertToTinyExpression(String url);

    /**
     * Converts a given tiny expression to long url.
     *
     * @param  tinyExpression the tiny expression
     * @return the long url
     */
    String convertToLongURL(String tinyExpression);
}
