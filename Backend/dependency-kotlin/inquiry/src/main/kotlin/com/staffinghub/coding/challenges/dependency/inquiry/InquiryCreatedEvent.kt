package com.staffinghub.coding.challenges.dependency.inquiry

import org.springframework.context.ApplicationEvent

/**
 * This class represents an event that is triggered when an inquiry is created.
 */
class InquiryCreatedEvent(source: Any, val inquiry: Inquiry) : ApplicationEvent(source)