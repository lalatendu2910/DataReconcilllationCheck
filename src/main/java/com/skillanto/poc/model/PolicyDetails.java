package com.skillanto.poc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class PolicyDetails {

    private String policyNumber;
    private String productCode;
    private LocalDate policyIssueDate;
    private double annualPremium;
    private String currency;
    private String policyStatus;


}
