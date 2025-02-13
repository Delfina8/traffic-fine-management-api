package com.app.apptransito.api.controller;

import com.app.apptransito.api.assembler.VeiculoAssembler;
import com.app.apptransito.api.representationmodel.VeiculoRepresentationModel;
import com.app.apptransito.domain.model.Veiculo;
import com.app.apptransito.domain.repository.VeiculoRepository;
import com.app.apptransito.domain.service.ApreensaoVeiculoService;
import com.app.apptransito.domain.service.RegistroVeiculoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoRepository veiculoRepository;
    private final RegistroVeiculoService registroVeiculoService;
    private final VeiculoAssembler veiculoAssembler;
    private final ApreensaoVeiculoService apreensaoVeiculoService;


    @GetMapping
    public List<VeiculoRepresentationModel> buscarVeiculos() {
        return veiculoAssembler.toCollectionRepresentationModel(veiculoRepository.findAll());
    }

    @GetMapping("/{veiculoId}")
    public ResponseEntity<VeiculoRepresentationModel> buscarVeiculoPorId(@PathVariable Long veiculoId) {
        return registroVeiculoService.buscarPorId(veiculoId)
                .map(veiculo -> veiculoAssembler.toRepresentationModel(veiculo))
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VeiculoRepresentationModel cadastrarVeiculo(@Valid @RequestBody Veiculo veiculo) {
        return veiculoAssembler.toRepresentationModel(registroVeiculoService.cadastrar(veiculo));
    }

    @PutMapping("/{veiculoId}/apreensao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void apreender(@PathVariable Long veiculoId) {
        apreensaoVeiculoService.apreender(veiculoId);
    }

    @DeleteMapping("/{veiculoId}/apreensao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerApreensao(@PathVariable Long veiculoId) {
        apreensaoVeiculoService.removerApreensao(veiculoId);
    }

}
