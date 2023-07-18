package com.staffinghub.coding.challenges.dependency.notifications

import com.staffinghub.coding.challenges.dependency.inquiry.Inquiry
import org.springframework.context.ApplicationEvent

class InquiryCreatedEvent(source: Any, val inquiry: Inquiry) : ApplicationEvent(source){

}
