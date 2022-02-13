package org.forsrc.sso.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;

@RestController
public class TestController {

    @Autowired
    private WebClient webClient;
    
    @Value("${my.resource-server}")
    private String resourceServer;

    @GetMapping(value = "/test/api")
    public String[] testApi(
      @RegisteredOAuth2AuthorizedClient("oauth2-client-authorization-code") OAuth2AuthorizedClient authorizedClient
    ) {
        return this.webClient
          .get()
          .uri(resourceServer + "/api/test")
          .attributes(ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient(authorizedClient))
          .retrieve()
          .bodyToMono(String[].class)
          .block();
    }
    
    @GetMapping(value = "/test/me")
    public Principal testMe(
      @RegisteredOAuth2AuthorizedClient("oauth2-client-authorization-code") OAuth2AuthorizedClient authorizedClient, Principal principal
    ) {
        return principal;
    }
}