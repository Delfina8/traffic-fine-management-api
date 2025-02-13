package com.app.apptransito.api.assembler;

import com.app.apptransito.api.representationmodel.AutuacaoRepresentationModel;
import com.app.apptransito.domain.model.Autuacao;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AutuacaoAssembler {

    private final ModelMapper modelMapper;

    public AutuacaoAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public AutuacaoRepresentationModel toRepresentationModel(Autuacao atuacao) {
        return modelMapper.map(atuacao, AutuacaoRepresentationModel.class);
    }

    public List<AutuacaoRepresentationModel> toCollectionRepresentationModel(List<Autuacao> autuacoes) {
        return autuacoes.stream()
                .map(autuacao -> toRepresentationModel(autuacao))
                .toList();
    }
}
