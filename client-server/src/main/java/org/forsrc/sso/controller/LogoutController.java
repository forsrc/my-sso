package org.forsrc.sso.controller;

import java.security.Principal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {

	@Value("${my.logout-url}")
	private String logoutUrl;

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response,
			@RegisteredOAuth2AuthorizedClient("oauth2-client-authorization-code") OAuth2AuthorizedClient authorizedClient,
			Principal principal) throws ServletException {
		System.out.println("--> logout: " + principal);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		authentication.setAuthenticated(false);
		new SecurityContextLogoutHandler().logout(request, response, authentication);
		SecurityContextHolder.clearContext();
		request.logout();
		request.getSession().invalidate();
		return "redirect:" + logoutUrl;
	}

}