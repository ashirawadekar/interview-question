package com.backbase.tinyexpression.model;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.Data;

/**
 * Link Entity.
 */
@Entity
@Table(
        name = "links",
        indexes = @Index(
                name = "idx_tiny_expression",
                columnList = "tiny_expression",
                unique = true
        )
)
public @Data class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "long_url", nullable = false, columnDefinition = "TEXT")
    private String longUrl;

    @Column(name = "tiny_expression", nullable = false, length = 10)
    private String tinyExpression;

    @Column(name = "create_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime createTime;

    /**
     * Default constructor.
     */
    public Link() {
    }

    /**
     * Constructor.
     *
     * @param longUrl long url.
     * @param tinyExpression tiny expression.
     * @param createTime create time.
     */
    public Link(String longUrl, String tinyExpression, LocalDateTime createTime) {
        this.longUrl = longUrl;
        this.tinyExpression = tinyExpression;
        this.createTime = createTime;
    }
}
