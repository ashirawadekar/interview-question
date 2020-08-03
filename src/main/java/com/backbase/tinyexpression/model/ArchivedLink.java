package com.backbase.tinyexpression.model;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.Data;

/**
 * Archived Link Entity.
 */
@Entity
@Table(
        name = "archived_links",
        indexes = @Index(
                name = "idax_tiny_expression",
                columnList = "tiny_expression",
                unique = true
        )
)
public @Data class ArchivedLink {

    @Id
    private long id;

    @Column(name = "long_url", nullable = false, columnDefinition = "TEXT")
    private String longUrl;

    @Column(name = "tiny_expression", nullable = false, length = 10)
    private String tinyExpression;

    @Column(name = "create_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime createTime;

    @Column(name = "expiration_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime expirationTime;

    /**
     * Default Constructor.
     */
    public ArchivedLink() {
    }

    /**
     * Constructor.
     * @param id id.
     * @param longUrl long url.
     * @param tinyExpression tiny expression.
     * @param createTime create time.
     * @param expirationTime expiration time.
     */
    public ArchivedLink(long id, String longUrl, String tinyExpression, LocalDateTime createTime, LocalDateTime expirationTime) {
        this.id = id;
        this.longUrl = longUrl;
        this.tinyExpression = tinyExpression;
        this.createTime = createTime;
        this.expirationTime = expirationTime;
    }
}
