package com.senai.saepsimulado.Services;

import com.senai.saepsimulado.Dtos.ProdutoDto;
import com.senai.saepsimulado.Dtos.UsuarioDto;
import com.senai.saepsimulado.Models.ProdutoModel;
import com.senai.saepsimulado.Repositorys.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public List<ProdutoDto> listarProdutos(){

        List<ProdutoModel> listaOp = repository.findAll();

        List<ProdutoDto> lista = new ArrayList<>();

        for (int i = 0; i < listaOp.size(); i++) {

            ProdutoDto dto = new ProdutoDto();
            dto.setId(listaOp.get(i).getId());
            dto.setEstoque(listaOp.get(i).getEstoque());
            dto.setNome(listaOp.get(i).getNome());

            lista.add(dto);
        }
        return lista;
    }

    public boolean cadastrarProduto(ProdutoDto dto){

        if (dto.getEstoque()>=0){

            ProdutoModel model = new ProdutoModel();
            model.setEstoque(dto.getEstoque());
            model.setNome(dto.getNome());

            repository.save(model);
            return true;
        }
        return false;
    }

    public ProdutoDto retornarProduto(Long id){

        Optional<ProdutoModel> op = repository.findById(id);
        ProdutoDto dto = new ProdutoDto();
        if (op.isPresent()){

            dto.setNome(op.get().getNome());
            dto.setId(id);
            dto.setEstoque(op.get().getEstoque());

            return dto;
        }
        return dto;
    }

    public void alterarProduto(ProdutoDto dto){

        Optional<ProdutoModel> op = repository.findById(dto.getId());

        ProdutoModel model = new ProdutoModel();

        model.setId(dto.getId());
        model.setNome(dto.getNome());
        model.setEstoque(op.get().getEstoque());
        repository.save(model);
    }
}
