package com.backbase.tinyexpression.exception;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.backbase.tinyexpression.util.IDToURLData;

/**
 * Test cases for {@link InvalidTinyExpressionException}.
 */
public class InvalidTinyExpressionExceptionTest {

    /**
     * Tests that the correct exception is thrown.
     */
    @Test
    public void exceptionShouldBeThrown() {
        Exception exception = assertThrows(InvalidTinyExpressionException.class,
                () -> IDToURLData.findLongURL("abcd")
        );
        assertThat(exception.getMessage()).isEqualTo("Not Found or Invalid Tiny Expression");
    }
}
