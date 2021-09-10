package br.com.banco.service;

import br.com.banco.dto.request.PessoaDTO;
import br.com.banco.exception.NenhumaPessoaEncontradaException;
import br.com.banco.exception.PessoaNaoEncontradaException;
import br.com.banco.mapper.PessoaMapper;
import br.com.banco.model.Conta;
import br.com.banco.model.Pessoa;
import br.com.banco.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PessoaService {

    private PessoaRepository pessoaRepository;
    private final PessoaMapper pessoaMapper = PessoaMapper.INSTANCE;

    public Long criarPessoa(PessoaDTO pessoaDTO) {
        Pessoa pessoaToSave = pessoaMapper.toModel(pessoaDTO);
        List<Conta> contas = pessoaToSave.getContas();
        contas.forEach(conta -> conta.setPessoa(pessoaToSave));
        pessoaToSave.setContas(contas);
        Pessoa savedPessoa = pessoaRepository.save(pessoaToSave);
        return savedPessoa.getId();
    }



    public List<PessoaDTO> listarTodos() {
        List<Pessoa> todasAsPessoa = pessoaRepository.findAll();
        if(todasAsPessoa.isEmpty()){
            throw new NenhumaPessoaEncontradaException();
        }
        return todasAsPessoa.stream()
                .map(pessoaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PessoaDTO encontrarPorID(Long id)  {
        return pessoaMapper.toDTO(verificarSeExiste(id));
    }

    public Long deletar(Long id)  {
        pessoaRepository.deleteById(verificarSeExiste(id).getId());
        return id;
    }

    public Long atualizarPorId(Long id, PessoaDTO pessoaDTO) {
        verificarSeExiste(id);
        Pessoa pessoaAtualizar = pessoaMapper.toModel(pessoaDTO);
        Pessoa updatedPessoa = pessoaRepository.save(pessoaAtualizar);
        return updatedPessoa.getId();
    }


    private Pessoa verificarSeExiste(Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new PessoaNaoEncontradaException(id));
    }


}
