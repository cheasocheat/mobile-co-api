package com.khmersolution.moduler.service;

import com.khmersolution.moduler.domain.response.address.Province;
import com.khmersolution.moduler.web.api.AddressController;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by DANG DIM
 * Date     : 3/19/2018, 4:10 PM
 * Email    : d.dim@gl-f.com
 */

@Service
public class AddressService {

    private Logger logger = Logger.getLogger(String.valueOf(AddressController.class));
    private RestTemplate template = new RestTemplate();
    private
static  final String HONDA_API = "http://localhost:8080/webservice-ws/efinance/addresses/provinces";
    public List<Province> getListProvince() {
        List<Province> provinces = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Province> entity = new HttpEntity<>(headers);
        ResponseEntity<Province> response = template.exchange(HONDA_API, HttpMethod.GET, entity, Province.class);
        if (response.getStatusCode().equals(HttpStatus.OK)){
            logger.info("--------------------------------------------------->>>> success------------------->>> done()");
            provinces.add(response.getBody());
        }
        return provinces;
    }
}
