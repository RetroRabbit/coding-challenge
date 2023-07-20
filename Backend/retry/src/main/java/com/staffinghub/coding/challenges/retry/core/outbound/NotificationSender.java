package com.staffinghub.coding.challenges.retry.core.outbound;

import com.staffinghub.coding.challenges.retry.core.entities.EmailNotification;
import com.staffinghub.coding.challenges.retry.core.entities.EmailNotificationMessage;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface NotificationSender {

    /**
     * Validates and submits a given emailNotification {@link EmailNotification} to send a new email
     * @param emailNotification {@link EmailNotification}
     */
    void sendEmail(@Valid @NotNull EmailNotification emailNotification);

    /**
     * Re-queues a given emailNotificationMessage to retry the notification for a max of five tries using exponential back-off
     * @param emailNotificationMessage {@link EmailNotificationMessage}
     */
    void notificationEvent(@Valid @NotNull EmailNotificationMessage emailNotificationMessage);
}
