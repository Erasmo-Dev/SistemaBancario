package br.com.banco.exception;

public class NenhumaContaEncontradaException extends RuntimeException{

    public NenhumaContaEncontradaException() {
        super("Nenhuma conta encontrada");
    }
}
