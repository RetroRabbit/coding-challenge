package com.staffinghub.coding.challenges.retry.core.logic;

import com.staffinghub.coding.challenges.retry.core.entities.EmailNotification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.PollableChannel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

// Instead of using the SpringBootTest annotation we can reduce the testing overhead by using the ContextConfiguration annotation while specifying the contexts we need to load in our tests
@SpringBootTest
public class NotificationServiceIntegrationTest {

    @Autowired
    private NotificationService notificationService;

    @Test
    public void submitEmailNotificationSuccessfully() {
        EmailNotification notification = EmailNotification.builder().recipient("testRecipient@test.com").subject("Integration test subject").text("Test text").build();
        EmailNotification response = notificationService.processEmailNotification(notification);

        assertThat(response)
                .usingRecursiveComparison()
                .isEqualTo(notification);
    }
}
