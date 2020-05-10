import TabelaGenerica from '../../tables/TabelaGenerica.vue';
import ModalLancamentoNota from '../../modals/modal-lancamento-nota/modal-lancamento-nota.vue';

export default {
  created() {
    this.buscar();
  },

  data() {
    return {
      turmas: [],
      filtro: '',
      pessoasTurma: [],
      campos: [ 
        {key: 'nome', label : 'Nome', sortable : true}, 
        {key: 'first', label: '', sortable : false, class : "colunaMenor"}
      ],
    };
  },

  methods: {
    dadosComFiltro() {
      if (this.filtro) {
        let expressao = new RegExp(this.filtro.trim(), 'i');
        return this.turmas.filter(turma => expressao.test(turma.nome));
      } else {
        return this.turmas;
      }
    },

    editar(turma) {
      this.$http
        .get(process.env.VUE_APP_BASE_URI + 'pessoa-turma/' + turma.idTurma)
        .then(resposta => resposta.json())
        .then(
          data => {
            this.pessoasTurma = data;
            this.$refs.modal.abrir();
          },
          erro =>
            this.$toast.error('Erro ao buscar os alunos' + erro.body.message)
        );
    },

    fecharModal() {
      this.pessoasTurma = [];
      this.buscar();
      this.$refs.modal.fechar();
    },

    buscar() {
      this.$http
        .get(process.env.VUE_APP_BASE_URI + 'turma')
        .then(resposta => resposta.json())
        .then(
          turmas => (this.turmas = turmas),
          erro =>
            this.$toast.error('Erro ao buscar as turmas' + erro.body.message)
        );
    },
  },
  components: {
    'tabela-generica': TabelaGenerica,
    'modal-lancamento-nota': ModalLancamentoNota,
  },
};
