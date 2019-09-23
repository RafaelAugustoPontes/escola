import TabelaGenerica from '../../tables/TabelaGenerica';
import Loading from 'vue-loading-overlay';
import 'vue-loading-overlay/dist/vue-loading.css';

export default {
  created() {
    this.buscarAluno();
  },

  data() {
    return {
      aluno: {},
      isLoading: true,
    };
  },

  methods: {
    voltar() {
      this.$router.push('/consulta-aluno');
    },

    buscarAluno() {
      let id = this.$route.params.id;
      let url = '';
      if (id) {
        url = 'pessoa/alunos/' + id;
      } else {
        url = 'pessoa/alunos/logado';
      }
      this.$http
        .get(process.env.VUE_APP_BASE_URI + url)
        .then(resposta => resposta.json())
        .then(
          aluno => {
            this.aluno = aluno;
            this.isLoading = false;
          },
          erro =>
            this.$bvToast.toast(
              'Erro ao buscar o aluno' + erro.body.message,
              this.$toastErro
            )
        );
    },
  },

  components: {
    TabelaGenerica,
    Loading,
  },
};
