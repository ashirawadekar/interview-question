package com.backbase.tinyexpression.service;

import org.springframework.stereotype.Service;

import com.backbase.tinyexpression.util.IDToURLData;

/**
 * Implementation for URL converter service.
 */
@Service
public class URLConverterServiceImpl implements URLConverterService {

    /**
     * Converts a given long url to tiny expression.
     *
     * @param url the long url to convert
     * @return the tiny expression
     */
    @Override
    public String convertToTinyExpression(String url) {
        return IDToURLData.saveLongURL(url);
    }

    /**
     * Converts a given tiny expression to long url.
     *
     * @param tinyExpression the tiny expression
     * @return the long url
     */
    @Override
    public String convertToLongURL(String tinyExpression) {
        return IDToURLData.findLongURL(tinyExpression);
    }
}
