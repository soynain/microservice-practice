package com.oauthserver.auth.PojoSets;

public class JwtTokenResponse {
    private String token;


    public JwtTokenResponse(String token) {
        this.token = token;
    }

    public String getToken(){
        return this.token;
    }
}
