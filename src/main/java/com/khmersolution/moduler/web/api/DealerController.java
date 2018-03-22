package com.khmersolution.moduler.web.api;

import com.khmersolution.moduler.configure.Route;
import io.swagger.annotations.ApiParam;
import org.springframework.data.repository.query.Param;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * Created by DANG DIM
 * Date     : 3/21/2018, 1:31 PM
 * Email    : d.dim@gl-f.com
 */

@RestController
@RequestMapping(value = Route.API, produces = MediaType.APPLICATION_JSON_VALUE)
public class DealerController {

    RestTemplate template = new RestTemplate();
    private static final String url = "http://localhost:8080/webservice-ws/efinance/dealers/dealers";

    @RequestMapping(value = "/dealers", method = RequestMethod.GET)
    public String getProvinceByTemplate(@ApiParam(value = "last update date", required = false) @Param("lastUpdate") String lastUpdate) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(lastUpdate, headers);
        return template.exchange(url, HttpMethod.GET, entity, String.class).getBody();
    }
}
