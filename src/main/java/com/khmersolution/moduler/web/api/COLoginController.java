package com.khmersolution.moduler.web.api;

import com.khmersolution.moduler.configure.Route;
import com.khmersolution.moduler.domain.Role;
import com.khmersolution.moduler.domain.SecUser;
import com.khmersolution.moduler.domain.User;
import com.khmersolution.moduler.domain.request.SecUserRequestVO;
import com.khmersolution.moduler.domain.response.RespondToken;
import com.khmersolution.moduler.repository.RoleRepository;
import com.khmersolution.moduler.repository.UserRepository;
import com.khmersolution.moduler.service.UserService;
import com.khmersolution.moduler.util.AuthTokenInfo;
import com.khmersolution.moduler.util.HeaderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

/*
Create By: Ron Rith
Create Date: 4/5/2018
*/
@RestController
@RequestMapping(value = Route.API, produces = MediaType.APPLICATION_JSON_VALUE)
public class COLoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    RestTemplate restTemplate = new RestTemplate();

    @RequestMapping(value = "/oauth/token", method = RequestMethod.GET)
    public String todoLogin(
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

        User user = new User();
        user.setUsername(secUserRequestVO.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(secUserRequestVO.getPassword()));
        user.setEmail(secUserRequestVO.getUsername().toString()+"@gmail.com");
        user.setFirstName(secUserRequestVO.getUsername());
        user.setLastName(secUserRequestVO.getUsername());


        save(user);

        HeaderInfo.sendTokenRequest("trusted-app", "secret", secUserRequestVO.getUsername(), secUserRequestVO.getPassword());

        HttpEntity<SecUserRequestVO> entity = new HttpEntity<SecUserRequestVO>(secUserRequestVO, headers);
        //ResponseEntity<List> response = restTemplate.exchange(URL_REST_NEWS, HttpMethod.GET, request, List.class);

        return restTemplate.exchange(Route.HD_BASE_URL + "/login/login", HttpMethod.POST, entity, String.class).getBody();
    }

    @RequestMapping(value = "/oauth/token2", method = RequestMethod.GET)
    public ResponseEntity<?> todoLoginProxy(
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

        User user = new User();
        user.setUsername(secUserRequestVO.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(secUserRequestVO.getPassword()));
        user.setEmail(secUserRequestVO.getUsername().toString()+"@gmail.com");
        user.setFirstName(secUserRequestVO.getUsername());
        user.setLastName(secUserRequestVO.getUsername());
        user.setEnabled(Boolean.TRUE);

        save(user);

        AuthTokenInfo tokenInfo = HeaderInfo.sendTokenRequest("trusted-app", "secret", secUserRequestVO.getUsername(), secUserRequestVO.getPassword());

        HttpEntity<SecUserRequestVO> entity = new HttpEntity<SecUserRequestVO>(secUserRequestVO, headers);
        ResponseEntity<Object> response = restTemplate.exchange(Route.HD_BASE_URL + "/login/login", HttpMethod.POST, entity, Object.class);
        LinkedHashMap<Object,Object> usersMap = (LinkedHashMap<Object,Object>) response.getBody();

        Integer userID = null;
        RespondToken respondToken = new RespondToken();

        if (usersMap != null) {
            List<LinkedHashMap<String,Object>> mapList = (List<LinkedHashMap<String,Object>>) usersMap.get("RSLT_DATA");
            System.out.println("*************************");
            if (mapList.get(0).get("userId") != null) {
                userID = Integer.valueOf(mapList.get(0).get("userId")+"");
            }
            respondToken.setRSLT_MSG(usersMap.get("RSLT_MSG").toString());
            respondToken.setRSLT_CD(usersMap.get("RSLT_CD").toString());

            System.out.println("*************************");
        } else {
            System.out.println("No user exist----------");
        }

        SecUser secUserRespond = new SecUser();

        if (tokenInfo != null) {
            secUserRespond.setAccessToken(tokenInfo.getAccess_token() != null ? tokenInfo.getAccess_token() : "");
            secUserRespond.setTokenExpireDate((tokenInfo.getExpires_in() != null ? tokenInfo.getExpires_in() : 0L) + "");
            secUserRespond.setRefreshToken(tokenInfo.getRefresh_token() != null ? tokenInfo.getRefresh_token() : "" + "");
            secUserRespond.setTokenType(tokenInfo.getToken_type() != null ? tokenInfo.getToken_type() : 0L + "");
            secUserRespond.setUserId(Long.valueOf(userID.toString()));
            secUserRespond.setUsername(username);
        }
        respondToken.setRSLT_DATA(secUserRespond);
        return new ResponseEntity<Object>(respondToken, HttpStatus.OK);

    }

    private void save(User user){
        if (user != null) {
            User userSource = userRepository.findByUsername(user.getUsername());
            if (userSource == null) {
                Role role = roleRepository.findByName("USER");

                List<Role> roles = new ArrayList<>();
                roles.add(role);

                user.setRoles(roles);
                userService.save(user);
            }
        }
    }
}
