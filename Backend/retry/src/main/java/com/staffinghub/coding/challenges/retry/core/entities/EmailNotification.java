package com.staffinghub.coding.challenges.retry.core.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailNotification implements Serializable {

    @NotBlank
    private String recipient;

    @NotBlank
    private String subject;

    @NotBlank
    private String text;
}
