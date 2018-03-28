package com.khmersolution.moduler.web.api;

import com.khmersolution.moduler.configure.Route;
import com.khmersolution.moduler.domain.response.document.QDocument;
import io.swagger.annotations.ApiParam;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.QueryParam;
import java.util.*;


/**
 * Created by DANG DIM
 * Date     : 3/24/2018, 11:52 AM
 * Email    : d.dim@gl-f.com
 */

@RestController
@RequestMapping(value = Route.API, produces = MediaType.APPLICATION_JSON_VALUE)
public class DocumentController {

    RestTemplate restTemplate = new RestTemplate();

    @RequestMapping(value = "/documents", method = RequestMethod.GET)
    public String getDocumentGroupByTemplate(@RequestParam(value = "lastUpdate", required = false) String lastUpdate,
                                             @RequestParam(value = "product", required = true) String product) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(lastUpdate, headers);
        if (product != null && !product.equals("") && product.equals("HD")) {
            return restTemplate.exchange(Route.HD_BASE_URL + "/documents/list_document", HttpMethod.POST, entity, String.class).getBody();
        } else {
            return restTemplate.toString();
        }

    }

    @RequestMapping(value = "/documentgroups", method = RequestMethod.GET)
    public String getDocumentByTemplate(@RequestParam(value = "lastUpdate", required = false) String lastUpdate,
                                        @RequestParam(value = "product", required = true) String product) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(lastUpdate, headers);
        if (product != null && !product.equals("") && product.equals("HD")) {
            return restTemplate.exchange(Route.HD_BASE_URL + "/documents/list_group", HttpMethod.POST, entity, String.class).getBody();
        } else {
            return restTemplate.toString();
        }
    }


    @RequestMapping(value = "/quotation-documents", method = RequestMethod.GET)
    public String getQuotationDocumentByTemplate(
            @RequestParam(value = "lastUpdate", required = false) String lastUpdate,
            @RequestParam(value = "quota_id", required = true) Long quota_id,
            @RequestParam(value = "product", required = true) String product) {

        QDocument qdocument = new QDocument();
        qdocument.setQuotationId(quota_id);
        qdocument.setDate(lastUpdate);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<QDocument> request = new HttpEntity<>(qdocument, headers);

        if (product != null && !product.equals("") && product.equals("HD")) {
            return restTemplate.exchange(Route.HD_BASE_URL + "/quotation-documents/list", HttpMethod.POST, request, String.class).getBody();
        } else {
            return restTemplate.toString();
        }
    }


 /*   public String getToken() {

        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("client_id", clientId);
        map.add("client_secret", secret);
        map.add("code", code);
        map.add("grant_type", "authorization_code");

        HttpEntity<MultiValueMap<String, String>> requestEntity= new HttpEntity<MultiValueMap<String, String>>(map, headers);
        String token = "";
        try{
            BimResponse response = template.postForObject(url, requestEntity,  BimResponse.class);
            token = response.getAccessToken() + " - " + response.getTokenType();
        }
        catch(Exception e){
            token = e.getMessage();
        }
        return token;
    }*/

}
