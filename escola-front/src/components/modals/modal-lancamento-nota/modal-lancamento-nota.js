export default {
  props: ['pessoasTurma'],

  data() {
    return {
      isLoading: false,
    };
  },

  methods: {
    resetModal() {
      this.$emit('modalFechada');
    },

    isNotasValidas() {
      let validas = !this.pessoasTurma.some(
        pessoaTurma => pessoaTurma.nota < 0 || pessoaTurma.nota > 100
      );
      return validas;
    },

    handleSubmit(event) {
      event.preventDefault();
      if (!this.isNotasValidas()) {
        this.$toast.error('Uma nota deve ter um valor entre 0 e 100');
        return;
      }

      this.isLoading = true;
      this.$http
        .put(process.env.VUE_APP_BASE_URI + 'pessoa-turma', this.pessoasTurma)
        .then(
          () => {
            this.$toast.success('Notas atualizadas com sucesso');
            this.isLoading = false;
            this.fechar();
          },
          erro => {
            this.$toast.error(erro.body.message);
            this.isLoading = false;
          }
        );
    },

    abrir() {
      this.$refs.modal.show();
    },

    fechar() {
      this.$refs.modal.hide();
    },
  },
};
