package com.app.apptransito.api.controller;

import com.app.apptransito.api.assembler.ProprietarioAssembler;
import com.app.apptransito.api.representationmodel.ProprietarioRepresentationModel;
import com.app.apptransito.domain.model.Proprietario;
import com.app.apptransito.domain.repository.ProprietarioRepository;
import com.app.apptransito.domain.service.RegistroProprietarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/proprietarios")
public class ProprietarioController {

    private final ProprietarioAssembler proprietarioAssembler;
    private final ProprietarioRepository proprietarioRepository;
    private final RegistroProprietarioService registroProprietarioService;

    @GetMapping
    public List<ProprietarioRepresentationModel> buscarProprietarios() {
        return proprietarioAssembler.toCollectionRepresentationModel(proprietarioRepository.findAll());
    }

    @GetMapping("/{proprietarioId}")
    public ResponseEntity<ProprietarioRepresentationModel> buscarProprietarioPorId(@PathVariable Long proprietarioId) {
        return proprietarioRepository.findById(proprietarioId)
                .map(proprietario -> proprietarioAssembler.toRepresentationModel(proprietario))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProprietarioRepresentationModel cadastrarProprietario(@Valid @RequestBody Proprietario proprietario) {
        return proprietarioAssembler.toRepresentationModel(registroProprietarioService.salvar(proprietario));
    }

    @PutMapping("/{proprietarioId}")
    public ResponseEntity<ProprietarioRepresentationModel> atualizar(@PathVariable Long proprietarioId,
                                                                     @Valid @RequestBody Proprietario proprietario) {
        if(!registroProprietarioService.existsById(proprietarioId)){
            return ResponseEntity.notFound().build();
        }

        proprietario.setId(proprietarioId);
        Proprietario proprietarioAtualizado = registroProprietarioService.salvar(proprietario);
        return ResponseEntity.ok(proprietarioAssembler.toRepresentationModel(proprietarioAtualizado));
    }

    @DeleteMapping("/{proprietarioId}")
    public ResponseEntity<Void> removerProprietario(@PathVariable Long proprietarioId){
        if(!registroProprietarioService.existsById(proprietarioId)){
            return ResponseEntity.notFound().build();
        }

        registroProprietarioService.excluir(proprietarioId);
        return ResponseEntity.noContent().build();
    }


}
