<template>
  <div>
    <b-modal
      size="lg"
      id="modal-cadastro-pessoa"
      ref="modal"
      title="Nova Pessoa"
      cancel-title="Cancelar"
      ok-title="Gravar"
      @hidden="resetModal"
      @ok="handleSubmit"
    >
      <form ref="form" @submit.stop.prevent="handleSubmit">
        <b-form-group label="Nome" label-for="nome">
          <b-form-input id="nome" v-model="pessoa.nome" required maxlength="45"></b-form-input>
        </b-form-group>
        <b-form-group label="Perfil" label-for="Perfil">
          <b-form-select v-model="pessoa.perfil" :options="opcoesPerfil"></b-form-select>
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
          <b-form-input id="dataNascimento" type="date" v-model="pessoa.dataNascimento" required></b-form-input>
        </b-form-group>
        <b-form-group label="Telefone" label-for="telefone">
          <b-form-input id="telefone" type="tel" v-model="pessoa.telefone"></b-form-input>
        </b-form-group>
        <b-form-group label="E-mail" label-for="email">
          <b-form-input id="email" type="email" v-model="pessoa.email"></b-form-input>
        </b-form-group>
        <b-form-group label="CEP" label-for="cep">
          <b-form-input id="cep" type="text" v-model="pessoa.cep" @focusout="buscarEndereco()"></b-form-input>
        </b-form-group>
        <b-form-group label="Endereco" label-for="endereco">
          <b-form-input
            :disabled="enderecoDesabilitado"
            id="endereco"
            type="text"
            v-model="pessoa.endereco"
          ></b-form-input>
        </b-form-group>
        <b-form-group label="Número" label-for="numero">
          <b-form-input id="numero" type="number" v-model="pessoa.numero"></b-form-input>
        </b-form-group>
        <b-form-group label="Bairro" label-for="bairro">
          <b-form-input
            :readonly="enderecoDesabilitado"
            id="bairro"
            type="text"
            v-model="pessoa.bairro"
          ></b-form-input>
        </b-form-group>
        <b-form-group label="Cidade" label-for="cidade">
          <b-form-input
            :readonly="enderecoDesabilitado"
            id="cidade"
            type="text"
            v-model="pessoa.cidade"
          ></b-form-input>
        </b-form-group>
      </form>
    </b-modal>
  </div>
</template>

<script>
import { mask } from 'vue-the-mask';
export default {
  directives: { mask },
  props: ['pessoa'],

  data() {
    return {
      enderecoDesabilitado: true,
      opcoesPerfil: [
        { text: 'Administrador', value: 'ADMINISTRADOR' },
        { text: 'Professor', value: 'PROFESSOR' },
        { text: 'Aluno', value: 'ALUNO' },
      ],
    };
  },

  methods: {
    resetModal() {
      this.$emit('modalFechada');
    },

    buscarEndereco() {
      this.$http
        .get('https://viacep.com.br/ws/' + this.pessoa.cep + '/json/')
        .then(resposta => resposta.json())
        .then(
          endereco => {
            this.pessoa.endereco = endereco.logradouro;
            this.pessoa.bairro = endereco.bairro;
            this.pessoa.cidade = endereco.localidade;
          },
          erro =>
            this.$bvToast.toast(
              'Erro ao buscar o cep' + erro.body.message,
              this.$toastErro
            )
        );
    },

    atualizar() {
      this.$http.put(process.env.VUE_APP_BASE_URI + 'pessoa', this.pessoa).then(
        () => {
          this.$bvToast.toast('Pessoa atualizada com sucesso', this.$toastInfo);
          this.fechar();
        },
        erro => this.$bvToast.toast(erro.body.message, this.$toastInfo)
      );
    },

    inserir() {
      this.$http
        .post(process.env.VUE_APP_BASE_URI + 'pessoa', this.pessoa)
        .then(
          () => {
            this.$bvToast.toast('Pessoa inserida com sucesso', this.$toastInfo),
              this.fechar();
          },
          erro => this.$bvToast.toast(erro.body.message, this.$toastInfo)
        );
    },

    handleSubmit() {
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

