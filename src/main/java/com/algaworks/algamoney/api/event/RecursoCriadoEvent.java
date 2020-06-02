package com.algaworks.algamoney.api.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

// Classe de evento
public class RecursoCriadoEvent extends ApplicationEvent {
	
	private static final long serialVersionUID = 1L;
	
	private HttpServletResponse response ;
	private Long codigo;
	
	//Regra para criação do envento - Necessário o response e o codigo
	public RecursoCriadoEvent(Object source, HttpServletResponse response, Long codigo) {
		super(source);
		this.response = response;
		this.codigo = codigo;
		
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public Long getCodigo() {
		return codigo;
	}

}
