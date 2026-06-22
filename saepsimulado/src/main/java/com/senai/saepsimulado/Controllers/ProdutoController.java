package com.senai.saepsimulado.Controllers;

import com.senai.saepsimulado.Dtos.ProdutoDto;
import com.senai.saepsimulado.Services.ProdutoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping("/listaproduto")
    public String viewLista(Model model){

        List<ProdutoDto> lista = service.listarProdutos();

        model.addAttribute("listaproduto", lista);

        return "/listaproduto";
    }

    @GetMapping("/cadastroproduto")
    public String viewCadastrarProduto(Model model){

        ProdutoDto dto = new ProdutoDto();

        model.addAttribute("ProdutoDto", dto);
        return "/cadastroproduto";
    }

    @PostMapping("/cadastroproduto")
    public String cadastrarProduto(@ModelAttribute("ProdutoDto")ProdutoDto dto){

        boolean resposta = service.cadastrarProduto(dto);

        if (resposta){
            return "redirect:/listaproduto";
        }
        return "redirect:/listaproduto?erro";
    }

    @GetMapping("/alterarproduto/{id}")
    public String viewAlterarProduto(Model model, @PathVariable Long id){

        ProdutoDto dto = service.retornarProduto(id);

        if (dto.getId()!=null){
            model.addAttribute("ProdutoDto", dto);
            return "/alterarproduto";
        }
        return "redirect:/listaproduto?erro";
    }

    @PostMapping("/alterarproduto/{id}")
    public String alterarProduto(@ModelAttribute("ProdutoDto") ProdutoDto dto, @PathVariable Long id){

        service.alterarProduto(dto);

        return "redirect:/listaproduto";
    }
}
