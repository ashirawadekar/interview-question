package com.backbase.tinyexpression.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backbase.tinyexpression.exception.RecordNotFoundException;
import com.backbase.tinyexpression.service.URLConverterService;
import com.backbase.tinyexpression.util.URLValidator;

/**
 * Rest controller for creating tiny expression from long url and getting the long url back using tiny expression.
 */
@RestController
public class URLController {

    private static final Logger LOGGER = LoggerFactory.getLogger(URLController.class);

    @Autowired
    private URLConverterService urlConverterService;

    /**
     * Default Constructor.
     */
    public URLController() {
    }

    /**
     * Handles POST requests to convert long url to tiny expression.
     *
     * @param url the long url.
     * @return the tiny expression.
     */
    @PostMapping(value = "/short")
    public ResponseEntity<String> tinyExpression(@RequestParam(name = "url") String url) {

        LOGGER.info("Received request to convert url={} to tiny expression", url);

        if (!URLValidator.isValidURL(url)) {
            LOGGER.error("Invalid url expression url={}", url);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid url expression");
        }

        String tinyExpression = urlConverterService.convertToTinyExpression(removeFragmentFromURL(url));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(tinyExpression);
    }

    /**
     * Handles GET requests to convert tiny expression to long url.
     *
     * @param tinyExpression the tiny expression
     * @return the long url
     */
    @GetMapping(value = "/long")
    public ResponseEntity<String> longURL(@RequestParam(name = "tiny") String tinyExpression) {
        LOGGER.info("Received request to find long url for tinyExpression={}", tinyExpression);
        try {
            String longURL = urlConverterService.convertToLongURL(tinyExpression);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(longURL);
        } catch (RecordNotFoundException e) {
            LOGGER.warn("Failed to find long url for tinyExpression={}", tinyExpression, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    /**
     * Remove fragment from url.
     *
     * @param url the url
     * @return cleaned url
     */
    private String removeFragmentFromURL(String url) {
        if(url.contains("#")) {
            return url.substring(0, url.indexOf("#"));
        }
        return url;
    }
}
