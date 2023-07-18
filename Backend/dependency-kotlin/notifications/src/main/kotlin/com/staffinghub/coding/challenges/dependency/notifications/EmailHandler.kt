package com.staffinghub.coding.challenges.dependency.notifications

import com.staffinghub.coding.challenges.dependency.inquiry.Inquiry
import mu.KotlinLogging
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}


@Component
class EmailHandler {
    fun sendEmail(inquiry: Inquiry) {
        logger.info {
            "Sending email for: $inquiry"
        }
    }
}
