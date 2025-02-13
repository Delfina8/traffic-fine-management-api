package com.app.apptransito.api.assembler;

import com.app.apptransito.api.representationmodel.ProprietarioRepresentationModel;
import com.app.apptransito.domain.model.Proprietario;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProprietarioAssembler {

    private final ModelMapper modelMapper;

    public ProprietarioAssembler(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public ProprietarioRepresentationModel toRepresentationModel(Proprietario proprietario) {
        return modelMapper.map(proprietario, ProprietarioRepresentationModel.class);
    }

    public List<ProprietarioRepresentationModel> toCollectionRepresentationModel(List<Proprietario> proprietarios) {
        return proprietarios.stream()
                .map(proprietario -> toRepresentationModel(proprietario))
                .toList();
    }

}
