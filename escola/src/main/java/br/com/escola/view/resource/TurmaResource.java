package br.com.escola.view.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.escola.controller.TurmaController;
import br.com.escola.model.repository.CursoRepository;
import br.com.escola.model.repository.EstagioRepository;
import br.com.escola.model.repository.PessoaRepository;
import br.com.escola.model.repository.PessoaTurmaRepository;
import br.com.escola.model.repository.TurmaRepository;
import br.com.escola.model.repository.UnidadeRepository;
import br.com.escola.model.repository.UsuarioRepository;
import br.com.escola.view.dto.OpcaoParaSelect;
import br.com.escola.view.dto.TurmaDTO;
import br.com.escola.view.dto.turma.TurmaConsultaDTO;

@RestController
@RequestMapping("/turma")
public class TurmaResource {

	@Autowired
	private TurmaRepository turmaRepository;

	@Autowired
	private PessoaTurmaRepository pessoaTurmaRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private UnidadeRepository unidadeRepository;

	@Autowired
	private CursoRepository cursoRepository;

	@Autowired
	private EstagioRepository estagioRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/{id}")
	public TurmaDTO buscarPorId(@PathVariable Integer id) {
		return new TurmaController(turmaRepository).buscarPorId(id);
	}

	@GetMapping("/{id}/detalhe")
	public TurmaConsultaDTO buscarDadosTurma(@PathVariable Integer id) {
		return new TurmaController(turmaRepository).buscarConsultaTurma(id);
	}

	@GetMapping
	public List<TurmaDTO> buscar() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return new TurmaController(turmaRepository).buscar(authentication.getName(), usuarioRepository);
	}

	@GetMapping("/turnos")
	public List<OpcaoParaSelect> buscarTurnos() {
		return new TurmaController(turmaRepository).buscarTurnos();
	}

	@PostMapping
	@Transactional(rollbackFor = Exception.class)
	public void gravar(@RequestBody TurmaDTO dto) {
		new TurmaController(turmaRepository, pessoaTurmaRepository, pessoaRepository, unidadeRepository,
				cursoRepository, estagioRepository).persistir(dto);
	}

	@PutMapping
	@Transactional(rollbackFor = Exception.class)
	public void atualizar(@RequestBody TurmaDTO dto) {
		new TurmaController(turmaRepository, pessoaTurmaRepository, pessoaRepository, unidadeRepository,
				cursoRepository, estagioRepository).atualizar(dto);
	}
	
	@PutMapping("/arquivar/{idTurma}")
	public TurmaDTO arquivar(@PathVariable Integer idTurma) {
		return new TurmaController(turmaRepository, pessoaTurmaRepository, pessoaRepository, unidadeRepository,
				cursoRepository, estagioRepository).arquivar(idTurma);
	}

}
