package com.app.apptransito.domain.service;

import com.app.apptransito.api.assembler.VeiculoAssembler;
import com.app.apptransito.domain.exception.EntidadeNaoEncontradaException;
import com.app.apptransito.domain.exception.NegocioException;
import com.app.apptransito.domain.model.Proprietario;
import com.app.apptransito.domain.model.StatusVeiculo;
import com.app.apptransito.domain.model.Veiculo;
import com.app.apptransito.domain.repository.VeiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.Optional;

@AllArgsConstructor
@Service
public class RegistroVeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final RegistroProprietarioService registroProprietarioService;

    public Optional<Veiculo> buscarPorId(Long veiculoId) {
        Optional<Veiculo> veiculo = veiculoRepository.findById(veiculoId);
        if (veiculo.isPresent()){
            return veiculo;
        } else {
            throw new EntidadeNaoEncontradaException("Veículo não encontrado");
        }
    }

    public Veiculo buscarVeiculoPorId(Long veiculoId) {
        return veiculoRepository.findById(veiculoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Veículo não encontrado"));
    }

    @Transactional
    public Veiculo cadastrar(Veiculo veiculo) {
        if(veiculo.getId() != null) {
            throw new NegocioException("Veículo a ser cadastrado não deve possuir um ID");
        }
/*        boolean placaEmUso = veiculoRepository.findByPlaca(veiculo.getPlaca())
                        .filter(v -> !v.equals(veiculo))
                        .isPresent();
*/
        Veiculo veiculoEncontrado = veiculoRepository.findByPlaca(veiculo.getPlaca()).orElse(null);
        boolean placaEmUso = veiculoEncontrado != null && !veiculoEncontrado.equals(veiculo);

        if(placaEmUso) {
            throw new NegocioException("Já existe um veículo cadastrado com esta placa");
        }

        Proprietario proprietario = registroProprietarioService.buscar(veiculo.getProprietario().getId());

        veiculo.setProprietario(proprietario);
        veiculo.setStatus(StatusVeiculo.REGULAR);
        veiculo.setDataCadastro(OffsetDateTime.now());

        return veiculoRepository.save(veiculo);
    }


}
