package com.staffinghub.coding.challenges.dependency.inquiry

import org.springframework.context.ApplicationEvent

class InquiryCreatedEvent(source: Any, val inquiry: Inquiry) : ApplicationEvent(source){

}
