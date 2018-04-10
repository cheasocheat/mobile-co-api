package com.khmersolution.moduler.web.api;

import com.khmersolution.moduler.configure.Route;
import com.khmersolution.moduler.domain.request.ApplicantVO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/*
Create By: Ron Rith
Create Date: 3/29/2018
*/
@RestController
@RequestMapping(value = Route.API, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApplicantController {
    RestTemplate restTemplate = new RestTemplate();

    @RequestMapping(value = "/applicant", method = RequestMethod.GET)
    public String getApplicantByTemplate(
            @RequestParam(value = "lastUpdate", required = false) String lastUpdate,
            @RequestParam(value = "quotationId",required = true) Long quotationId,
            @RequestParam(value = "product", required = true) String product) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        ApplicantVO applicant = new ApplicantVO();
        applicant.setId(quotationId);
        applicant.setLastUpdateDate(lastUpdate);

        HttpEntity<ApplicantVO> entity = new HttpEntity<ApplicantVO>(applicant, headers);
        if (product != null && !product.equals("") && product.equalsIgnoreCase("HD")){
            return restTemplate.exchange(Route.HD_BASE_URL + "/applicant/list", HttpMethod.POST, entity, String.class).getBody();
        }else {
            return restTemplate.toString();
        }
    }
}
