package com.app.apptransito.common;

import com.app.apptransito.api.representationmodel.VeiculoRepresentationModel;
import com.app.apptransito.domain.model.Veiculo;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        var modelMapper = new ModelMapper();

        modelMapper.createTypeMap(Veiculo.class, VeiculoRepresentationModel.class)
                .addMappings(mapper -> mapper.map(Veiculo::getPlaca, VeiculoRepresentationModel::setNumeroPlaca));

        return modelMapper;
    }

}
