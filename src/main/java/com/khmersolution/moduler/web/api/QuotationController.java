package com.khmersolution.moduler.web.api;

import com.khmersolution.moduler.configure.Route;
import com.khmersolution.moduler.domain.quotation.Profiles;
import com.khmersolution.moduler.domain.quotation.SubmitQuotation;
import com.khmersolution.moduler.domain.response.document.QDocument;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/*
Create By: Ron Rith
Create Date: 3/29/2018
*/

@RestController
@RequestMapping(value = Route.API, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class QuotationController {
    RestTemplate restTemplate = new RestTemplate();

    @RequestMapping(value = "/quotations", method = RequestMethod.GET)
    public String getQuotationByTemplate(@RequestParam(value = "lastUpdate", required = false) String lastUpdate,
                                         @RequestParam(value = "co_id", required = true) Long co_id,
                                         @RequestParam(value = "product", required = true) String product) {

        Profiles profiles = new Profiles();
        profiles.setCoId(co_id);
        profiles.setUpdatedDate(lastUpdate);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Profiles> request = new HttpEntity<>(profiles, headers);

        if (product != null && !product.equals("") && product.equalsIgnoreCase("HD")) {
            return restTemplate.exchange(Route.HD_BASE_URL + "/quotation/list_quotations", HttpMethod.POST, request, String.class).getBody();
        } else {
            return restTemplate.toString();
        }
    }

    @RequestMapping(value = "/quotations", method = RequestMethod.POST)
    public String submitQuotation(@RequestBody SubmitQuotation quotation) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<SubmitQuotation> request = new HttpEntity<>(quotation, headers);

        if (quotation.getProduct() != null && !quotation.getProduct().equals("") && quotation.getProduct().equalsIgnoreCase("HD")) {
            return restTemplate.exchange(Route.HD_BASE_URL + "/quotation/submition", HttpMethod.POST, request, String.class).getBody();
        } else {
            return restTemplate.toString();
        }
    }

    @RequestMapping(value = "/quotations", method = RequestMethod.PUT)
    public String updateQuotation(@RequestBody SubmitQuotation quotation) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<SubmitQuotation> request = new HttpEntity<>(quotation, headers);
        String noProduct = "No product type found";

        if (quotation.getProduct() != null && !quotation.getProduct().equals("") && quotation.getProduct().equalsIgnoreCase("HD")) {
            return restTemplate.exchange(Route.HD_BASE_URL + "/quotation/submition", HttpMethod.PUT, request, String.class).getBody();
        } else {
            return noProduct;
        }
    }
}
