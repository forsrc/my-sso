package org.forsrc.sso.controller;

import java.security.Principal;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
 
    @GetMapping(value = "/")
    public String home(
      @RegisteredOAuth2AuthorizedClient("oauth2-client-authorization-code") OAuth2AuthorizedClient authorizedClient,
      Principal principal
    ) {
        System.out.println("--> principal: " + principal);
        return "index";
    }
    
}