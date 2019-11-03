export default {
  props: ['alunos'],

  data() {
    return {
      campos: ['nome'],
    };
  },

  methods: {
    selecionar(aluno) {
      this.$emit('alunoSelecionado', aluno);
      this.fechar();
    },

    fechar() {
      this.$refs.modalPesquisa.hide();
    },
  },
};
