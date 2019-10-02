<template>
  <div>
    <app-loading :isLoading="isLoading"></app-loading>
    <b-modal
      size="xl"
      id="modal-cadastro-pessoa"
      ref="modal"
      title="Nova Pessoa"
      cancel-title="Cancelar"
      ok-title="Gravar"
      @hidden="resetModal"
      @ok="handleSubmit"
      :ok-disabled="!pessoa || !pessoa.nome || !pessoa.perfilDescricao || !pessoa.cpf || !pessoa.dataNascimento"
    >
      <form ref="form" @submit.stop.prevent="handleSubmit">
        <b-form-group label="Nome" label-for="nome">
          <b-form-input id="nome" v-model="pessoa.nome" required maxlength="45"></b-form-input>
        </b-form-group>
        <b-form-group label="Perfil" label-for="Perfil">
          <b-form-select v-model="pessoa.perfilDescricao" :options="opcoesPerfil"></b-form-select>
        </b-form-group>
        <b-form-group label="CPF" label-for="cpf">
          <b-form-input
            id="cpf"
            v-model="pessoa.cpf"
            required
            maxlength="14"
            masked="false"
            v-mask="'###.###.###-##'"
          ></b-form-input>
        </b-form-group>
        <b-form-group label="Data de nascimento" label-for="dataNascimento">
          <b-form-input
            id="dataNascimento"
            type="date"
            v-model="pessoa.dataNascimento"
            min="1920-01-01"
            max="2015-12-31"
            required
          ></b-form-input>
        </b-form-group>
        <b-form-group label="Telefone" label-for="telefone">
          <b-form-input id="telefone" type="tel" v-model="pessoa.telefone"></b-form-input>
        </b-form-group>
        <b-form-group label="E-mail" label-for="email">
          <b-form-input id="email" type="email" v-model="pessoa.email"></b-form-input>
        </b-form-group>
        <b-form-group label="CEP" label-for="cep">
          <b-form-input id="cep" type="text" v-model="pessoa.cep"></b-form-input>
        </b-form-group>
        <b-form-group label="Endereco" label-for="endereco">
          <b-form-input id="endereco" type="text" v-model="pessoa.endereco"></b-form-input>
        </b-form-group>
        <b-form-group label="Número" label-for="numero">
          <b-form-input id="numero" type="number" v-model="pessoa.numero"></b-form-input>
        </b-form-group>
        <b-form-group label="Bairro" label-for="bairro">
          <b-form-input id="bairro" type="text" v-model="pessoa.bairro"></b-form-input>
        </b-form-group>
        <b-form-group label="Cidade" label-for="cidade">
          <b-form-input id="cidade" type="text" v-model="pessoa.cidade"></b-form-input>
        </b-form-group>
      </form>
    </b-modal>
  </div>
</template>

<script>
import { mask } from 'vue-the-mask';
import AppLoading from '../partials/app-loading/app-loading.vue';
export default {
  directives: { mask },
  props: ['pessoa'],

  components: {
    'app-loading': AppLoading,
  },

  data() {
    return {
      opcoesPerfil: [
        { text: 'ADMINISTRADOR', value: 'ADMINISTRADOR' },
        { text: 'PROFESSOR', value: 'PROFESSOR' },
        { text: 'ALUNO', value: 'ALUNO' },
      ],
      isLoading: false,
    };
  },

  methods: {
    resetModal() {
      this.$emit('modalFechada');
    },

    atualizar(evt) {
      this.isLoading = true;
      this.$http.put(process.env.VUE_APP_BASE_URI + 'pessoa', this.pessoa).then(
        () => {
          this.$toast.success('Pessoa atualizada com sucesso');
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
      this.$http
        .post(process.env.VUE_APP_BASE_URI + 'pessoa', this.pessoa)
        .then(
          () => {
            this.$toast.success('Pessoa inserida com sucesso');
            this.isLoading = false;
            this.fechar();
          },
          erro => {
            this.$toast.error(erro.body.message);
            this.isLoading = false;
          }
        );
    },

    handleSubmit(bvModalEvt) {
      bvModalEvt.preventDefault();
      if (this.pessoa.idPessoa) this.atualizar();
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

