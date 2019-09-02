<template>
  <div>
    <b-modal
      id="modal-cadastro-unidade"
      ref="modal"
      title="Nova unidade"
      cancel-title="Cancelar"
      ok-title="Gravar"
      :ok-disabled="!unidade || !unidade.nome"
      @hidden="resetModal"
      @ok="handleSubmit"
    >
      <form ref="form" @submit.stop.prevent="handleSubmit">
        <b-form-group label="Nome" label-for="nome">
          <b-form-input id="nome" v-model="unidade.nome" required maxlength="45"></b-form-input>
        </b-form-group>
      </form>
    </b-modal>
  </div>
</template>

<script>
export default {
  props: ['unidade'],

  methods: {
    resetModal() {
      this.$emit('modalFechada');
    },

    atualizarUnidade() {
      this.$http
        .put(process.env.VUE_APP_BASE_URI + 'unidade', this.unidade)
        .then(
          () =>
            this.$bvToast.toast(
              'Unidade atualizada com sucesso',
              this.$toastInfo
            ),
          erro => this.$bvToast.toast(erro.body.message, this.$toastInfo)
        );
      this.fechar();
    },

    inserirUnidade() {
      this.$http
        .post(process.env.VUE_APP_BASE_URI + 'unidade', this.unidade)
        .then(
          () =>
            this.$bvToast.toast(
              'Unidade inserida com sucesso',
              this.$toastInfo
            ),
          erro => this.$bvToast.toast(erro.body.message, this.$toastInfo)
        );
      this.fechar();
    },

    handleSubmit() {
      if (this.unidade.idUnidade) this.atualizarUnidade();
      else this.inserirUnidade();
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

