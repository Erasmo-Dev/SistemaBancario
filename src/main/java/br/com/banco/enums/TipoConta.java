package br.com.banco.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoConta {
    CORRENTE("Conta para saque "),
    POUPANCA("Conta para deposito");
    private final String descricao;
}
