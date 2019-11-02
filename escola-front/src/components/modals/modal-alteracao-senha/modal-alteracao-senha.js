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
            this.$toast.success('Senha atualizada com sucesso');
            this.$refs.modal.hide();
          },
          erro => this.$toast.error(erro.body.message)
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
