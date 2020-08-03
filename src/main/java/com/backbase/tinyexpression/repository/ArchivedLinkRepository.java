package com.backbase.tinyexpression.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backbase.tinyexpression.model.ArchivedLink;

/**
 * JPA Archived Link Repository.
 */
@Repository
public interface ArchivedLinkRepository extends JpaRepository<ArchivedLink, Long> {

}
