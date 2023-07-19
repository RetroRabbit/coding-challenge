package com.staffinghub.coding.challenges.dependency.inquiry

import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.context.ApplicationEventPublisher

private val logger = KotlinLogging.logger {}

@Component
class InquiryService (private val applicationEventPublisher: ApplicationEventPublisher) {
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
