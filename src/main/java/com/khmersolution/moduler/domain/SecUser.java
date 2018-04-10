package com.khmersolution.moduler.domain;

/*
Create By: Ron Rith
Create Date: 4/6/2018
*/
public class SecUser {
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private String tokenExpireDate;
    private Long userId;
    private String username;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getTokenExpireDate() {
        return tokenExpireDate;
    }

    public void setTokenExpireDate(String tokenExpireDate) {
        this.tokenExpireDate = tokenExpireDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public SecUser(String accessToken, String refreshToken, String tokenType, String tokenExpireDate, Long userId, String username) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.tokenType = tokenType;
        this.tokenExpireDate = tokenExpireDate;
        this.userId = userId;
        this.username = username;
    }

    public SecUser() {
    }
}
