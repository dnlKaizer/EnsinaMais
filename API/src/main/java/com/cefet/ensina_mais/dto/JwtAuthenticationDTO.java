package com.cefet.ensina_mais.dto;

public class JwtAuthenticationDTO {
    
    private String acessToken;
    private String tokenType = "Bearer";

    public JwtAuthenticationDTO(String acessToken) {
        this.acessToken = acessToken;
    }

    public JwtAuthenticationDTO() {
    }

    public String getAcessToken() {
        return acessToken;
    }
    
    public String getTokenType() {
        return tokenType;
    }    

}
