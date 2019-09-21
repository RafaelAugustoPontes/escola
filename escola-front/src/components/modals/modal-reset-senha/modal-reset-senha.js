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
            this.$bvToast.toast(
              'Senha resetada com sucesso. A senha agora é o seu CPF sem caracteres especiais',
              this.$toastInfo
            );
            this.$refs.modal.hide();
          },
          erro => this.$bvToast.toast(erro.body.message, this.$toastInfo)
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
