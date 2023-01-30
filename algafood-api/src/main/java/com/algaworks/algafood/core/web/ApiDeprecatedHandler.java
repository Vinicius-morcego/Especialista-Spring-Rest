package com.algaworks.algafood.core.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

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
