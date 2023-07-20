package com.staffinghub.coding.challenges.dependency.notifications;

import com.staffinghub.coding.challenges.dependency.inquiry.CreateInquiryEvent;
import com.staffinghub.coding.challenges.dependency.inquiry.InquiryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Notification service sends email and push notifications based off of incoming events.
 */
@Component
public class NotificationService {

    private EmailHandler emailHandler;
    private PushNotificationHandler pushNotificationHandler;
    private static final Logger LOG = LoggerFactory.getLogger(InquiryService.class);

    public NotificationService(EmailHandler emailHandler, PushNotificationHandler pushNotificationHandler) {
        this.emailHandler = emailHandler;
        this.pushNotificationHandler = pushNotificationHandler;
    }

    /**
     * Process create Inquiry event to send emails and push notifications.
     * @param event incoming Inquiry event of type {@link CreateInquiryEvent}
     */
    @Async
    @EventListener
    public void notificationEvent(CreateInquiryEvent event) {
        LOG.info("Received notification by event for inquiry {} in date {}.",
                event.getInquiry(),
                event.getDate());
        emailHandler.sendEmail(event.getInquiry());
        pushNotificationHandler.sendNotification(event.getInquiry());
    }
}
