package com.backbase.tinyexpression.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backbase.tinyexpression.exception.RecordNotFoundException;
import com.backbase.tinyexpression.model.Link;
import com.backbase.tinyexpression.repository.LinkRepository;
import com.backbase.tinyexpression.util.IDConverter;
import com.backbase.tinyexpression.util.SequenceGenerator;

/**
 * Implementation for {@link URLConverterService}.
 */
@Service
public class URLConverterServiceImpl implements URLConverterService {

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private SequenceGenerator sequenceGenerator;

    /**
     * Default Constructor.
     */
    public URLConverterServiceImpl() {
    }

    /**
     * Constructor for testing.
     * @param linkRepository link repository
     * @param sequenceGenerator sequence generator
     */
    public URLConverterServiceImpl(LinkRepository linkRepository, SequenceGenerator sequenceGenerator) {
        this.linkRepository = linkRepository;
        this.sequenceGenerator = sequenceGenerator;
    }

    /**
     * Converts a given long url to tiny expression.
     *
     * @param url the long url to convert
     * @return the tiny expression
     */
    @Override
    public String convertToTinyExpression(String url) {
        String tinyExpression = IDConverter.idToTinyExpression(sequenceGenerator.nextId());
        linkRepository.save(new Link(url, tinyExpression, LocalDateTime.now()));
        return tinyExpression;
    }

    /**
     * Converts a given tiny expression to long url.
     *
     * @param tinyExpression the tiny expression
     * @return the long url
     */
    @Override
    public String convertToLongURL(String tinyExpression) {
        Optional<Link> link = linkRepository.findByTinyExpression(tinyExpression);
        if (link.isPresent()) {
            return link.get().getLongUrl();
        }
        throw new RecordNotFoundException();
    }
}
