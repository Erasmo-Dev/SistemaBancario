package br.com.banco.model;

import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Audited
public  class Pessoa {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Length(min=5, max=200)
    private String nomeCompleto;

    @Column(nullable = false, unique = true)
    private String cpf;

    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "pessoa",fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Conta> contas;


}
