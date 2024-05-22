package com.projet.demo.dto;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private String accessToken;
    private String tokenType = "Bearer ";
    private long idsalle; // Nouveau champ pour l'ID de la salle

    public AuthResponseDTO(String accessToken, long idsalle) {
        this.accessToken = accessToken;
        this.idsalle = idsalle;
    }

    public AuthResponseDTO() {
        super();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public long getIdsalle() {
        return idsalle;
    }

    public void setIdsalle(long idsalle) {
        this.idsalle = idsalle;
    }

    @Override
    public String toString() {
        return "AuthResponseDTO [accessToken=" + accessToken + ", tokenType=" + tokenType + ", idsalle=" + idsalle + "]";
    }
}
