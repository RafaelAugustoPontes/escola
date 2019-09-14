import TabelaGenerica from '../../tables/TabelaGenerica';
import Loading from 'vue-loading-overlay';
import 'vue-loading-overlay/dist/vue-loading.css';

export default {
  created() {
    this.buscarAlunos();
  },

  data() {
    return {
      filtro: '',
      campos: ['nome'],
      alunos: [],
      isLoading: true,
    };
  },

  methods: {
    dadosComFiltro() {
      if (this.filtro) {
        let expressao = new RegExp(this.filtro.trim(), 'i');
        return this.alunos.filter(aluno => expressao.test(aluno.nome));
      } else {
        return this.alunos;
      }
    },

    buscarAlunos() {
      this.$http
        .get(process.env.VUE_APP_BASE_URI + 'pessoa/alunos')
        .then(resposta => resposta.json())
        .then(
          opcoesAlunos => {
            this.alunos = opcoesAlunos;
            this.isLoading = false;
          },
          erro =>
            this.$bvToast.toast(
              'Erro ao buscar os alunos' + erro.body.message,
              this.$toastErro
            )
        );
    },

    editar(aluno) {
      console.log(aluno);
      this.$router.push('/consulta-aluno-detalhe/' + aluno.idPessoa);
    },
  },

  components: {
    TabelaGenerica,
    Loading,
  },
};
