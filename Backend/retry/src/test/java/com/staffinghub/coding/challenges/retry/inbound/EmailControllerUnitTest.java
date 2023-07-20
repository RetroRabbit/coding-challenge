package com.staffinghub.coding.challenges.retry.inbound;

import com.staffinghub.coding.challenges.retry.core.entities.EmailNotification;
import com.staffinghub.coding.challenges.retry.core.inbound.NotificationHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.staffinghub.coding.challenges.retry.utility.JsonHelpers.asJsonString;
import static com.staffinghub.coding.challenges.retry.utility.JsonHelpers.asObject;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmailController.class)
public class EmailControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificationHandler notificationHandler;

    @Test
    public void createEmailNotificationShouldReturnMessageFromService() throws Exception {

        EmailNotification emailNotification = EmailNotification
                .builder()
                .recipient("test@retry.com")
                .subject("Testing email notification")
                .text("Some test text.")
                .build();

        when(notificationHandler.processEmailNotification(any())).thenReturn(emailNotification);
        MvcResult result = this.mockMvc.perform(
                post("/v1/emails")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(emailNotification)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        EmailNotification resultObject = asObject(result.getResponse().getContentAsString(), EmailNotification.class);
        assertThat(resultObject)
                .usingRecursiveComparison()
                .isEqualTo(emailNotification);
    }
}
