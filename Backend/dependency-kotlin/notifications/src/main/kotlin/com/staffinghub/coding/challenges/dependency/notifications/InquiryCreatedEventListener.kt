package com.staffinghub.coding.challenges.dependency.notifications
import com.staffinghub.coding.challenges.dependency.inquiry.InquiryCreatedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class InquiryCreatedEventListener(
    private val emailHandler: EmailHandler,
    private val pushNotificationHandler: PushNotificationHandler
) {

    @EventListener
    fun handleInquiryCreatedEvent(event: InquiryCreatedEvent) {
        val inquiry = event.inquiry

        emailHandler.sendEmail(inquiry)
        pushNotificationHandler.sendNotification(inquiry)
    }
}