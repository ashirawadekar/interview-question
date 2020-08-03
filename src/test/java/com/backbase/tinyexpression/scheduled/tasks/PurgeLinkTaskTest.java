package com.backbase.tinyexpression.scheduled.tasks;


import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.backbase.tinyexpression.model.Link;
import com.backbase.tinyexpression.repository.ArchivedLinkRepository;
import com.backbase.tinyexpression.repository.LinkRepository;

/**
 * Test cases for {@link PurgeLinkTask}.
 */
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class PurgeLinkTaskTest {

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private ArchivedLinkRepository archivedLinkRepository;

    private PurgeLinkTask purgeLinkTask;

    /**
     * Test Setup.
     */
    @BeforeEach
    public void setUp(){
        purgeLinkTask = new PurgeLinkTask(linkRepository, archivedLinkRepository);
        linkRepository.saveAll(createTestLinks());
    }

    /**
     * Verifies that 10 links older than 30 minutes are purged and active links are still valid.
     */
    @Test
    public void shouldCallPurgeJobAndArchiveLinksOlderThan30Minutes() {

        assertThat(linkRepository.findAll().size()).isEqualTo(15);

        purgeLinkTask.purgeExpiredLinks();

        assertThat(linkRepository.findAll().size()).isEqualTo(5);
        assertThat(archivedLinkRepository.findAll().size()).isEqualTo(10);
    }

    private List<Link> createTestLinks() {
        List<Link> links = new ArrayList<>();
        links.add(new Link("https://backbase.com/jobs/1", "2MhdgDP6Da", LocalDateTime.now().minusMinutes(30), LocalDateTime.now()));
        links.add(new Link("https://backbase.com/jobs/2", "2MhdgDP6Db", LocalDateTime.now().minusMinutes(30), LocalDateTime.now()));
        links.add(new Link("https://backbase.com/jobs/3", "2MhdgDP6Dc", LocalDateTime.now().minusMinutes(30), LocalDateTime.now()));
        links.add(new Link("https://backbase.com/jobs/4", "2MhdgDP6Dd", LocalDateTime.now().minusMinutes(30), LocalDateTime.now()));
        links.add(new Link("https://backbase.com/jobs/5", "2MhdgDP6De", LocalDateTime.now().minusMinutes(30), LocalDateTime.now()));
        links.add(new Link("https://backbase.com/jobs/6", "2MhdgDP6Df", LocalDateTime.now().minusMinutes(30), LocalDateTime.now()));
        links.add(new Link("https://backbase.com/jobs/7", "2MhdgDP6Dg", LocalDateTime.now().minusMinutes(30), LocalDateTime.now()));
        links.add(new Link("https://backbase.com/jobs/8", "2MhdgDP6Dh", LocalDateTime.now().minusMinutes(30), LocalDateTime.now()));
        links.add(new Link("https://backbase.com/jobs/9", "2MhdgDP6Di", LocalDateTime.now().minusMinutes(30), LocalDateTime.now()));
        links.add(new Link("https://backbase.com/jobs/10","2MhdgDP6Dj", LocalDateTime.now().minusMinutes(30), LocalDateTime.now()));
        links.add(new Link("https://backbase.com/jobs/11","2MhdgDP6Dk", LocalDateTime.now(), LocalDateTime.now().plusMinutes(30)));
        links.add(new Link("https://backbase.com/jobs/12","2MhdgDP6Dl", LocalDateTime.now(), LocalDateTime.now().plusMinutes(30)));
        links.add(new Link("https://backbase.com/jobs/13","2MhdgDP6Dm", LocalDateTime.now(), LocalDateTime.now().plusMinutes(30)));
        links.add(new Link("https://backbase.com/jobs/14","2MhdgDP6Dn", LocalDateTime.now(), LocalDateTime.now().plusMinutes(30)));
        links.add(new Link("https://backbase.com/jobs/15","2MhdgDP6D0", LocalDateTime.now(), LocalDateTime.now().plusMinutes(30)));
        return links;
    }
}
