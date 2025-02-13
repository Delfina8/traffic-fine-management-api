package com.app.apptransito.api.assembler;

import com.app.apptransito.api.representationmodel.VeiculoRepresentationModel;
import com.app.apptransito.domain.model.Veiculo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class VeiculoAssembler {

    private final ModelMapper modelMapper;

    public VeiculoRepresentationModel toRepresentationModel(Veiculo veiculo) {
        return modelMapper.map(veiculo, VeiculoRepresentationModel.class);
    }

    public List<VeiculoRepresentationModel> toCollectionRepresentationModel(List<Veiculo> veiculos) {
        return veiculos.stream()
                .map(veiculo -> toRepresentationModel(veiculo))
                .toList();
    }

}
