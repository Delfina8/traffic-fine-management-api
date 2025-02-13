package com.app.apptransito.domain.service;

import com.app.apptransito.domain.model.Veiculo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ApreensaoVeiculoService {

    private final RegistroVeiculoService registroVeiculoService;

    @Transactional
    public void apreender(Long veiculoId) {
        Veiculo veiculo = registroVeiculoService.buscarVeiculoPorId(veiculoId);
        veiculo.apreender();
    }

    @Transactional
    public void removerApreensao(Long veiculoId) {
        Veiculo veiculo = registroVeiculoService.buscarVeiculoPorId(veiculoId);
        veiculo.removerApreensao();
    }

}
