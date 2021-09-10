package br.com.banco.exception;

import lombok.Getter;

public class SaldoNaoDisponivelException extends RuntimeException{
    public SaldoNaoDisponivelException() {
        super("Saldo não disponivel");
    }
}
