package br.com.escola.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import br.com.escola.model.entidades.PessoaTurmaModel;
import br.com.escola.model.entidades.TurmaModel;
import br.com.escola.model.repository.PessoaTurmaRepository;
import br.com.escola.model.repository.TurmaRepository;
import br.com.escola.view.dto.PessoaDTO;
import br.com.escola.view.dto.PessoaTurmaDTO;

public class PessoaTurmaController {

	private TurmaRepository turmaRepository;
	private final ModelMapper mapper = new ModelMapper();
	private PessoaTurmaRepository pessoaTurmaRepository;

	public PessoaTurmaController(TurmaRepository turmaRepository, PessoaTurmaRepository pessoaTurmaRepository) {
		this.turmaRepository = turmaRepository;
		this.pessoaTurmaRepository = pessoaTurmaRepository;
	}

	public List<PessoaTurmaDTO> buscarPessoasTurma(Integer idTurma) {
		TurmaModel turmaModel = turmaRepository.findById(idTurma).get();
		List<PessoaTurmaDTO> pessoasTurmas = new ArrayList<>();
		for (PessoaTurmaModel pessoaTurma : turmaModel.getAlunosTurma()) {
			PessoaTurmaDTO dto = new PessoaTurmaDTO();
			dto.setIdPessoaTurma(pessoaTurma.getIdPessoaTurma());
			dto.setTurma(new TurmaController(turmaRepository).obterDTO(turmaModel));
			PessoaDTO pessoaDTO = mapper.map(pessoaTurma.getPessoa(), PessoaDTO.class);
			dto.setPessoa(pessoaDTO);
			dto.setNota(pessoaTurma.getNota());
			pessoasTurmas.add(dto);
		}
		return pessoasTurmas;
	}

	public void atualizarPessoasTurma(List<PessoaTurmaDTO> pessoasTurma) {
		pessoasTurma.forEach(pessoaTurma -> {
			PessoaTurmaModel pessoaTurmaModel = pessoaTurmaRepository.findById(pessoaTurma.getIdPessoaTurma()).get();
			pessoaTurmaModel.setNota(pessoaTurma.getNota());
			pessoaTurmaRepository.save(pessoaTurmaModel);
		});
	}

}
