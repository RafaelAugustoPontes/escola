import TabelaGenerica from '../../tables/TabelaGenerica';
import Loading from 'vue-loading-overlay';
import 'vue-loading-overlay/dist/vue-loading.css';

export default {
  created() {
    this.buscarTurmas();
  },

  data() {
    return {
      filtro: '',
      campos: [ 
        {key: 'nome', label : 'Nome', sortable : true}, 
        {key: 'first', label: '', sortable : false, class : "colunaMenor"}
      ],
      turmas: [],
      isLoading: true,
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

    buscarTurmas() {
      this.$http
        .get(process.env.VUE_APP_BASE_URI + 'turma')
        .then(resposta => resposta.json())
        .then(
          turmas => {
            this.turmas = turmas;
            this.isLoading = false;
          },
          erro =>
            this.$toast.error('Erro ao buscar os alunos' + erro.body.message)
        );
    },

    editar(turma) {
      this.$router.push('/consulta-turma-detalhe/' + turma.idTurma);
    },
  },

  components: {
    TabelaGenerica,
    Loading,
  },
};
