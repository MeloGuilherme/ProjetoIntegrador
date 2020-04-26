package com.projeto_int.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.projeto_int.model.Proposta;
import com.projeto_int.repository.PropostaRepository;

@Controller
public class ListaPropostaController {

	@Autowired
	private PropostaRepository propostaRepository;

	@RequestMapping(method = RequestMethod.GET, value = "/todas_propostas")
	public ModelAndView inicio() {

		ModelAndView modelAndView = new ModelAndView("cadastro/lista_propostas");
		modelAndView.addObject("propostaobj", new Proposta());

		Iterable<Proposta> propostaIt = propostaRepository.findAll();
		modelAndView.addObject("propostas", propostaIt);

		return modelAndView;
	}

	
	@RequestMapping(method = RequestMethod.GET, value = "/listatodaspropostas")
	public ModelAndView propostas() {
		
		ModelAndView andView = new ModelAndView("cadastro/lista_propostas");
		Iterable<Proposta> propostaIt = propostaRepository.findAll();
		
		andView.addObject("propostas", propostaIt);
		andView.addObject("propostaobj", new Proposta());
		
		return andView;
	}
	
	@PostMapping("**/pesquisarpropostas")
	public ModelAndView pesquisar(@RequestParam("titulopesquisa") String titulopesquisa){
		
		ModelAndView modelAndView = new ModelAndView("cadastro/lista_propostas");
		modelAndView.addObject("propostas", propostaRepository.findPropostaByTitulo(titulopesquisa));
		modelAndView.addObject("propostaobj", new Proposta());
		
		return modelAndView;
	}
}
