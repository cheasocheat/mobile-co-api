package com.khmersolution.moduler.web.api;

import com.khmersolution.moduler.configure.Route;
import com.khmersolution.moduler.domain.request.ProfileRequestVO;
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
public class COProfileController {
    RestTemplate restTemplate = new RestTemplate();

    @RequestMapping(value = "/user-profiles", method = RequestMethod.GET)
    public String getProfileByTemplate(
            @RequestParam(value = "token", required = false) String token,
            @RequestParam(value = "coId", required = true) Long coId,
            @RequestParam(value = "updatedDate",required = false) String updatedDate
    ) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        ProfileRequestVO profileRequestVO = new ProfileRequestVO();
        profileRequestVO.setCoId(coId);
        if (updatedDate != null) {
            profileRequestVO.setUpdatedDate(updatedDate);
        }
        if (token != null) {
            profileRequestVO.setToken(token);
        }


        HttpEntity<ProfileRequestVO> entity = new HttpEntity<ProfileRequestVO>(profileRequestVO, headers);

        return restTemplate.exchange(Route.HD_BASE_URL + "/profile/list_profiles", HttpMethod.POST, entity, String.class).getBody();

    }
}
