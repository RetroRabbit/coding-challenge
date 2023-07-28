package com.staffinghub.coding.challenges.dependency.inquiry

import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.context.ApplicationEventPublisher

private val logger = KotlinLogging.logger {}

/**
 * This class represents a service responsible for creating inquiries and publishing an event when an inquiry is created.
 */
@Component
class InquiryService(private val applicationEventPublisher: ApplicationEventPublisher) {

    /**
     * Creates an inquiry and publishes an event for the newly created inquiry.
     *
     * @param inquiry The Inquiry object representing the inquiry to be created.
     */
    fun create(inquiry: Inquiry) {
        logger.info {
            "User sent inquiry: $inquiry"
        }
        applicationEventPublisher.publishEvent(InquiryCreatedEvent(this, inquiry))
    }
}

data class Inquiry(
    var username: String,
    var recipient: String,
    var text: String,
)
