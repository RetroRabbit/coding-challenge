package com.staffinghub.coding.challenges.retry.inbound;

import com.staffinghub.coding.challenges.retry.core.entities.EmailNotification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.staffinghub.coding.challenges.retry.utility.JsonHelpers.asJsonString;
import static com.staffinghub.coding.challenges.retry.utility.JsonHelpers.asObject;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmailControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createEmailNotificationShouldReturnMessageFromService() throws Exception {

        EmailNotification emailNotification = EmailNotification
                .builder()
                .recipient("test@retry.com")
                .subject("Testing email notification")
                .text("Some test text.")
                .build();

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
