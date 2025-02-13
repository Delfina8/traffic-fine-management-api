package com.app.apptransito.domain.service;

import com.app.apptransito.domain.model.Autuacao;
import com.app.apptransito.domain.model.Veiculo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistroAutuacaoService {

    private final RegistroVeiculoService registroVeiculoService;

    public RegistroAutuacaoService(RegistroVeiculoService registroVeiculoService) {
        this.registroVeiculoService = registroVeiculoService;
    }

    @Transactional
    public Autuacao registrar(Long veiculoId, Autuacao novaAutuacao) {
        Veiculo veiculo = registroVeiculoService.buscarVeiculoPorId(veiculoId);
        return veiculo.adicionarAutuacao(novaAutuacao);
    }
}
