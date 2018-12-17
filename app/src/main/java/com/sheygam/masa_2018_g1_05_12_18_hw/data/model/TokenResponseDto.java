package com.sheygam.masa_2018_g1_05_12_18_hw.data.model;

public class TokenResponseDto {
    private String token;

    public TokenResponseDto() {
    }

    public TokenResponseDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "TokenResponseDto{" +
                "token='" + token + '\'' +
                '}';
    }
}
