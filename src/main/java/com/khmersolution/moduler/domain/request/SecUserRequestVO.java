package com.khmersolution.moduler.domain.request;

/*
Create By: Ron Rith
Create Date: 4/4/2018
*/
public class SecUserRequestVO {
    private String grantType;
    private String username;
    private String password;

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
