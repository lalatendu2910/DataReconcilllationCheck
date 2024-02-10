package com.skillanto.poc.controller;

import com.skillanto.poc.service.DataReconcillationCheckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/staging")
public class DataReconcillationCheckController {
    @Autowired
    private DataReconcillationCheckService dataReconcillationCheckService;
    private static final Map<String, List<String>> STAGING_SCHEMA_FIELD_VALUES =
            Map.of("policyNumber", List.of("00005432", "00005433"),
                    "agentNumber", List.of("C0123456", "C78643"));


    @GetMapping("/fieldNames/{fieldName}")
    //public ResponseEntity<?> verifyDataReconcilationUsingQueryParam(@RequestParam("fieldName") String fieldName) {
    public ResponseEntity<?> verifyDataReconcilation(@PathVariable("fieldName") String fieldName) {

        if ("policyNumber".equalsIgnoreCase(fieldName)) {
            List<String> policyNumberValues = STAGING_SCHEMA_FIELD_VALUES.get(fieldName);
            log.info("policyNumberValues retrieved {}", policyNumberValues);
            for (String policyNumber : policyNumberValues) {
                dataReconcillationCheckService.readReconciledDataForPolicyNumber(policyNumber);
            }

        }
        return null;
    }
}
