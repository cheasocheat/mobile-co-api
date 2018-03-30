package com.khmersolution.moduler.web.api;

import com.khmersolution.moduler.configure.Route;
import com.khmersolution.moduler.domain.request.QuotationApplicantVO;
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
@RequestMapping(value = Route.API, produces = MediaType.APPLICATION_JSON_VALUE)
public class QuotationApplicantController {
    RestTemplate restTemplate = new RestTemplate();

    @RequestMapping(value = "/quotationapplicant", method = RequestMethod.GET)
    public String getQuotationApplicantByTemplate(
            @RequestParam(value = "lastUpdate", required = false) String lastUpdate,
            @RequestParam(value = "quotationId",required = true) Long quotationId,
            @RequestParam(value = "product", required = true) String product
    ) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        QuotationApplicantVO quotationApplicantVO = new QuotationApplicantVO();
        quotationApplicantVO.setId(quotationId);
        quotationApplicantVO.setLastUpdateDate(lastUpdate);

        HttpEntity<QuotationApplicantVO> entity = new HttpEntity<QuotationApplicantVO>(quotationApplicantVO, headers);
        if (product != null && !product.equals("") && product.equals("HD")){
            return restTemplate.exchange(Route.HD_BASE_URL + "/quotationapplicant/list", HttpMethod.POST, entity, String.class).getBody();
        }else {
            return restTemplate.toString();
        }
    }
}
