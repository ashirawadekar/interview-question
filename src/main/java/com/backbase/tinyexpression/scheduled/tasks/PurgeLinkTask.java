package com.backbase.tinyexpression.scheduled.tasks;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.backbase.tinyexpression.model.ArchivedLink;
import com.backbase.tinyexpression.model.Link;
import com.backbase.tinyexpression.repository.ArchivedLinkRepository;
import com.backbase.tinyexpression.repository.LinkRepository;

/**
 * Scheduled task to purge links older than 30 minutes, links are purged and moved to archived_links table.
 */
@Component
public class PurgeLinkTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(PurgeLinkTask.class);

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private ArchivedLinkRepository archivedLinkRepository;

    /**
     * Default constructor.
     */
    public PurgeLinkTask() {
    }

    /**
     * Constructor For testing.
     *
     * @param linkRepository link repository.
     * @param archivedLinkRepository archived link repository.
     */
    public PurgeLinkTask(LinkRepository linkRepository, ArchivedLinkRepository archivedLinkRepository) {
        this.linkRepository = linkRepository;
        this.archivedLinkRepository = archivedLinkRepository;
    }

    /**
     * The scheduled task to purge expired links.
     */
    @Scheduled(cron = "${cron.expression}")
    public void purgeExpiredLinks() {
        LOGGER.info("Started purge job at={}", LocalDateTime.now());

        List<Link> expiredLinks = linkRepository.findAllByExpirationTimeBefore(LocalDateTime.now());

        if (expiredLinks.size() > 0) {
            LOGGER.info("Purging number of expiredLinks={}", expiredLinks.size());
            List<ArchivedLink> archivedLinks = new ArrayList<>();
            expiredLinks.forEach(link ->
                    archivedLinks.add(new ArchivedLink(link.getId(),
                            link.getLongUrl(),
                            link.getTinyExpression(),
                            link.getCreateTime(),
                            link.getExpirationTime())
                    ));
            archivedLinkRepository.saveAll(archivedLinks);

            linkRepository.deleteAll(expiredLinks);
        }

        LOGGER.info("Finished purge job at={}", LocalDateTime.now());
    }
}
