package org.forsrc.sso.controller;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
public class LogoutController {

	@RequestMapping(path = "/oauth/logout")
	// @PreAuthorize("isAuthenticated()")
	public ResponseEntity<Void> oauthLogout(HttpServletRequest request, HttpServletResponse response, Principal principal,
			String referer, @RequestParam(value = "gateway_referer", required = false) String gatewayReferer) {
		String user = principal == null ? "NO USER to logout" : principal.getName();
		new SecurityContextLogoutHandler().logout(request, null, null);
		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

		if (gatewayReferer != null) {
			String gateway = request.getHeader(gatewayReferer);
			if (gateway != null) {
				String ref = UriComponentsBuilder.fromUriString(gateway).build().toString();
				try {
					redirectStrategy.sendRedirect(request, response, ref);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return ResponseEntity.ok().header("logout_sso_user", user).build();
			}
		}

		try {
			redirectStrategy.sendRedirect(request, response, referer != null ? referer : request.getHeader("referer"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("-> logout: " + principal);
		return ResponseEntity.ok().header("logout_sso_user", user).build();
	}
	
	
	@RequestMapping(path = "/logout")
	// @PreAuthorize("isAuthenticated()")
	public String logout(HttpServletRequest request, HttpServletResponse response, Principal principal,
			String referer, @RequestParam(value = "gateway_referer", required = false) String gatewayReferer) {
		String user = principal == null ? "NO USER to logout" : principal.getName();
		System.out.println("-> logout: " + user);

		new SecurityContextLogoutHandler().logout(request, null, null);
		SecurityContextHolder.clearContext();

		return "redirect:login?logout";
	}


}
