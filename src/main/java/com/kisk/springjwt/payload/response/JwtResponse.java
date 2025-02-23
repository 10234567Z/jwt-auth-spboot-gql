package com.kisk.springjwt.payload.response;

import com.kisk.springjwt.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class JwtResponse {
  private String token;
  private String type = "Bearer";
  @Getter
  @Setter
  private User user;

  public JwtResponse(String accessToken, User user) {
    this.token = accessToken;
    this.user = user;
  }

  public String getAccessToken() {
    return token;
  }

  public void setAccessToken(String accessToken) {
    this.token = accessToken;
  }

  public String getTokenType() {
    return type;
  }

  public void setTokenType(String tokenType) {
    this.type = tokenType;
  }


}
