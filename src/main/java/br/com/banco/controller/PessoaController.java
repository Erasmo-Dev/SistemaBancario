package br.com.banco.controller;

import br.com.banco.dto.request.PessoaDTO;
import br.com.banco.service.PessoaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pessoa")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PessoaController {

    private PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<Long> criarPessoa(@RequestBody @Valid PessoaDTO pessoaDTO) {
        return ResponseEntity.ok().body(pessoaService.criarPessoa(pessoaDTO));
    }

    @GetMapping
    public ResponseEntity<List<PessoaDTO>> listarTodos() {
        return ResponseEntity.ok().body(pessoaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> encontrarPorId(@PathVariable Long id)  {
        return ResponseEntity.ok().body(pessoaService.encontrarPorID(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> atualizarPorId(@PathVariable Long id, @RequestBody @Valid PessoaDTO pessoaDTO) {
        return ResponseEntity.ok().body(pessoaService.atualizarPorId(id, pessoaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deletarPorID(@PathVariable Long id) {
        return ResponseEntity.ok().body(pessoaService.deletar(id));
    }
}
