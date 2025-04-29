package com.info.FuncionarioConsumer.controller;

import com.info.FuncionarioConsumer.model.Funcionario;
import com.info.FuncionarioConsumer.service.FuncionarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/funcionario")
public class FuncionarioController {
    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping("")
    public String listarFuncionario(Model model, HttpSession session) {
        session.removeAttribute("idFuncionario");
        model.addAttribute("listafuncionario", funcionarioService.pesquisar());
        return "list-funcionario";
    }

    @GetMapping("/novo")
    public String novoFuncionario(Model model){
        model.addAttribute("funcionario", new Funcionario());
        return "form-funcionario";
    }

    @GetMapping("/editar/{id}")
    public String editaFuncionario(@PathVariable int id, Model model, HttpSession session){
        session.setAttribute("idFuncionario", id);
        model.addAttribute("funcionario", funcionarioService.localizar(id));
        return "form-funcionario";
    }

    @PostMapping("/salvar")
    public String salvaFuncionario(@ModelAttribute("funcionario") Funcionario funcionario, HttpSession session){
        //funcionario.setId(session.getAttribute("idFuncionario")==null?0:(int)session.getAttribute("idFuncionario"));
        funcionarioService.salvar(funcionario);
        return "redirect:/funcionario";
    }

    @GetMapping("/excluir/{id}")
    public String excluirFuncionario(@PathVariable int id){
        funcionarioService.excluir(id);
        return "redirect:/funcionario";
    }
}
