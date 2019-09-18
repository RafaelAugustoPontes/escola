export default {
  data() {
    return {
      usuario: { senhaAtual: '', novaSenha: '', repeticaoNovaSenha: '' },
    };
  },

  methods: {
    handleSubmit(event) {
      event.preventDefault();
      console.log(this.usuario);
      this.$http
        .put(process.env.VUE_APP_BASE_URI + 'usuario', this.usuario)
        .then(
          () => {
            this.$bvToast.toast(
              'Senha atualizada com sucesso',
              this.$toastInfo
            );
            this.$refs.modal.hide();
          },
          erro => this.$bvToast.toast(erro.body.message, this.$toastInfo)
        );
    },
    resetModal() {
      this.usuario = { senhaAtual: '', novaSenha: '', repeticaoNovaSenha: '' };
    },

    abrir() {
      this.$refs.modal.show();
    },
  },
};
