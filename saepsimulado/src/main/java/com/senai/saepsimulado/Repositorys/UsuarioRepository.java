package com.senai.saepsimulado.Repositorys;

import com.senai.saepsimulado.Models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    Optional<UsuarioModel> findByEmailAndSenha(String email, String senha);
}
