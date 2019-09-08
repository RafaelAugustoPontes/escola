package br.com.escola.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.escola.model.entidades.AulaModel;
import br.com.escola.model.entidades.PessoaAulaModel;
import br.com.escola.model.entidades.PessoaModel;
import br.com.escola.model.entidades.PessoaTurmaModel;
import br.com.escola.model.entidades.TurmaModel;
import br.com.escola.model.repository.AulaRepository;
import br.com.escola.model.repository.PessoaAulaRepository;
import br.com.escola.model.repository.TurmaRepository;
import br.com.escola.view.dto.AulaDTO;
import br.com.escola.view.dto.PessoaDTO;

public class AulaController {

	private TurmaRepository turmaRepository;
	private AulaRepository aulaRepository;
	private PessoaAulaRepository pessoaAulaRepository;

	public AulaController(TurmaRepository turmaRepository, AulaRepository aulaRepository,
			PessoaAulaRepository pessoaAulaRepository) {
		this.turmaRepository = turmaRepository;
		this.aulaRepository = aulaRepository;
		this.pessoaAulaRepository = pessoaAulaRepository;
	}

	public List<AulaDTO> buscarAulas(Integer idTurma) {
		TurmaModel turma = turmaRepository.findById(idTurma).get();
		List<AulaDTO> aulas = new ArrayList<>();
		for (AulaModel aula : turma.getAulas()) {
			AulaDTO dto = new AulaDTO();
			dto.setIdAula(aula.getIdAula());
			dto.setData(aula.getData());
			dto.setDiario(aula.getDiario());
			aulas.add(dto);
		}
		return aulas;
	}

	public void inserir(AulaDTO dto) {
		TurmaModel turmaModel = turmaRepository.findById(dto.getTurma().getIdTurma()).get();
		AulaModel aulaModel = obterAula(dto, turmaModel);
		aulaRepository.save(aulaModel);
		for (PessoaTurmaModel pessoaTurma : turmaModel.getAlunosTurma()) {
			PessoaModel alunoDaTurma = pessoaTurma.getPessoa();
			boolean alunoPresente = false;
			for (PessoaDTO aluno : dto.getAlunos()) {
				if (aluno.getIdPessoa() == alunoDaTurma.getIdPessoa()) {
					alunoPresente = true;
					break;
				}
			}
			PessoaAulaModel pessoaAula = new PessoaAulaModel();
			pessoaAula.setAula(aulaModel);
			pessoaAula.setPessoa(pessoaTurma.getPessoa());
			pessoaAula.setPresente(alunoPresente);
			pessoaAulaRepository.save(pessoaAula);
		}
	}

	public AulaModel obterAula(AulaDTO dto, TurmaModel turma) {
		AulaModel aula;
		if (dto.getIdAula() != null)
			aula = aulaRepository.findById(dto.getIdAula()).get();
		else
			aula = new AulaModel();
		aula.setData(dto.getData());
		aula.setDiario(dto.getDiario());
		aula.setTurma(turma);

		return aula;
	}

}
