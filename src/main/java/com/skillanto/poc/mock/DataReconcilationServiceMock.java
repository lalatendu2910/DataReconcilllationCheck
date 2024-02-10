package com.skillanto.poc.mock;

import com.skillanto.poc.model.PolicyDetails;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataReconcilationServiceMock
{

       public PolicyDetails getPolicyNumberReconcilledDataFromSource(String policyNumber){
            return PolicyDetails.builder()
                    .policyNumber(policyNumber)
                    .productCode("GMR1")
                    .policyIssueDate(LocalDate.of(2023,01,01))
                    .annualPremium(12000)
                    .currency("MYR")
                    .policyStatus("PP")
                    .build();
       }

    public PolicyDetails getPolicyNumberReconcilledDataFromTarget(String policyNumber){
        return PolicyDetails.builder()
                .policyNumber(policyNumber)
                .productCode("GMR")
                .policyIssueDate(LocalDate.of(2023,01,01))
                .annualPremium(12000)
                .currency("MYR")
                .policyStatus("PP")
                .build();
    }
}
