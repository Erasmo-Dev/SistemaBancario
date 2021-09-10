package br.com.banco.controller;

import br.com.banco.dto.request.ContaDTO;
import br.com.banco.dto.response.MessageResponseDTO;
import br.com.banco.service.ContaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/conta")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ContaController {

    private ContaService contaService;

    @PostMapping("/criar/{idPessoa}")
    public ResponseEntity<MessageResponseDTO> createconta(@PathVariable Long idPessoa,@RequestBody @Valid ContaDTO conta) {
        return ResponseEntity.ok().body(contaService.saveConta(idPessoa,conta));
    }

    @PutMapping("/atualizar/{idConta}")
    public ResponseEntity<MessageResponseDTO> updateConta(@PathVariable Long idConta, @RequestBody  @Valid ContaDTO conta) {
        return ResponseEntity.ok().body(contaService.updateConta(idConta,conta));
    }

    @GetMapping("/buscarTodos")
    public ResponseEntity<List<ContaDTO>> getContaList() {
        return ResponseEntity.ok().body(contaService.findAll());
    }

    @GetMapping("/buscarPorId/{idConta}")
    public ResponseEntity<ContaDTO> getContaById(@PathVariable Long idConta) {
        return ResponseEntity.ok().body(contaService.getById(idConta));
    }

    @DeleteMapping("/deletar/{idConta}")
    public ResponseEntity<MessageResponseDTO> deleteById(@PathVariable Long idConta){
        return ResponseEntity.ok().body(contaService.deleteConta(idConta));
    }

    @PutMapping("/transferir/{idOrigem}/{idDestino}")
    public ResponseEntity<MessageResponseDTO> transferencia(@PathVariable Long idOrigem, @PathVariable Long idDestino, @RequestParam BigDecimal valor) {
        return ResponseEntity.ok().body(contaService.transferencia(idOrigem, idDestino, valor));
    }

    @PutMapping("/sacar/{idOrigem}")
    public ResponseEntity<MessageResponseDTO> sacar(@PathVariable Long idOrigem, @RequestParam BigDecimal valor) {
        return ResponseEntity.ok().body(contaService.sacar(idOrigem, valor));
    }

    @PutMapping("/depositar/{idOrigem}")
    public ResponseEntity<MessageResponseDTO> depositar(@PathVariable Long idOrigem, @RequestParam BigDecimal valor) {
        return ResponseEntity.ok().body(contaService.depositar(idOrigem, valor));
    }

}
