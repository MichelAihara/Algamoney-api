package com.algaworks.algamoney.api.resource;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Classe para realizar logout, removendo o Refresh Token do Cookie

@RestController
@RequestMapping("/tokens")
public class TokenResource {
	
	@DeleteMapping("/revoke")
	private void revoke(HttpServletRequest req, HttpServletResponse resp) {
		Cookie cookie = new Cookie("refreshToken", null);
		cookie.setHttpOnly(true);
		cookie.setSecure(false); //TODO: Em produtção será true
		cookie.setPath(req.getContextPath() + "/oauth/token");
		cookie.setMaxAge(0);
		
		//Pega a requisição e add o cookie vazio
		resp.addCookie(cookie);
		resp.setStatus(HttpStatus.NO_CONTENT.value());

	}

}
