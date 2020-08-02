package com.backbase.tinyexpression.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Test cases for {@link IDConverter}.
 */
public class IDConverterTest {

    /**
     * Tests that a unique tiny expression is generated for unique id.
     */
    @Test
    public void shouldReturnTinyExpressionForIDAsExpected() {
        assertThat(IDConverter.idToTinyExpression(1)).isEqualTo("b");
        assertThat(IDConverter.idToTinyExpression(2)).isEqualTo("c");
        assertThat(IDConverter.idToTinyExpression(12345)).isEqualTo("dnh");
        assertThat(IDConverter.idToTinyExpression(12346)).isEqualTo("dni");
        assertThat(IDConverter.idToTinyExpression(109090)).isEqualTo("CxG");
        assertThat(IDConverter.idToTinyExpression(207135)).isEqualTo("123");
    }

    /**
     * Tests that the unique id can be retrieved using a unique tiny expression.
     */
    @Test
    public void shouldReturnIDForTinyExpressionAsExpected() {
        assertThat(IDConverter.tinyExpressionToID("b")).isEqualTo(1);
        assertThat(IDConverter.tinyExpressionToID("c")).isEqualTo(2);
        assertThat(IDConverter.tinyExpressionToID("dnh")).isEqualTo(12345);
        assertThat(IDConverter.tinyExpressionToID("dni")).isEqualTo(12346);
        assertThat(IDConverter.tinyExpressionToID("CxG")).isEqualTo(109090);
        assertThat(IDConverter.tinyExpressionToID("123")).isEqualTo(207135);
    }
}
