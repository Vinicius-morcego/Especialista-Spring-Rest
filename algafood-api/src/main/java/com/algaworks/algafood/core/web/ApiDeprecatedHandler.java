package com.algaworks.algafood.core.web;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ApiDeprecatedHandler implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(request.getRequestURI().startsWith("/v1/")) {
			var sb = new StringBuilder();
			sb.append("Esta versão da API esta depreciada, sem suporte a partir de 01/02/2023. ");
			sb.append("Use a versão mais atual da API.");
			response.addHeader("X-Algafood-Deprecated", sb.toString());
		}
		return true;
	}
}
