package com.backbase.tinyexpression.repository;

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
     * Find link using tiny expression.
     * @param tinyExpression tiny expression.
     * @return Optional Link.
     */
    Optional<Link> findByTinyExpression(String tinyExpression);
}
