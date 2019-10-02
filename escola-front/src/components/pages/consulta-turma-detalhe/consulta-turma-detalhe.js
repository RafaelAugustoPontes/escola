import TabelaGenerica from '../../tables/TabelaGenerica';
import Loading from 'vue-loading-overlay';
import 'vue-loading-overlay/dist/vue-loading.css';

export default {
  created() {
    this.buscarTurma();
  },

  data() {
    return {
      turma: {},
      isLoading: true,
    };
  },

  methods: {
    voltar() {
      this.$router.push('/consulta-turma');
    },

    buscarTurma() {
      this.$http
        .get(
          process.env.VUE_APP_BASE_URI +
            'turma/' +
            this.$route.params.id +
            '/detalhe'
        )
        .then(resposta => resposta.json())
        .then(
          turma => {
            this.turma = turma;
            this.isLoading = false;
          },
          erro =>
            this.$toast.error('Erro ao buscar o aluno' + erro.body.message)
        );
    },
  },

  components: {
    TabelaGenerica,
    Loading,
  },
};
