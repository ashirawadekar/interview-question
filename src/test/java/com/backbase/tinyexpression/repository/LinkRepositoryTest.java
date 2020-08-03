package com.backbase.tinyexpression.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.backbase.tinyexpression.model.Link;

/**
 * Test cases for {@link LinkRepository}.
 */
@SpringBootTest
public class LinkRepositoryTest {

    @Autowired
    private LinkRepository linkRepository;

    /**
     * Tests a link can be saved and retrieved from the database using the id.
     */
    @Test
    public void givenLinkRepository_WhenSaveAndRetrieveEntity_thenOK() {
        Link link = linkRepository.save(new Link("http://www.backbase.com", "tiny", LocalDateTime.now()));
        Optional<Link> linkRetrieved = linkRepository.findById(link.getId());

        assertThat(linkRetrieved.get()).isNotNull();
        assertThat(link.getLongUrl()).isEqualTo(linkRetrieved.get().getLongUrl());
    }

    /**
     * Tests when tried to retrieve a unknown link it fails.
     */
    @Test
    public void givenLinkRepository_WhenRetrieveUnknownEntity_thenFail() {
        Optional<Link> linkRetrieved = linkRepository.findByTinyExpression("random");

        assertThat(linkRetrieved.isPresent()).isFalse();
    }

    /**
     * Tests a link can be saved and retrieved from database using the tiny expression.
     */
    @Test
    public void givenLinkRepository_WhenSaveAndRetrieveEntityFromExpression_thenOK() {
        Link link = linkRepository.save(new Link("http://www.backbase.com/tiny", "expression", LocalDateTime.now()));
        Optional<Link> linkRetrieved = linkRepository.findByTinyExpression(link.getTinyExpression());

        assertThat(linkRetrieved.get()).isNotNull();
        assertThat(link.getLongUrl()).isEqualTo(linkRetrieved.get().getLongUrl());
    }
}

