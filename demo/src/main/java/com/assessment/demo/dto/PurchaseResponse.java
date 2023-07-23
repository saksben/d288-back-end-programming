package com.assessment.demo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PurchaseResponse {

    private final String orderTrackingNumber;
}
