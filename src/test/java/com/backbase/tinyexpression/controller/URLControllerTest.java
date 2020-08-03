package com.backbase.tinyexpression.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

/**
 * Test cases for {@link URLController}.
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class URLControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static String tinyExpression;

    /**
     * Tests when a post request is received for long url, returns a tiny expression.
     *
     * @throws Exception exception.
     */
    @Test
    @Order(1)
    public void shouldReturnATinyExpression() throws Exception {
        MvcResult result = mockMvc.perform(post("/short")
                .param("url", "https://stash.backbase.com/projects/PO/repos/payment-order-integration-spec/browse/src/main/resources/schemas/definitions.json#38"))
                .andExpect(status().isCreated()).andReturn();
        tinyExpression = result.getResponse().getContentAsString();
        assertThat(tinyExpression).isNotNull();
    }

    /**
     * Tests when a Get request is received for a valid tiny expression, the long url associated with it is returned.
     *
     * @throws Exception exception.
     */
    @Test
    @Order(2)
    public void shouldReturnALongURL() throws Exception {
        MvcResult result = mockMvc.perform(get("/long")
                .param("tiny", tinyExpression))
                .andExpect(status().isOk()).andReturn();
        assertThat(result.getResponse().getContentAsString()).isEqualTo("https://stash.backbase.com/projects/PO/repos/payment-order-integration-spec/browse/src/main/resources/schemas/definitions.json");
    }

    /**
     * Tests a POST request for converting a long url to tiny expression and submits a GET request to retrieve the long url.
     *
     * @throws Exception exception
     */
    @Test
    @Order(3)
    public void shouldSaveATinyExpressionAndReturnALongURL() throws Exception {
        MvcResult resultOne = mockMvc.perform(post("/short")
                .param("url", "https://stash.backbase.com/projects/PO"))
                .andExpect(status().isCreated()).andReturn();
        String expression = resultOne.getResponse().getContentAsString();
        MvcResult resultTwo = mockMvc.perform(get("/long")
                .param("tiny", expression))
                .andExpect(status().isOk()).andReturn();
        assertThat(resultTwo.getResponse().getContentAsString()).isEqualTo("https://stash.backbase.com/projects/PO");
    }

    /**
     * Tests when a Get request is received for an invalid tiny expresion, correct response and error are returned.
     *
     * @throws Exception exception.
     */
    @Test
    @Order(4)
    public void shouldReturnNotFoundForInvalidTinyExpression() throws Exception {
        MvcResult result = mockMvc.perform(get("/long")
                .param("tiny", "abcd"))
                .andExpect(status().isNotFound()).andReturn();
        assertThat(result.getResponse().getContentAsString()).isEqualTo("URL not found in DB");
    }

    /**
     * Tests when a Post request is received the url param sent is valid and returns correct response for invalid urls.
     *
     * @throws Exception exception.
     */
    @Test
    @Order(5)
    public void shouldReturnBadRequestForInvalidURL() throws Exception {
        MvcResult result = mockMvc.perform(post("/short")
                .param("url", "abcd.com"))
                .andExpect(status().isBadRequest()).andReturn();
        assertThat(result.getResponse().getContentAsString()).isEqualTo("Invalid url expression");
    }
}
