package br.com.banco.exception;

public class ContaDoMesmoTipoException extends RuntimeException{

    public ContaDoMesmoTipoException(String tipo) {
        super("Não se pode ter duas contas do tipo "+tipo);
    }

}
