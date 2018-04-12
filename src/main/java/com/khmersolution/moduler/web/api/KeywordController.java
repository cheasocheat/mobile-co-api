package com.khmersolution.moduler.web.api;

import com.khmersolution.moduler.configure.Route;
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
import java.util.HashMap;
import java.util.Map;

/*
Create By: Ron Rith
Create Date: 3/28/2018
*/

@RestController
@RequestMapping(value = Route.API, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class KeywordController {
    RestTemplate restTemplate = new RestTemplate();

    @RequestMapping(value = "/keywords", method = RequestMethod.GET)
    public String getKeyWordByTemplate(@RequestParam(value = "lastUpdate", required = false) String lastUpdate,
                                        @RequestParam(value = "product", required = true) String product) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(lastUpdate, headers);
        if (product != null && !product.equals("") && product.equalsIgnoreCase("HD")){
            return restTemplate.exchange(Route.HD_BASE_URL + "/keyword/list_cokeywords", HttpMethod.POST, entity, String.class).getBody();
        }else {
            return restTemplate.toString();
        }
    }
}
