package com.staffinghub.coding.challenges.retry.core.logic;

import com.staffinghub.coding.challenges.retry.core.entities.EmailNotification;
import com.staffinghub.coding.challenges.retry.core.entities.EmailNotificationMessage;
import com.staffinghub.coding.challenges.retry.core.inbound.NotificationHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.MessageBuilder;

@Slf4j
public class NotificationService implements NotificationHandler {

    private PollableChannel channel;

    private final MessagingTemplate messagingTemplate = new MessagingTemplate();

    public NotificationService(PollableChannel channel) {
        this.channel = channel;
    }

    /** {@inheritDoc} */
    @Override
    public EmailNotification processEmailNotification(EmailNotification emailNotification) {
        messagingTemplate.send(channel, MessageBuilder.withPayload(EmailNotificationMessage
                .builder()
                .emailNotification(emailNotification)
                .build()).build());
        log.info("Message queued: {}", emailNotification);
        return emailNotification;
    }
}
