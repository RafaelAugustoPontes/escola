package br.com.escola.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import br.com.escola.model.entidades.CursoModel;
import br.com.escola.model.entidades.EstagioModel;
import br.com.escola.model.entidades.PessoaModel;
import br.com.escola.model.entidades.PessoaTurmaModel;
import br.com.escola.model.entidades.TurmaModel;
import br.com.escola.model.entidades.TurnoModel;
import br.com.escola.model.entidades.UnidadeModel;
import br.com.escola.model.repository.CursoRepository;
import br.com.escola.model.repository.EstagioRepository;
import br.com.escola.model.repository.PessoaRepository;
import br.com.escola.model.repository.PessoaTurmaRepository;
import br.com.escola.model.repository.TurmaRepository;
import br.com.escola.model.repository.UnidadeRepository;
import br.com.escola.view.dto.CursoDTO;
import br.com.escola.view.dto.EstagioDTO;
import br.com.escola.view.dto.PessoaDTO;
import br.com.escola.view.dto.TurmaDTO;
import br.com.escola.view.dto.UnidadeDTO;

public class TurmaController {

	private TurmaRepository repository;
	private PessoaTurmaRepository pessoaTurmaRepository;
	private final ModelMapper mapper = new ModelMapper();
	private PessoaRepository pessoaRepository;
	private UnidadeRepository unidadeRepository;
	private CursoRepository cursoRepository;
	private EstagioRepository estagioRepository;

	public TurmaController(TurmaRepository turmaRepository) {
		this.repository = turmaRepository;
	}

	public TurmaController(TurmaRepository turmaRepository, PessoaTurmaRepository pessoaTurmaRepository,
			PessoaRepository pessoaRepository, UnidadeRepository unidadeRepository, CursoRepository cursoRepository,
			EstagioRepository estagioRepository) {
		this.repository = turmaRepository;
		this.pessoaTurmaRepository = pessoaTurmaRepository;
		this.pessoaRepository = pessoaRepository;
		this.unidadeRepository = unidadeRepository;
		this.cursoRepository = cursoRepository;
		this.estagioRepository = estagioRepository;
	}

	public TurmaDTO buscarPorId(Integer id) {
		TurmaModel turmaModel = repository.findById(id).get();
		return obterDTO(turmaModel);
	}

	public List<TurmaDTO> buscar() {
		List<TurmaDTO> turma = new ArrayList<>();
		repository.findAll().forEach(pessoa -> {
			turma.add(obterDTO(pessoa));
		});

		return turma;
	}

	public TurmaDTO persistir(TurmaDTO dto) {
		TurmaModel turma = obterTurma(dto);
		repository.save(turma);
		for (PessoaDTO dtoAluno : dto.getAlunos()) {
			PessoaTurmaModel pessoaTurmaModel = new PessoaTurmaModel();
			PessoaModel aluno = pessoaRepository.findById(dtoAluno.getIdPessoa()).get();
			pessoaTurmaModel.setPessoa(aluno);
			pessoaTurmaModel.setTurma(turma);
			pessoaTurmaRepository.save(pessoaTurmaModel);
		}
		return dto;
	}

	private TurmaModel obterTurma(TurmaDTO dto) {
		TurmaModel turma = new TurmaModel();
		if (dto.getIdTurma() != null)
			turma.setIdTurma(dto.getIdTurma());
		turma.setNome(dto.getNome());
		turma.setDataInicio(dto.getDataInicio());
		turma.setDataFim(dto.getDataFim());
		UnidadeModel unidadeModel = unidadeRepository.findById(dto.getUnidade().getIdUnidade()).get();
		turma.setUnidade(unidadeModel);
		CursoModel cursoModel = cursoRepository.findById(dto.getCurso().getIdCurso()).get();
		turma.setCurso(cursoModel);
		PessoaModel professor = pessoaRepository.findById(dto.getProfessor().getIdPessoa()).get();
		turma.setProfessor(professor);
		turma.setTurno(TurnoModel.valueOf(dto.getTurno()));
		EstagioModel estagioModel = estagioRepository.findById(dto.getEstagio().getIdEstagio()).get();
		turma.setEstagio(estagioModel);

		return turma;
	}

	public TurmaDTO obterDTO(TurmaModel turma) {
		TurmaDTO dto = new TurmaDTO();
		dto.setIdTurma(turma.getIdTurma());
		dto.setNome(turma.getNome());
		dto.setDataInicio(turma.getDataInicio());
		dto.setDataFim(turma.getDataFim());
		dto.setTurno(turma.getTurno().getDescricao());
		UnidadeDTO unidadeDTO = new UnidadeDTO();
		mapper.map(turma.getUnidade(), unidadeDTO);
		dto.setUnidade(unidadeDTO);

		CursoDTO cursoDTO = new CursoDTO();
		mapper.map(turma.getCurso(), cursoDTO);
		dto.setCurso(cursoDTO);

		PessoaDTO professorDTO = new PessoaDTO();
		mapper.map(turma.getProfessor(), professorDTO);
		dto.setProfessor(professorDTO);

		EstagioDTO estagioDTO = new EstagioDTO();
		mapper.map(turma.getEstagio(), estagioDTO);
		dto.setEstagio(estagioDTO);

		for (PessoaTurmaModel pessoaTurma : turma.getAlunosTurma()) {
			PessoaModel pessoa = pessoaTurma.getPessoa();
			PessoaDTO alunoDTO = new PessoaDTO();
			mapper.map(pessoa, alunoDTO);
			dto.setEstagio(estagioDTO);
		}

		return dto;
	}

}
