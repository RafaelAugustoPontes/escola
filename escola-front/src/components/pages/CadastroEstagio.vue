<template>
  <div>
    <app-loading :isLoading="isLoading"></app-loading>
    <h1>Cadastro de estágios</h1>
    <b-form-group>
      <b-form-input type="search" placeholder="Digite um nome para pesquisar" v-model="filtro"></b-form-input>
    </b-form-group>
    <b-button class="btn btn-success float-right" v-b-modal.modal-cadastro-estagio>Novo</b-button>
      <tabela-generica 
        :itens="dadosComFiltro()" 
        :campos="campos" 
        @editar="editar" 
        @arquivar="excluir"
        dangerValueButtonName="Excluir"
        >
    </tabela-generica>
    <modal-cadastro-estagio ref="modal" :estagio="estagio" @modalFechada="fecharModal()"></modal-cadastro-estagio>
  </div>
</template>

<script>
import ModalCadastroEstagio from '../modals/ModalCadastroEstagio.vue';
import AppLoading from '../partials/app-loading/app-loading.vue';
import TabelaGenerica from '../tables/TabelaGenerica';

export default {
  components: {
    'modal-cadastro-estagio': ModalCadastroEstagio,
    'app-loading': AppLoading,
    TabelaGenerica,
  },

  data() {
    return {
      estagio: {},
      estagios: [],
      filtro: '',
      isLoading: false,
      campos: [ 
          {key: 'nome', label : 'Nome', sortable : true}, 
          {key: 'first', label: '', sortable : false, class : "colunaMenor"},
          {key: 'second', label: '', sortable : false, class : "colunaMenor"},
        ],
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
            this.$toast.error('Erro ao buscar os estágios' + erro.body.message);
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

    excluir(estagio){
      this.isLoading = true;
      this.$http
        .delete(process.env.VUE_APP_BASE_URI + `estagio/${estagio.idEstagio}`)
        .then(() => {
          this.isLoading = false;
          this.buscarEstagios();
        }, erro => {
           this.$toast.error('Erro ao excluir o estágio. Verifique se está em uso e tente novamente.');
           this.isLoading = false;
        })
    },



  },
};
</script>


<style scoped>
</style>



