package br.com.banco.exception;

public class NumeroMaximoDeContasException extends RuntimeException{
    public NumeroMaximoDeContasException() {
        super("Usuario so pode ter no maximo duas contas");
    }
}
