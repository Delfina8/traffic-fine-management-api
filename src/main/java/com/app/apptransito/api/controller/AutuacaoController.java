package com.app.apptransito.api.controller;

import com.app.apptransito.api.assembler.AutuacaoAssembler;
import com.app.apptransito.api.representationmodel.AutuacaoRepresentationModel;
import com.app.apptransito.domain.model.Autuacao;
import com.app.apptransito.domain.model.Veiculo;
import com.app.apptransito.domain.service.RegistroAutuacaoService;
import com.app.apptransito.domain.service.RegistroVeiculoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/veiculos/{veiculoId}/autuacoes")
public class AutuacaoController {

    private final AutuacaoAssembler autuacaoAssembler;
    private final RegistroAutuacaoService registroAutuacaoService;
    private final RegistroVeiculoService registroVeiculoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AutuacaoRepresentationModel registrar(@PathVariable Long veiculoId,
                                                 @RequestBody @Valid Autuacao autuacao) {
    return autuacaoAssembler.toRepresentationModel(registroAutuacaoService.registrar(veiculoId, autuacao));
    }

    @GetMapping
    public List<AutuacaoRepresentationModel> listarAutuacoes(@PathVariable Long veiculoId) {
        Veiculo veiculo = registroVeiculoService.buscarVeiculoPorId(veiculoId);
        return autuacaoAssembler.toCollectionRepresentationModel(veiculo.getAutuacoes());
    }


}
