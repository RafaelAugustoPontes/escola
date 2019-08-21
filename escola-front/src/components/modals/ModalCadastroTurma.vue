<template>
  <div>
    <b-modal
      size="xl"
      id="modal-cadastro-turma"
      ref="modal"
      title="Nova turma"
      cancel-title="Cancelar"
      ok-title="Gravar"
      :ok-disabled="!turma || !turma.nome"
      @hidden="resetModal"
      @ok="handleSubmit"
    >
      <form ref="form" @submit.stop.prevent="handleSubmit">
        <b-form-group label="Nome" label-for="nome">
          <b-form-input id="nome" v-model="turma.nome" required maxlength="45"></b-form-input>
        </b-form-group>
      </form>
    </b-modal>
  </div>
</template>

<script>
export default {
  props: ['turma'],

  methods: {
    resetModal() {
      this.$emit('modalFechada');
    },

    atualizar() {
      this.$http
        .put(process.env.VUE_APP_BASE_URI + 'turma', this.turma)
        .then(
          () =>
            this.$bvToast.toast(
              'Turma atualizada com sucesso',
              this.$toastInfo
            ),
          erro => this.$bvToast.toast(erro.body.message, this.$toastInfo)
        );
      this.fechar();
    },

    inserir() {
      this.$http
        .post(process.env.VUE_APP_BASE_URI + 'turma', this.turma)
        .then(
          () =>
            this.$bvToast.toast('Turma inserida com sucesso', this.$toastInfo),
          erro => this.$bvToast.toast(erro.body.message, this.$toastInfo)
        );
      this.fechar();
    },

    handleSubmit() {
      if (this.turma.idturma) this.atualizar();
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

