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
      <app-loading :isLoading="isLoading"></app-loading>
      <form ref="form" @submit.stop.prevent="handleSubmit">
        <b-form-group label="Nome" label-for="nome">
          <b-form-input id="nome" v-model="curso.nome" required maxlength="45"></b-form-input>
        </b-form-group>
      </form>
    </b-modal>
  </div>
</template>

<script>
import AppLoading from '../partials/app-loading/app-loading.vue';
export default {
  props: ['curso'],

  components: {
    AppLoading,
  },

  data() {
    return {
      isLoading: false,
    };
  },

  methods: {
    resetModal() {
      this.$emit('modalFechada');
    },

    atualizar() {
      this.isLoading = true;
      this.$http.put(process.env.VUE_APP_BASE_URI + 'curso', this.curso).then(
        () => {
          this.$toast.success('Curso atualizado com sucesso');
          this.isLoading = false;
          this.fechar();
        },
        erro => {
          this.$toast.error(erro.body.message);
          this.isLoading = false;
        }
      );
    },

    inserir() {
      this.isLoading = true;
      this.$http.post(process.env.VUE_APP_BASE_URI + 'curso', this.curso).then(
        () => {
          this.$toast.success('Curso inserido com sucesso');
          this.isLoading = false;
          this.fechar();
        },
        erro => {
          this.isLoading = false;
          this.$toast.error(erro.body.message);
        }
      );
    },

    handleSubmit(event) {
      event.preventDefault();

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

