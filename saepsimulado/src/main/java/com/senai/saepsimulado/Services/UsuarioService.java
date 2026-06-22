package com.senai.saepsimulado.Services;

import com.senai.saepsimulado.Dtos.UsuarioDto;
import com.senai.saepsimulado.Models.UsuarioModel;
import com.senai.saepsimulado.Repositorys.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public boolean validarLogin(UsuarioDto dto){

        Optional<UsuarioModel> usuarioOP = repository.findByEmailAndSenha(dto.getEmail(), dto.getSenha());

        if (usuarioOP.isPresent()){

            return true;
        }
        return false;
    }

    public boolean cadastrarUsuario(UsuarioDto dto){

        Optional<UsuarioModel> usuarioOp = repository.findByEmailAndSenha(dto.getEmail(), dto.getSenha());

        if (usuarioOp.isPresent()){

            return false;
        }
        UsuarioModel model = new UsuarioModel();

        model.setCargo("ALMOXARIFADO");
        model.setEmail(dto.getEmail());
        model.setSenha(dto.getSenha());
        model.setNome(dto.getNome());

        repository.save(model);

        return true;
    }
}
