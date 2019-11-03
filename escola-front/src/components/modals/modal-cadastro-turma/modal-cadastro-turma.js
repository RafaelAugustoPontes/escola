import TabelaGenerica from '../../tables/TabelaGenerica';
import AppLoading from '../../partials/app-loading/app-loading.vue';
import ModalPesquisaAluno from '../modal-pesquisa-aluno/modal-pesquisa-aluno.vue';
import Chip from '../../partials/chip/chip.vue';

export default {
  created() {
    this.buscarAlunos();
    this.buscarUnidades();
    this.buscarCursos();
    this.buscarEstagios();
    this.buscarProfessores();
    this.buscarTurnos();
  },

  props: ['turma'],

  data() {
    return {
      opcoesUnidade: [],
      opcoesCursos: [],
      opcoesEstagios: [],
      opcoesProfessores: [],
      opcoesAlunos: [],
      opcoesTurno: [],
      campos: ['nome'],
      isLoading: false,
    };
  },

  components: {
    'tabela-generica': TabelaGenerica,
    'app-loading': AppLoading,
    'modal-pesquisa-aluno': ModalPesquisaAluno,
    chip: Chip,
  },

  methods: {
    alunoSelecionado(aluno) {
      this.selecionar(aluno);
    },

    resetModal() {
      this.$emit('modalFechada');
    },

    atualizar() {
      this.isLoading = true;
      this.$http.put(process.env.VUE_APP_BASE_URI + 'turma', this.turma).then(
        () => {
          this.$toast.success('Turma atualizada com sucesso');
          this.isLoading = false;
          this.fechar();
        },
        erro => {
          this.$toast.error(erro.body.message);
          this.isLoading = false;
        }
      );
    },

    inserir() {
      this.isLoading = true;
      this.$http.post(process.env.VUE_APP_BASE_URI + 'turma', this.turma).then(
        () => {
          this.$toast.success('Turma inserida com sucesso');
          this.isLoading = false;
          this.fechar();
        },
        erro => {
          this.$toast.error(erro.body.message);
          this.isLoading = false;
        }
      );
    },

    handleSubmit(event) {
      event.preventDefault();
      if (this.turma.idTurma) this.atualizar();
      else this.inserir();
    },

    abrir() {
      this.$refs.modal.show();
    },

    fechar() {
      this.$refs.modal.hide();
    },

    selecionar(item) {
      let alunoExiste = false;
      this.turma.alunos.forEach(aluno => {
        if (aluno.idPessoa == item.idPessoa) {
          alunoExiste = true;
        }
      });
      if (!alunoExiste) this.turma.alunos.unshift(item);
    },
    remover(id) {
      if (this.turma.idTurma) {
        this.$toast.warning(
          'Não é possível remover alunos de uma turma em andamento'
        );
      } else {
        for (let i = 0; i < this.turma.alunos.length; i++) {
          if (this.turma.alunos[i].idPessoa == id) {
            this.turma.alunos.splice(i, 1);
          }
        }
      }
    },

    buscarAlunos() {
      this.$http
        .get(process.env.VUE_APP_BASE_URI + 'pessoa/alunos')
        .then(resposta => resposta.json())
        .then(
          opcoesAlunos => (this.opcoesAlunos = opcoesAlunos),
          erro =>
            this.$toast.error('Erro ao buscar os alunos' + erro.body.message)
        );
    },
    buscarUnidades() {
      this.$http
        .get(process.env.VUE_APP_BASE_URI + 'unidade')
        .then(resposta => resposta.json())
        .then(
          opcoesUnidade => (this.opcoesUnidade = opcoesUnidade),
          erro =>
            this.$toast.error('Erro ao buscar as unidades' + erro.body.message)
        );
    },
    buscarCursos() {
      this.$http
        .get(process.env.VUE_APP_BASE_URI + 'curso')
        .then(resposta => resposta.json())
        .then(
          opcoesCursos => (this.opcoesCursos = opcoesCursos),
          erro =>
            this.$toast.error('Erro ao buscar os cursos' + erro.body.message)
        );
    },
    buscarEstagios() {
      this.$http
        .get(process.env.VUE_APP_BASE_URI + 'estagio')
        .then(resposta => resposta.json())
        .then(
          opcoesEstagios => (this.opcoesEstagios = opcoesEstagios),
          erro =>
            this.$toast.error('Erro ao buscar os estágios' + erro.body.message)
        );
    },
    buscarProfessores() {
      this.$http
        .get(process.env.VUE_APP_BASE_URI + 'pessoa/professores')
        .then(resposta => resposta.json())
        .then(
          opcoesProfessores => (this.opcoesProfessores = opcoesProfessores),
          erro =>
            this.$toast.error(
              'Erro ao buscar os professores' + erro.body.message
            )
        );
    },
    buscarTurnos() {
      this.$http
        .get(process.env.VUE_APP_BASE_URI + 'turma/turnos')
        .then(resposta => resposta.json())
        .then(
          opcoesTurno => (this.opcoesTurno = opcoesTurno),
          erro =>
            this.$toast.error('Erro ao buscar os turnos' + erro.body.message)
        );
    },
  },
};
