package br.com.banco.dto.request;

import br.com.banco.enums.TipoConta;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public  class ContaDTO {


    private Long id;

    @NotNull
    private BigDecimal saldo;

    @Enumerated(EnumType.STRING)
    private TipoConta tipo;

    @Temporal(TemporalType.DATE)
    private Date dataCriacao;


}
