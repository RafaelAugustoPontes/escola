<template>
  <div>
    <app-loading :isLoading="isLoading"></app-loading>
    <h1>Cadastro de estágios</h1>
    <b-form-group>
      <b-form-input type="search" placeholder="Digite um nome para pesquisar" v-model="filtro"></b-form-input>
    </b-form-group>
    <b-button class="btn btn-success float-right" v-b-modal.modal-cadastro-estagio>Novo</b-button>
    <tabela-estagios :estagios="dadosComFiltro()" @editar="editar"></tabela-estagios>
    <modal-cadastro-estagio ref="modal" :estagio="estagio" @modalFechada="fecharModal()"></modal-cadastro-estagio>
  </div>
</template>

<script>
import ModalCadastroEstagio from '../modals/ModalCadastroEstagio.vue';
import TabelaEstagios from '../tables/TabelaEstagios.vue';
import AppLoading from '../partials/app-loading/app-loading.vue';

export default {
  components: {
    'modal-cadastro-estagio': ModalCadastroEstagio,
    'tabela-estagios': TabelaEstagios,
    'app-loading': AppLoading,
  },

  data() {
    return {
      estagio: {},
      estagios: [],
      filtro: '',
      isLoading: false,
    };
  },

  created() {
    this.buscarEstagios();
  },

  methods: {
    dadosComFiltro() {
      if (this.filtro) {
        let expressao = new RegExp(this.filtro.trim(), 'i');
        return this.estagios.filter(estagio => expressao.test(estagio.nome));
      } else {
        return this.estagios;
      }
    },
    buscarEstagios() {
      this.isLoading = true;
      this.$http
        .get(process.env.VUE_APP_BASE_URI + 'estagio')
        .then(resposta => resposta.json())
        .then(
          estagios => {
            this.isLoading = false;
            this.estagios = estagios;
          },
          erro => {
            this.$bvToast.toast(
              'Erro ao buscar os estágios' + erro.body.message,
              this.$toastErro
            );
            this.isLoading = false;
          }
        );
    },

    editar(estagio) {
      this.estagio = estagio;
      this.$refs.modal.abrir();
    },

    fecharModal() {
      this.buscarEstagios();
      this.estagio = {};
      this.$refs.modal.fechar();
    },
  },
};
</script>


<style scoped>
</style>



