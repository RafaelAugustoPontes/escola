<template>
  <div>
    <b-modal
      id="modal-cadastro-aula"
      ref="modal"
      title="Nova aula"
      cancel-title="Cancelar"
      ok-title="Gravar"
      :ok-disabled="false"
      @hidden="resetModal"
      @ok="handleSubmit"
    >
      <form ref="form" @submit.stop.prevent="handleSubmit">
        <b-form-group label="Turma" label-for="turma">
          <span id="turma">{{turma}}</span>
        </b-form-group>
        <b-form-group label="Diário da aula" label-for="diario">
          <b-form-textarea id="diario" v-model="aula.diario" rows="3" maxlength="2000"></b-form-textarea>
        </b-form-group>
      </form>
    </b-modal>
  </div>
</template>

<script>
export default {
  props: ['idTurma', 'aula'],

  data() {
    return {
      turma: {},
    };
  },

  methods: {
    resetModal() {
      this.$emit('modalFechada');
    },

    atualizar() {
      this.$http
        .put(process.env.VUE_APP_BASE_URI + 'aula', this.aula)
        .then(
          () =>
            this.$bvToast.toast('Aula atualizada com sucesso', this.$toastInfo),
          erro => this.$bvToast.toast(erro.body.message, this.$toastInfo)
        );
      this.fechar();
    },

    inserir() {
      this.$http
        .post(process.env.VUE_APP_BASE_URI + 'aula', this.aula)
        .then(
          () =>
            this.$bvToast.toast('Aula inserida com sucesso', this.$toastInfo),
          erro => this.$bvToast.toast(erro.body.message, this.$toastInfo)
        );
      this.fechar();
    },

    handleSubmit() {
      if (this.aula.idAula) this.atualizar();
      else this.inserir();
    },

    abrir() {
      buscarTurma();
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

