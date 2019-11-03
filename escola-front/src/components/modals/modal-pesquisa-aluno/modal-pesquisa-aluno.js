import TabelaGenerica from '../../tables/TabelaGenerica.vue';

export default {
  props: ['alunos'],

  components: {
    TabelaGenerica,
  },

  data() {
    return {
      campos: ['nome'],
      exibirFooter: false,
      filtro: '',
    };
  },

  methods: {
    dadosComFiltro() {
      if (this.filtro) {
        let expressao = new RegExp(this.filtro.trim(), 'i');
        return this.alunos.filter(pessoa => expressao.test(pessoa.nome));
      } else {
        return this.alunos;
      }
    },
    selecionar(aluno) {
      this.$emit('alunoSelecionado', aluno);
      this.fechar();
    },

    fechar() {
      this.$refs.modalPesquisa.hide();
    },
  },
};
