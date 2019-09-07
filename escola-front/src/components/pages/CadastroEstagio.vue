<template>
  <div>
    <h1>Cadastro de estágios</h1>
    <b-form-group label="Pesquisa por nome" label-for="a">
      <b-form-input id="a"></b-form-input>
    </b-form-group>
    <b-button class="btn btn-success float-right" v-b-modal.modal-cadastro-estagio>Novo</b-button>
    <tabela-estagios :estagios="estagios" @editar="editar"></tabela-estagios>
    <modal-cadastro-estagio ref="modal" :estagio="estagio" @modalFechada="fecharModal()"></modal-cadastro-estagio>
  </div>
</template>

<script>
import ModalCadastroEstagio from '../modals/ModalCadastroEstagio.vue';
import TabelaEstagios from '../tables/TabelaEstagios.vue';

export default {
  components: {
    'modal-cadastro-estagio': ModalCadastroEstagio,
    'tabela-estagios': TabelaEstagios,
  },

  data() {
    return {
      estagio: {},
      estagios: [],
    };
  },

  created() {
    this.buscarEstagios();
  },

  methods: {
    buscarEstagios() {
      this.$http
        .get(process.env.VUE_APP_BASE_URI + 'estagio')
        .then(resposta => resposta.json())
        .then(
          estagios => (this.estagios = estagios),
          erro =>
            this.$bvToast.toast(
              'Erro ao buscar os estágios' + erro.body.message,
              this.$toastErro
            )
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



