package com.khmersolution.moduler.web.api;

import com.khmersolution.moduler.configure.Route;
import com.khmersolution.moduler.domain.request.SecUserRequestVO;
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
Create Date: 4/5/2018
*/
@RestController
@RequestMapping(value = Route.API, produces = MediaType.APPLICATION_JSON_VALUE)
public class COLoginController {
    RestTemplate restTemplate = new RestTemplate();

    @RequestMapping(value = "/oauth/token", method = RequestMethod.GET)
    public String getQuotationApplicantByTemplate(
            @RequestParam(value = "grantType", required = true) String grantType,
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password",required = true) String password
    ) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        SecUserRequestVO secUserRequestVO = new SecUserRequestVO();
        secUserRequestVO.setGrantType(grantType);
        secUserRequestVO.setUsername(username);
        secUserRequestVO.setPassword(password);

        HttpEntity<SecUserRequestVO> entity = new HttpEntity<SecUserRequestVO>(secUserRequestVO, headers);

        return restTemplate.exchange(Route.HD_BASE_URL + "/login/login", HttpMethod.POST, entity, String.class).getBody();
    }
}
