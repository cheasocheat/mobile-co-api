package com.khmersolution.moduler.web.api;

import com.khmersolution.moduler.configure.Route;
import com.khmersolution.moduler.domain.quotation.Profiles;
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

/**
 * Created by DANG DIM
 * Date     : 4/26/2018, 4:58 PM
 * Email    : d.dim@gl-f.com
 */

@RestController
@RequestMapping(value = Route.API, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class COProfileController {

    RestTemplate restTemplate = new RestTemplate();

    @RequestMapping(value = "/user-profiles", method = RequestMethod.GET)
    public String getApplicantByTemplate(
            @RequestParam(value = "lastUpdate", required = false) String lastUpdate,
            @RequestParam(value = "co_id",required = true) Long co_id,
            @RequestParam(value = "product", required = true) String product) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        Profiles profiles = new Profiles();
        profiles.setCoId(co_id);
        profiles.setUpdatedDate(lastUpdate);

        HttpEntity<Profiles> entity = new HttpEntity<Profiles>(profiles, headers);
        if (product != null && !product.equals("") && product.equalsIgnoreCase("HD")){
            return restTemplate.exchange(Route.HD_BASE_URL + "/profile/list_profiles", HttpMethod.POST, entity, String.class).getBody();
        }else {
            return restTemplate.toString();
        }
    }
}

