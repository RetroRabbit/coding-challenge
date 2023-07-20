package com.staffinghub.coding.challenges.retry.core.entities;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Message used for queueing incoming email notifications.
 */
@Data
@Builder
public class EmailNotificationMessage implements Serializable {

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

    @Builder.Default
    private LocalDateTime dueTimestamp = LocalDateTime.now();

    @Builder.Default
    private Integer retryAttempts = 0;

    private EmailNotification emailNotification;
}
