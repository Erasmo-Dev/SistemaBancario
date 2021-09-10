package br.com.banco.exception;

public class NenhumaPessoaEncontradaException extends RuntimeException{

    public NenhumaPessoaEncontradaException() {
        super("Nenhuma pessoa encontrada");
    }
}
