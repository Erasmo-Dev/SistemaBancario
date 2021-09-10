package br.com.banco.model;

import br.com.banco.enums.TipoConta;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Audited
public  class Conta {

    @Id
    @GeneratedValue()
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Pessoa pessoa;

    @Column(nullable= false)
    private BigDecimal saldo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoConta tipo;

    @Temporal(TemporalType.DATE)
    @Column
    private Date dataCriacao;


}
