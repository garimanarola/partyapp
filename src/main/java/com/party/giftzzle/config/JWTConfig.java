package com.party.giftzzle.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

@Component
//@ConfigurationProperties(prefix = "security.authentication.jwt")
@ConfigurationProperties(prefix = "jwt")
@Validated
public class JWTConfig {
  private static Logger LOG = LoggerFactory.getLogger(JWTConfig.class);

  @NotNull
  private String secret;

  @NotNull
  private Long tokenValidityInSeconds;

  @NotNull
  private Long tokenValidityInSecondsForRememberMe;

  @PostConstruct
  public void logProperties() {
    LOG.info("com.party.giftzzel.config.JWTConfig: {}", this);
  }

  public String getSecret() {
    return secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }

  public long getTokenValidityInSeconds() {
    return tokenValidityInSeconds;
  }

  public void setTokenValidityInSeconds(long tokenValidityInSeconds) {
    this.tokenValidityInSeconds = tokenValidityInSeconds;
  }

  public long getTokenValidityInSecondsForRememberMe() {
    return tokenValidityInSecondsForRememberMe;
  }

  public void setTokenValidityInSecondsForRememberMe(long tokenValidityInSecondsForRememberMe) {
    this.tokenValidityInSecondsForRememberMe = tokenValidityInSecondsForRememberMe;
  }
}
