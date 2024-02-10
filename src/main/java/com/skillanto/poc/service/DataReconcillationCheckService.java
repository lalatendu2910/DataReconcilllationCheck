package com.skillanto.poc.service;

import com.skillanto.poc.model.PolicyDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Objects;

import static com.skillanto.poc.constants.APIConstants.POLICY_NUMBER_SOURCE_URL;
import static com.skillanto.poc.constants.APIConstants.POLICY_NUMBER_TARGET_URL;

@Service
@Slf4j
public class DataReconcillationCheckService {

   // @Autowired
   // private DataReconcilationServiceMock mockService;

    @Autowired
    private RestTemplate restTemplate;

    private static String base_url = "http://localhost:9090/idmDataService";
    //uses mock service to get response
    /*public void readReconciledDataForPolicyNumber(String policyNumber) {

        PolicyDetails sourceData =  mockService.getPolicyNumberReconcilledDataFromSource(policyNumber);
        log.info("policyDetails retrieved from sourceAPI {}",sourceData);
        PolicyDetails targetData =  mockService.getPolicyNumberReconcilledDataFromTarget(policyNumber);
        log.info("policyDetails retrieved from targetAPI {}",targetData);
        HashMap<String,String> changedFields = null;
       try {
           changedFields = verifyDataReconcillation(sourceData, targetData);
       } catch(IllegalAccessException ex) {
           ex.printStackTrace();
       }
        log.info("changedProperties calculated {}",changedFields);

    }*/
    //uses rest template to get response
    public void readReconciledDataForPolicyNumber(String policyNumber) {

        PolicyDetails sourceResponse = restTemplate.postForObject(POLICY_NUMBER_SOURCE_URL, policyNumber, PolicyDetails.class);
        log.info("sourceResponse received from api call {}", sourceResponse);

        PolicyDetails targetResponse = restTemplate.postForObject(POLICY_NUMBER_TARGET_URL, policyNumber, PolicyDetails.class);
        log.info("sourceResponse received from api call {}", sourceResponse);

        HashMap<String,String> changedFields = null;
        try {
            changedFields = verifyDataReconcillation(sourceResponse, targetResponse);
        } catch(IllegalAccessException ex) {
            ex.printStackTrace();
        }
        log.info("changedProperties calculated {}",changedFields);
    }

    private HashMap<String,String> verifyDataReconcillation(Object sourceData, Object targetData) throws IllegalAccessException {
        HashMap<String, String> changedProperties = new HashMap<>();
        for (Field field : sourceData.getClass().getDeclaredFields()) {
            // You might want to set modifier to public first (if it is not public yet)
            field.setAccessible(true);
            Object value1 = field.get(sourceData);
            Object value2 = field.get(targetData);
            if (value1 != null && value2 != null) {
                if (!Objects.equals(value1, value2)) {
                    changedProperties.put(field.getName(),"Failed");
                } else {
                    changedProperties.put(field.getName(),"Passed");
                }
            }
        }
        return changedProperties;
    }
}
