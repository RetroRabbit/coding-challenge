package com.staffinghub.coding.challenges.retry.outbound;

import com.staffinghub.coding.challenges.retry.core.entities.EmailNotification;
import com.staffinghub.coding.challenges.retry.core.entities.EmailNotificationMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.PollableChannel;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
public class EmailNotificationSenderServiceUnitTest {

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private PollableChannel channel;

    @InjectMocks
    private EmailNotificationSenderService emailNotificationSenderService;

    private EmailNotification validEmail = EmailNotification.builder().recipient("testEmai@test.com").subject("Test subject").text("Test message").build();

    @Test
    public void sendValidEmail() {
        assertDoesNotThrow(() -> emailNotificationSenderService.sendEmail(validEmail));
    }

    @Test
    public void sendValidEmailServiceFailure() {
        doThrow(RuntimeException.class).when(mailSender).send(any(SimpleMailMessage.class));
        assertThrows(RuntimeException.class, () -> emailNotificationSenderService.sendEmail(validEmail));
    }

    @Test
    public void sendNotificationWithoutRetry() {
        emailNotificationSenderService.notificationEvent(EmailNotificationMessage.builder().emailNotification(validEmail).build());
    }

    @ParameterizedTest(name = "Retry notification system: retry attempt number: {0}")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6})
    public void sendNotificationWithExponentialFallback(int retryAttempt) {
        if(retryAttempt <= 5) {
            doReturn(true).when(channel).send(any());
        }
        doThrow(RuntimeException.class).when(mailSender).send(any(SimpleMailMessage.class));
        EmailNotificationMessage validEmailNotification = EmailNotificationMessage.builder().retryAttempts(retryAttempt).emailNotification(validEmail).build();
        emailNotificationSenderService.notificationEvent(validEmailNotification);
    }
}
