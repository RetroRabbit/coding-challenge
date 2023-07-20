package com.staffinghub.coding.challenges.dependency.inquiry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * The inquiry service is used to create new inquiries.
 */
@Component
public class InquiryService {

    private final ApplicationEventPublisher events;

    private static final Logger LOG = LoggerFactory.getLogger(InquiryService.class);

    public InquiryService(ApplicationEventPublisher events) {
        this.events = events;
    }

    /**
     * Create a new inquiry.
     * @param inquiry incoming inquiry to create of type {@link Inquiry}
     */
    public void create(final Inquiry inquiry) {
        LOG.info("User sent inquiry: {}", inquiry);
        events.publishEvent(new CreateInquiryEvent(new Date(), inquiry));
    }
}