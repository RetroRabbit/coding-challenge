package com.staffinghub.coding.challenges.retry.outbound;

import com.staffinghub.coding.challenges.retry.core.entities.EmailNotification;
import com.staffinghub.coding.challenges.retry.core.entities.EmailNotificationMessage;
import com.staffinghub.coding.challenges.retry.core.outbound.NotificationSender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class EmailNotificationSenderServiceIntegrationTest {

    @Autowired
    private NotificationSender emailNotificationSenderService;

    private EmailNotification validEmail = EmailNotification.builder().recipient("testEmai@test.com").subject("Test subject").text("Test message").build();

    @Test
    public void sendValidEmail() {
        assertDoesNotThrow(() -> emailNotificationSenderService.sendEmail(validEmail));
    }

    @Test
    public void sendNotificationWithoutRetry() {
        assertDoesNotThrow(() -> emailNotificationSenderService.notificationEvent(EmailNotificationMessage.builder().emailNotification(validEmail).build()));
    }
}
