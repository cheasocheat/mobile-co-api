package com.khmersolution.moduler.util;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;

/**
 * Created by : Ron Rith
 * Create Date: 03/18/2018.
 */
public class HeaderInfo {
    public static final String AUTH_SERVER_URI = "http://localhost:9999/oauth/token";

    public static final String QPM_PASSWORD_GRANT_USERNAME = "?grant_type=password&username=";

    public static final String QPM_PASSWORD_GRANT_PASSWORD = "&password=";

    public static final String QPM_ACCESS_TOKEN = "?access_token=";

    /*
     * Prepare HTTP Headers.
     */
    public static HttpHeaders getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        //headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }
    /*
     * Add HTTP Authorization header, using Basic-Authentication to send client-credentials.
     */
    public static HttpHeaders getHeadersWithClientCredentials(String clientID, String clientSecret){
        String plainClientCredentials = clientID + ":" + clientSecret;
        String base64ClientCredentials = new String(org.apache.commons.codec.binary.Base64.encodeBase64(plainClientCredentials.getBytes()));

        HttpHeaders headers = getHeaders();
        headers.add("Authorization", "Basic " + base64ClientCredentials);
        return headers;
    }
    /*
     * Send a POST request [on /oauth/token] to get an access-token, which will then be send with each request.
     */
    @SuppressWarnings({ "unchecked"})
    public static AuthTokenInfo sendTokenRequest(String clientId, String clientSecret, String userName, String password){
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> request = new HttpEntity<String>(getHeadersWithClientCredentials(clientId,clientSecret));
        AuthTokenInfo tokenInfo = null;

        try {
            ResponseEntity<Object> response = restTemplate.exchange(AUTH_SERVER_URI +
                            QPM_PASSWORD_GRANT_USERNAME + userName + QPM_PASSWORD_GRANT_PASSWORD + password
                    , HttpMethod.POST, request, Object.class);
            LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) response.getBody();

            if (map != null) {
                tokenInfo = new AuthTokenInfo();
                tokenInfo.setAccess_token((String) map.get("access_token"));
                tokenInfo.setToken_type((String) map.get("token_type"));
                tokenInfo.setRefresh_token((String) map.get("refresh_token"));
                tokenInfo.setExpires_in((Integer) map.get("expires_in"));
                tokenInfo.setScope((String) map.get("scope"));

                System.out.println("===============================");
                System.out.println(tokenInfo);
                System.out.println("===============================");

                //System.out.println("access_token ="+map.get("access_token")+", token_type="+map.get("token_type")+", refresh_token="+map.get("refresh_token")
                //+", expires_in="+map.get("expires_in")+", scope="+map.get("scope"));;
            } else {
                System.out.println("No user exist----------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tokenInfo;
    }


    public static void main(String args[]){
        //AuthTokenInfo tokenInfo = sendTokenRequest("trusted-app", "secret", "adminz", "123123");
        AuthTokenInfo tokenInfo = sendTokenRequest("trusted-app", "secret", "akra_sonisak", "11");
    }
}
