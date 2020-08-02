package com.backbase.tinyexpression.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Test cases for {@link URLValidator}.
 */
public class URLValidatorTest {

    /**
     * Tests if a url is valid.
     */
    @Test
    public void validateURL() {
        assertThat(URLValidator.isValidURL("www.abcd.com")).isFalse();
        assertThat(URLValidator.isValidURL("http://www.abcd.com")).isTrue();
        assertThat(URLValidator.isValidURL("htpp:/www.abcd.com")).isFalse();
        assertThat(URLValidator.isValidURL("http://abcd.com")).isTrue();
        assertThat(URLValidator.isValidURL("ftp://username:password@ftp.xyz.com")).isTrue();
        assertThat(URLValidator.isValidURL("tp://www.abcd.com")).isFalse();
        assertThat(URLValidator.isValidURL("file://localhost/users/workspace")).isTrue();
        assertThat(URLValidator.isValidURL("fil://localhost/users/workspace")).isFalse();
    }
}
