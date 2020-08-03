package com.backbase.tinyexpression.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backbase.tinyexpression.model.Link;

/**
 * JPA Link Repository.
 */
@Repository
public interface LinkRepository extends JpaRepository<Link, Long>{

    /**
     * Find link using tiny expression and expiration time after time passed, to get links that are not expired.
     *
     * @param tinyExpression tiny expression
     * @param timeAfter time after
     * @return Optional Link.
     */
    Optional<Link> findByTinyExpressionAndExpirationTimeAfter(String tinyExpression, LocalDateTime timeAfter);

    /**
     * Find all links before expiration time passed.
     *
     * @param timeBefore time before.
     * @return list of expired links.
     */
    List<Link> findAllByExpirationTimeBefore(LocalDateTime timeBefore);
}
