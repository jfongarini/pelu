package com.figaro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClientesController {
	
	@RequestMapping("/clientes")
	public String cliente(Model model) {
		return "clientes";
	}

}