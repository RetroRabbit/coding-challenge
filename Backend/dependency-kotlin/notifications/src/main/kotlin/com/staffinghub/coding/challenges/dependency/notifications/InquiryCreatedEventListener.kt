package com.staffinghub.coding.challenges.dependency.notifications
import com.staffinghub.coding.challenges.dependency.inquiry.Inquiry
import com.staffinghub.coding.challenges.dependency.inquiry.InquiryCreatedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

/**
 * This class is responsible for handling the creation of inquiries and triggering appropriate actions such as sending
 * emails and push notifications.
 */
@Component
class InquiryCreatedEventListener(
    private val emailHandler: EmailHandler,
    private val pushNotificationHandler: PushNotificationHandler
) {

    /**
     * Listens to the InquiryCreatedEvent and handles it by sending an email and a push notification for the newly
     * created inquiry.
     *
     * @param event The InquiryCreatedEvent object representing the event triggered upon inquiry creation.
     */
    @EventListener
    fun handleInquiryCreatedEvent(event: InquiryCreatedEvent) {
        val inq: Inquiry = event.inquiry

        emailHandler.sendEmail(inq)
        pushNotificationHandler.sendNotification(inq)
    }
}
