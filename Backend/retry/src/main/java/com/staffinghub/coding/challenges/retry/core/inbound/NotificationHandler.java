package com.staffinghub.coding.challenges.retry.core.inbound;

import com.staffinghub.coding.challenges.retry.core.entities.EmailNotification;

public interface NotificationHandler {

    /**
     * Submits an emailNotification {@link EmailNotification} onto the email notification queue to handle the submission of the given notification
     * @param emailNotification {@link EmailNotification}
     * @return {@link EmailNotification} The email notification that was submitted for processing
     */
    EmailNotification processEmailNotification(EmailNotification emailNotification);
}
