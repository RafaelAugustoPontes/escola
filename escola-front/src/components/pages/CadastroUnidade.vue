<template>
  <div>
    <app-loading :isLoading="isLoading"></app-loading>
    <h1>Cadastro de unidades</h1>
    <b-form-group>
      <b-form-input type="search" placeholder="Digite um nome para pesquisar" v-model="filtro"></b-form-input>
    </b-form-group>
    <b-button class="btn btn-success float-right" v-b-modal.modal-cadastro-unidade>Nova</b-button>
    <tabela-unidades :unidades="dadosComFiltro()" @editar="editar"></tabela-unidades>
    <modal-cadastro-unidade ref="modal" :unidade="unidade" @modalFechada="fecharModal()"></modal-cadastro-unidade>
  </div>
</template>

<script>
import ModalCadastroUnidade from '../modals/ModalCadastroUnidade.vue';
import TabelaUnidades from '../tables/TabelaUnidades.vue';
import AppLoading from '../partials/app-loading/app-loading.vue';

export default {
  components: {
    'modal-cadastro-unidade': ModalCadastroUnidade,
    'tabela-unidades': TabelaUnidades,
    'app-loading': AppLoading,
  },

  data() {
    return {
      unidade: {},
      unidades: [],
      filtro: '',
      isLoading: false,
    };
  },

  created() {
    this.buscarUnidades();
  },

  methods: {
    dadosComFiltro() {
      if (this.filtro) {
        let expressao = new RegExp(this.filtro.trim(), 'i');
        return this.unidades.filter(unidade => expressao.test(unidade.nome));
      } else {
        return this.unidades;
      }
    },
    buscarUnidades() {
      this.isLoading = true;
      this.$http
        .get(process.env.VUE_APP_BASE_URI + 'unidade')
        .then(resposta => resposta.json())
        .then(
          unidades => {
            this.unidades = unidades;
            this.isLoading = false;
          },
          erro => {
            this.$bvToast.toast(
              'Erro ao buscar as unidades' + erro.body.message,
              this.$toastErro
            );
            this.isLoading = false;
          }
        );
    },

    editar(unidade) {
      this.unidade = unidade;
      this.$refs.modal.abrir();
    },

    fecharModal() {
      this.unidade = {};
      this.buscarUnidades();
      this.$refs.modal.fechar();
    },
  },
};
</script>


<style scoped>
</style>



