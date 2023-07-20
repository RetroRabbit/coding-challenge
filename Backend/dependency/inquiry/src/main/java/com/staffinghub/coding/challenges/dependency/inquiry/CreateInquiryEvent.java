package com.staffinghub.coding.challenges.dependency.inquiry;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateInquiryEvent {
    private Date date;
    private Inquiry inquiry;
}
