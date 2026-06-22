package com.senai.saepsimulado.Controllers;

import com.senai.saepsimulado.Dtos.UsuarioDto;
import com.senai.saepsimulado.Services.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping("/login")
    public String viewLogin(Model model){

        UsuarioDto dto = new UsuarioDto();

        model.addAttribute("UsuarioDto", dto);

        return "/login";
    }

    @PostMapping("/login")
    public String logarUsuario(@ModelAttribute("UsuarioDto") UsuarioDto dto){

        boolean resposta = service.validarLogin(dto);

        if (resposta){
            return "/listaproduto";
        }
        return "redirect:/login?erro";
    }

    @GetMapping("/cadastrarusuario")
    public String viewCadastroUsuario(Model model){

        UsuarioDto dto = new UsuarioDto();

        model.addAttribute("UsuarioDto", dto);

        return "/cadastrarusuario";
    }

    @PostMapping("/cadastrarusuario")
    public String cadastrarUsuario(@ModelAttribute("UsuarioDto") UsuarioDto dto){

        boolean resposta = service.cadastrarUsuario(dto);

        if (resposta){
            return "/login";
        }
        return "redirect:/login?erro";
    }
}
