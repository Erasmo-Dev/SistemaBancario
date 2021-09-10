package br.com.banco.exception;

public class PessoaNaoEncontradaException extends RuntimeException{
    public PessoaNaoEncontradaException(Long id) {
        super("Pessoa com id "+id+" não encontrada");
    }
}
