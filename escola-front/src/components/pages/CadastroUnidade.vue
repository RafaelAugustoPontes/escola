<template>
  <div>
    <h1>Cadastro de unidades</h1>
    <b-form-group label="Pesquisa por nome" label-for="a">
      <b-form-input id="a"></b-form-input>
    </b-form-group>
    <b-button class="btn btn-success float-right" v-b-modal.modal-cadastro-unidade>Nova</b-button>
    <tabela-unidades :unidades="unidades" @editar="editar"></tabela-unidades>
    <modal-cadastro-unidade ref="modal" :unidade="unidade" @modalFechada="fecharModal()"></modal-cadastro-unidade>
  </div>
</template>

<script>
import ModalCadastroUnidade from '../modals/ModalCadastroUnidade.vue';
import TabelaUnidades from '../tables/TabelaUnidades.vue';

export default {
  components: {
    'modal-cadastro-unidade': ModalCadastroUnidade,
    'tabela-unidades': TabelaUnidades,
  },

  data() {
    return {
      unidade: {},
      unidades: [],
    };
  },

  created() {
    this.buscarUnidades();
  },

  methods: {
    buscarUnidades() {
      this.$http
        .get(process.env.VUE_APP_BASE_URI + 'unidade')
        .then(resposta => resposta.json())
        .then(
          unidades => (this.unidades = unidades),
          erro =>
            this.$bvToast.toast(
              'Erro ao buscar as unidades' + erro.body.message,
              this.$toastErro
            )
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



