package org.forsrc.sso.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

	@GetMapping("/api/test")
	public String[] test(Principal principal) {
		return new String[] { "test", principal == null ? "no login" : principal.toString() };
	}

	@GetMapping("/api/me")
	public String principal(Principal principal) {
		return principal == null ? "" : principal.toString() ;
	}
}