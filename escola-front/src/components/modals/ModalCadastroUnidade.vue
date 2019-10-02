<template>
  <div>
    <app-loading :isLoading="isLoading"></app-loading>
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
import AppLoading from '../partials/app-loading/app-loading.vue';
export default {
  props: ['unidade'],
  data() {
    return {
      isLoading: false,
    };
  },
  components: {
    'app-loading': AppLoading,
  },

  methods: {
    resetModal() {
      this.$emit('modalFechada');
    },

    atualizarUnidade() {
      this.isLoading = true;
      this.$http
        .put(process.env.VUE_APP_BASE_URI + 'unidade', this.unidade)
        .then(
          () => {
            this.$toast.success('Unidade atualizada com sucesso');
            this.isLoading = false;
            this.fechar();
          },
          erro => {
            this.$toast.error(erro.body.message);
            this.isLoading = false;
          }
        );
      this.fechar();
    },

    inserirUnidade() {
      this.isLoading = true;
      this.$http
        .post(process.env.VUE_APP_BASE_URI + 'unidade', this.unidade)
        .then(
          () => {
            this.$toast.success('Unidade inserida com sucesso');
            this.isLoading = false;
            this.fechar();
          },
          erro => {
            this.$toast.error(erro.body.message);
            this.isLoading = false;
          }
        );
    },

    handleSubmit(evt) {
      evt.preventDefault();
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

