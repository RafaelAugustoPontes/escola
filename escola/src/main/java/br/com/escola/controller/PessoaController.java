package br.com.escola.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import br.com.escola.controller.exception.RegraDeNegocioException;
import br.com.escola.model.entidades.PerfilModel;
import br.com.escola.model.entidades.PessoaModel;
import br.com.escola.model.entidades.PessoaTurmaModel;
import br.com.escola.model.entidades.TurmaModel;
import br.com.escola.model.entidades.UsuarioModel;
import br.com.escola.model.repository.PessoaRepository;
import br.com.escola.model.repository.UsuarioRepository;
import br.com.escola.view.dto.OpcaoDTO;
import br.com.escola.view.dto.PessoaDTO;
import br.com.escola.view.dto.pessoa.AlunoConsultaDTO;
import br.com.escola.view.dto.pessoa.DadosAlunoConsultaDTO;

public class PessoaController {

	private PessoaRepository repository;
	private final ModelMapper mapper = new ModelMapper();
	private UsuarioRepository usuarioRepository;

	public PessoaController(PessoaRepository repository, UsuarioRepository usuarioRepository) {
		this.repository = repository;
		this.usuarioRepository = usuarioRepository;
	}

	public PessoaController(PessoaRepository repository) {
		this.repository = repository;
	}

	public PessoaDTO buscar(Integer id) {
		PessoaModel pessoa = repository.findById(id).get();
		if (pessoa != null)
			return mapper.map(pessoa, PessoaDTO.class);

		return new PessoaDTO();
	}

	public List<PessoaDTO> buscar() {
		List<PessoaDTO> pessoas = new ArrayList<>();
		repository.findAll().forEach(pessoa -> {
			pessoas.add(mapper.map(pessoa, PessoaDTO.class));
		});

		return pessoas;
	}

	public PessoaDTO inserir(PessoaDTO dto) {
		try {
			CPFValidator validator = new CPFValidator();
			validator.assertValid(dto.getCpf());
			PessoaModel pessoa = mapper.map(dto, PessoaModel.class);
			pessoa.setMatricula(buscarMatricula());
			pessoa.setArquivado(false);
			PessoaModel pessoaPorCpf = repository.findByCpf(dto.getCpf());
			if (pessoaPorCpf != null)
				throw new RegraDeNegocioException("Já existe uma pessoa cadastrada com esse CPF.");
			pessoa.setPerfil(PerfilModel.valueOf(dto.getPerfilDescricao()));
			pessoa = repository.save(pessoa);
			UsuarioModel usuarioModel = new UsuarioModel();
			String cpfDesformatado = new CPFFormatter().unformat(dto.getCpf());
			usuarioModel.setLogin(cpfDesformatado);
			usuarioModel.setSenha(new BCryptPasswordEncoder().encode(cpfDesformatado));
			usuarioModel.setPessoa(pessoa);

			usuarioModel = usuarioRepository.save(usuarioModel);

			return mapper.map(pessoa, PessoaDTO.class);
		} catch (InvalidStateException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("CPF inválido");
		}
	}

	public PessoaDTO atualizar(PessoaDTO dto) {
		try {
			CPFValidator validator = new CPFValidator();
			validator.assertValid(dto.getCpf());
			PessoaModel pessoa = mapper.map(dto, PessoaModel.class);
			PessoaModel pessoaPorCpf = repository.findByCpf(dto.getCpf());
			if (pessoaPorCpf != null && !pessoaPorCpf.getIdPessoa().equals(dto.getIdPessoa()))
				throw new RegraDeNegocioException("Já existe uma pessoa cadastrada com esse CPF.");
			pessoa.setPerfil(PerfilModel.valueOf(dto.getPerfilDescricao()));
			pessoa = repository.save(pessoa);

			return mapper.map(pessoa, PessoaDTO.class);
		} catch (InvalidStateException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("CPF inválido");
		}
	}

	private Integer buscarMatricula() {
		return repository.findProximaMatricula();
	}

	public List<OpcaoDTO> buscarPerfis() {
		List<OpcaoDTO> opcoes = new ArrayList<>();
		PerfilModel[] perfis = PerfilModel.values();
		for (PerfilModel perfilModel : perfis) {
			OpcaoDTO opcao = new OpcaoDTO();
			opcao.setText(perfilModel.getDescricao());
			opcao.setValue(perfilModel.name());
			opcoes.add(opcao);
		}

		return opcoes;
	}

	public List<PessoaDTO> buscarAlunos(String username) {
		List<PessoaDTO> resultado = new ArrayList<>();
		UsuarioModel usuario = usuarioRepository.findByLogin(username);

		if (usuario.isAdministrador()) {
			repository.findByPerfil(PerfilModel.ALUNO).forEach(pessoa -> {
				resultado.add(mapper.map(pessoa, PessoaDTO.class));
			});
		} else {
			repository.findAlunoByProfessor(usuario.getPessoa().getIdPessoa()).forEach(pessoa -> {
				resultado.add(mapper.map(pessoa, PessoaDTO.class));
			});
		}

		return resultado;
	}

	public AlunoConsultaDTO buscarAlunoLogado(String username) {
		UsuarioModel usuario = usuarioRepository.findByLogin(username);
		PessoaModel pessoa = usuario.getPessoa();
		AlunoConsultaDTO dto = new AlunoConsultaDTO();
		criarDTOConsulta(pessoa.getIdPessoa(), dto);
		return dto;
	}

	public AlunoConsultaDTO buscarAluno(Integer idPessoa) {
		AlunoConsultaDTO dto = new AlunoConsultaDTO();
		criarDTOConsulta(idPessoa, dto);
		return dto;
	}

	private void criarDTOConsulta(Integer idPessoa, AlunoConsultaDTO dto) {
		PessoaModel aluno = repository.findById(idPessoa).get();
		dto.setIdPessoa(aluno.getIdPessoa());
		dto.setNome(aluno.getNome());
		dto.setCpf(aluno.getCpf());
		dto.setDataNascimento(aluno.getDataNascimento());
		dto.setMatricula(aluno.getMatricula());
		Set<PessoaTurmaModel> pessoaTurmas = aluno.getPessoaTurmas();
		for (PessoaTurmaModel pessoaTurma : pessoaTurmas) {
			DadosAlunoConsultaDTO dado = new DadosAlunoConsultaDTO();
			dado.setFaltas(pessoaTurma.calcularFaltas());
			TurmaModel turma = pessoaTurma.getTurma();
			dado.setNomeCurso(turma.getCurso().getNome());
			dado.setNomeEstagio(turma.getEstagio().getNome());
			dado.setNomeProfessor(turma.getProfessor().getNome());
			dado.setNomeTurma(turma.getNome());
			dado.setAprovado(pessoaTurma.aprovado());
			dado.setNota(pessoaTurma.getNota());
			dado.setQuantidadeAulas(pessoaTurma.quantidadeDeAulas());

			dto.adicionarDado(dado);
		}
	}

	public List<PessoaDTO> buscarProfessores() {
		List<PessoaDTO> resultado = new ArrayList<>();
		List<PessoaModel> alunos = repository.findByPerfilAndArquivadoFalse(PerfilModel.PROFESSOR);

		alunos.forEach(pessoa -> {
			resultado.add(mapper.map(pessoa, PessoaDTO.class));
		});

		return resultado;
	}

	public PessoaDTO arquivar(Integer idPessoa) {
		PessoaModel pessoa = this.repository.getOne(idPessoa);
		pessoa.setArquivado(!pessoa.getArquivado());
		repository.save(pessoa);
		
		return mapper.map(pessoa, PessoaDTO.class);
	}
}
