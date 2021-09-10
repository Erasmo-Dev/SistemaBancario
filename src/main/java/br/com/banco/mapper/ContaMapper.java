package br.com.banco.mapper;

import br.com.banco.dto.request.ContaDTO;
import br.com.banco.model.Conta;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContaMapper {
    ContaMapper INSTANCE = Mappers.getMapper(ContaMapper.class);

    Conta toModel(ContaDTO contaDTO);

    ContaDTO toDTO(Conta conta);
}
