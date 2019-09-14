export default {
  props: ['pessoasTurma'],

  methods: {
    resetModal() {
      this.$emit('modalFechada');
    },

    atualizar() {},

    handleSubmit(event) {
      event.preventDefault();
      this.$http
        .put(process.env.VUE_APP_BASE_URI + 'pessoa-turma', this.pessoasTurma)
        .then(
          () => {
            this.$bvToast.toast(
              'Notas atualizadas com sucesso',
              this.$toastInfo
            );
            this.fechar();
          },
          erro => this.$bvToast.toast(erro.body.message, this.$toastInfo)
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
