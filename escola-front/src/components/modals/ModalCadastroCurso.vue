<template>
  <div>
    <b-modal
      id="modal-cadastro-curso"
      ref="modal"
      title="Novo curso"
      cancel-title="Cancelar"
      ok-title="Gravar"
      :ok-disabled="!curso || !curso.nome"
      @hidden="resetModal"
      @ok="handleSubmit"
    >
      <form ref="form" @submit.stop.prevent="handleSubmit">
        <b-form-group label="Nome" label-for="nome">
          <b-form-input id="nome" v-model="curso.nome" required maxlength="45"></b-form-input>
        </b-form-group>
      </form>
    </b-modal>
  </div>
</template>

<script>
export default {
  props: ['curso'],

  methods: {
    resetModal() {
      this.$emit('modalFechada');
    },

    atualizar() {
      this.$http
        .put(process.env.VUE_APP_BASE_URI + 'curso', this.curso)
        .then(
          () =>
            this.$bvToast.toast(
              'Curso atualizado com sucesso',
              this.$toastInfo
            ),
          erro => this.$bvToast.toast(erro.body.message, this.$toastInfo)
        );
      this.fechar();
    },

    inserir() {
      this.$http
        .post(process.env.VUE_APP_BASE_URI + 'curso', this.curso)
        .then(
          () =>
            this.$bvToast.toast('Curso inserido com sucesso', this.$toastInfo),
          erro => this.$bvToast.toast(erro.body.message, this.$toastInfo)
        );
      this.fechar();
    },

    handleSubmit() {
      if (this.curso.idCurso) this.atualizar();
      else this.inserir();
    },

    abrir() {
      this.$refs.modal.show();
    },

    fechar() {
      this.$refs.modal.hide();
    },
  },
};
</script>

<style scoped>
</style>

