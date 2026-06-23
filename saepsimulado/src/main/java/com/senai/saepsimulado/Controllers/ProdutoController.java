package com.senai.saepsimulado.Controllers;

import com.senai.saepsimulado.Dtos.ProdutoDto;
import com.senai.saepsimulado.Services.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
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

    @DeleteMapping("/listaproduto/{id}")
    public ResponseEntity<String> deletarProduto(@PathVariable Long id){

        String resposta = service.deletarProduto(id);

        if (resposta.equals("ok")){
            return ResponseEntity.ok().body(resposta);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
    }
}
