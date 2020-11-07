package br.com.tokiomarine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "churros")
public class BemVindoController {

	@GetMapping
	public ModelAndView wellcome() {
		ModelAndView retornoBemVindo = new ModelAndView();
		retornoBemVindo.addObject("mensagem", "Bem Vindo!");
		return retornoBemVindo;
	}
}
