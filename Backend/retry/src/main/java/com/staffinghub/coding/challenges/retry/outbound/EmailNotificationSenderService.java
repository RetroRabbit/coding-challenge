package com.staffinghub.coding.challenges.retry.outbound;

import com.staffinghub.coding.challenges.retry.core.entities.EmailNotification;
import com.staffinghub.coding.challenges.retry.core.entities.EmailNotificationMessage;
import com.staffinghub.coding.challenges.retry.core.outbound.NotificationSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.SECONDS;

@Service
@Validated
@Slf4j
public class EmailNotificationSenderService implements NotificationSender {

    private static final String SENDER_ADDRESS = "info@test.com";

    private JavaMailSender mailSender;

    private PollableChannel channel;

    private final MessagingTemplate messagingTemplate = new MessagingTemplate();

    public EmailNotificationSenderService(JavaMailSender mailSender, PollableChannel channel) {
        this.mailSender = mailSender;
        this.channel = channel;
    }

    /** {@inheritDoc} */
    @Async
    @EventListener
    @Override
    public void notificationEvent(EmailNotificationMessage emailNotificationMessage) {
        log.info("Received notification by event for email {} in date {}.",
                emailNotificationMessage,
                emailNotificationMessage.getTimestamp());
        try {
            this.sendEmail(emailNotificationMessage.getEmailNotification());
        } catch(Exception ex) {
            if (emailNotificationMessage.getRetryAttempts() > 5) {
                return;
            }

            emailNotificationMessage.setRetryAttempts(emailNotificationMessage.getRetryAttempts() + 1);
            emailNotificationMessage.setDueTimestamp(LocalDateTime.now().plus(5 * (10^emailNotificationMessage.getRetryAttempts()), SECONDS));
            messagingTemplate.send(channel, MessageBuilder.withPayload(emailNotificationMessage).build());
            log.info("Message re-queued: {}", emailNotificationMessage.getEmailNotification());
        }

    }

    /** {@inheritDoc} */
    @Async
    @Override
    public void sendEmail(@Valid @NotNull EmailNotification emailNotification) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(SENDER_ADDRESS);
            mailMessage.setTo(emailNotification.getRecipient());
            mailMessage.setSubject(emailNotification.getSubject());
            mailMessage.setText(emailNotification.getText());

            mailSender.send(mailMessage);
            log.info("Successfully sent: {}", emailNotification);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Failed to send email to recipient: %s", emailNotification.getRecipient()));
        }
    }
}
