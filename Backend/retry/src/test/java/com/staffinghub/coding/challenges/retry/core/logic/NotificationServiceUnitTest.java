package com.staffinghub.coding.challenges.retry.core.logic;

import com.staffinghub.coding.challenges.retry.core.entities.EmailNotification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.PollableChannel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceUnitTest {

    @Mock
    private PollableChannel channel;

    @InjectMocks
    private NotificationService notificationService;

    @Test
    public void submitEmailNotificationSuccessfully() {
        doReturn(true).when(channel).send(any());

        EmailNotification notification = EmailNotification.builder().recipient("testRecipient@test.com").subject("Test subject line").text("Test text").build();
        EmailNotification response = notificationService.processEmailNotification(notification);

        assertThat(response)
                .usingRecursiveComparison()
                .isEqualTo(notification);
    }
}
