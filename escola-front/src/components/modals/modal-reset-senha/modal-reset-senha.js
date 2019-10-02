export default {
  data() {
    return {
      usuario: { username: '', dataNascimento: '' },
    };
  },

  methods: {
    handleSubmit(event) {
      event.preventDefault();
      console.log(this.usuario);
      this.$http
        .put(process.env.VUE_APP_BASE_URI + 'login/reset-senha', this.usuario)
        .then(
          () => {
            this.$toast.success(
              'Senha resetada com sucesso. A senha agora é o seu CPF sem caracteres especiais'
            );
            this.$refs.modal.hide();
          },
          erro => this.$toast.error(erro.body.message)
        );
    },
    resetModal() {
      this.usuario = {};
    },

    abrir() {
      this.$refs.modal.show();
    },
  },
};
