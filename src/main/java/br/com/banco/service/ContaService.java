package br.com.banco.service;

import br.com.banco.dto.request.ContaDTO;
import br.com.banco.dto.response.MessageResponseDTO;
import br.com.banco.exception.*;
import br.com.banco.mapper.ContaMapper;
import br.com.banco.mapper.PessoaMapper;
import br.com.banco.model.Conta;
import br.com.banco.model.Pessoa;
import br.com.banco.repository.ContaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ContaService {

    private ContaRepository contaRepository;
    private PessoaService pessoaService;

    private final ContaMapper contaMapper = ContaMapper.INSTANCE;
    private final PessoaMapper pessoaMapper = PessoaMapper.INSTANCE;

    public MessageResponseDTO saveConta(Long id, ContaDTO contaDTO) {
        Pessoa pessoa = pessoaMapper.toModel(pessoaService.encontrarPorID(id));
        checarContas(pessoa);
        checarTipoConta(pessoa, contaDTO);
        Conta contaParaSalvar = contaMapper.toModel(contaDTO);
        return createMessageResponse(contaRepository.save(contaParaSalvar).getId()," conta criada ID ");
    }

    public List<ContaDTO> findAll() {
        List<Conta> todasContas = contaRepository.findAll();
        if(todasContas.isEmpty()){
            throw new NenhumaContaEncontradaException();
        }

        return todasContas.stream()
                .map(contaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ContaDTO getById(Long id) {
        return contaMapper.toDTO(verificarSeExiste(id));
    }

    public MessageResponseDTO updateConta(Long id, ContaDTO contaDTO) {
        Conta contaParaSalvar = verificarSeExiste(id);
        contaParaSalvar.setId(id);
        return createMessageResponse(contaRepository.save(contaParaSalvar).getId()," conta atualizada com ID ");

    }

    public MessageResponseDTO deleteConta(Long id) {
        contaRepository.deleteById(verificarSeExiste(id).getId());
        return createMessageResponse(id," conta deletada com ID ");
    }

    public MessageResponseDTO transferencia(Long idOrigem, Long idDestino, BigDecimal valor){
        Conta origem = verificarSeExiste(idOrigem);
        validarTransferencia(origem, valor);
        origem.setSaldo(origem.getSaldo().subtract(valor));
        Conta destino = verificarSeExiste(idDestino);
        destino.setSaldo(destino.getSaldo().add(valor));
        origem.setId(idOrigem);
        destino.setId(idDestino);
        contaRepository.save(origem);
        contaRepository.save(destino);
        return createMessageResponse(destino.getId()," Valor transferido para conta com ID ");
    }

    public MessageResponseDTO sacar(Long idOrigem, BigDecimal valor){
        Conta origem = verificarSeExiste(idOrigem);
        validarTransferencia(origem, valor);
        origem.setSaldo(origem.getSaldo().subtract(valor));
        origem.setId(idOrigem);
        contaRepository.save(origem);
        return createMessageResponse(origem.getId()," Valor sacado da conta com ID ");
    }

    public MessageResponseDTO depositar(Long idOrigem, BigDecimal valor){
        Conta origem = verificarSeExiste(idOrigem);
        origem.setSaldo(origem.getSaldo().add(valor));
        origem.setId(idOrigem);
        contaRepository.save(origem);
        return createMessageResponse(origem.getId()," Valor sacado da conta com ID ");
    }

    private void validarTransferencia(Conta conta, BigDecimal valor){
        if(conta.getSaldo().compareTo(valor) <= 0){
            throw new SaldoNaoDisponivelException();
        }
    }

    private void checarContas(Pessoa pessoa) {
        if (pessoa.getContas().size() == 2) {
            throw new NumeroMaximoDeContasException();
        }
    }

    private void checarTipoConta(Pessoa pessoa, ContaDTO contaDTO) {
        for (Conta conta : pessoa.getContas()) {
            if (conta.getTipo().equals(contaDTO.getTipo())) {
                throw new ContaDoMesmoTipoException(contaDTO.getTipo().toString());
            }
        }
    }

    private Conta verificarSeExiste(Long id) throws ContaNaoEncontradaException {
        return contaRepository.findById(id)
                .orElseThrow(() -> new ContaNaoEncontradaException(id));
    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }
}
