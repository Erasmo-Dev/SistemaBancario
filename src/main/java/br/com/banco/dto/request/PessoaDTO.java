package br.com.banco.dto.request;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public  class PessoaDTO {

    private Long id;

    @NotNull
    @Length(min=5, max=200)
    private String nomeCompleto;

    @NotEmpty
    @CPF
    private String cpf;

    private String dataNascimento;

    @NotNull
    private List<ContaDTO> contas;


}
