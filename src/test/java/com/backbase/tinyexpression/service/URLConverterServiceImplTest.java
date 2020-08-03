package com.backbase.tinyexpression.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.backbase.tinyexpression.exception.RecordNotFoundException;
import com.backbase.tinyexpression.repository.LinkRepository;
import com.backbase.tinyexpression.util.SequenceGenerator;

/**
 * Test cases for {@link URLConverterService}.
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class URLConverterServiceImplTest {

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private SequenceGenerator sequenceGenerator;

    private URLConverterService urlConverterService;

    private static String TINY_EXPRESSION;

    /**
     * Test Setup.
     */
    @BeforeEach
    public void setUp(){
        urlConverterService = new URLConverterServiceImpl(linkRepository, sequenceGenerator);
    }

    /**
     * Tests service is able to convert a long url to tiny expression.
     */
    @Test
    @Order(1)
    public void shouldReturnATinyExpression() {
        TINY_EXPRESSION = urlConverterService.convertToTinyExpression("https://stash.backbase.com/projects/PO/repos/payment-order-integration-spec");
        assertThat(TINY_EXPRESSION).isNotNull();
    }

    /**
     * Tests service is able to retrieve a long url using the tiny expression.
     */
    @Test
    @Order(2)
    public void shouldReturnALongURL() {
        String url = urlConverterService.convertToLongURL(TINY_EXPRESSION);
        assertThat(url).isNotNull();
        assertThat(url).isEqualTo("https://stash.backbase.com/projects/PO/repos/payment-order-integration-spec");
    }

    /**
     * Tests service be used to save a long url and retrieve it using the tiny expression.
     */
    @Test
    @Order(3)
    public void shouldSaveATinyExpressionAndReturnALongURL() {
        String expression = urlConverterService.convertToTinyExpression("https://stash.backbase.com");
        assertThat(expression).isNotNull();
        String url = urlConverterService.convertToLongURL(expression);
        assertThat(url).isNotNull();
        assertThat(url).isEqualTo("https://stash.backbase.com");
    }

    /**
     * Tests service catches exception for invalid expression.
     */
    @Test
    @Order(4)
    public void shouldReturnNotFoundForInvalidTinyExpression() {
        Exception exception = assertThrows(RecordNotFoundException.class,
                () -> urlConverterService.convertToLongURL("random")
        );
        assertThat(exception.getMessage()).isEqualTo("URL not found in DB");
    }
}
