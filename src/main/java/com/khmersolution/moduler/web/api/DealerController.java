package com.khmersolution.moduler.web.api;

import com.khmersolution.moduler.configure.Route;
import io.swagger.annotations.ApiParam;
import org.springframework.data.repository.query.Param;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by DANG DIM
 * Date     : 3/21/2018, 1:31 PM
 * Email    : d.dim@gl-f.com
 */

@RestController
@RequestMapping(value = Route.API, produces = MediaType.APPLICATION_JSON_VALUE)
public class DealerController {

    RestTemplate template = new RestTemplate();

    @RequestMapping(value = "/dealers", method = RequestMethod.GET)
    public String getProvinceByTemplate(@RequestParam(value = "lastUpdate", required = false) String lastUpdate,
                                        @RequestParam(value = "product", required = true) String product) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(lastUpdate, headers);
        if (product != null && !product.equals("") && product.equalsIgnoreCase("HD")){
            return template.exchange(Route.HD_BASE_URL + "/dealers/dealer_list", HttpMethod.POST, entity, String.class).getBody();
        }else {
            return template.toString();
        }

    }
}
