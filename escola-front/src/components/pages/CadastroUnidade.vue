<template>
  <div>
    <app-loading :isLoading="isLoading"></app-loading>
    <h1>Cadastro de unidades</h1>
    <b-form-group>
      <b-form-input type="search" placeholder="Digite um nome para pesquisar" v-model="filtro"></b-form-input>
    </b-form-group>
    <b-button class="btn btn-success float-right" v-b-modal.modal-cadastro-unidade>Nova</b-button>
    <tabela-generica 
        :itens="dadosComFiltro()" 
        :campos="campos" 
        @editar="editar" 
        @arquivar="arquivar"
        dangerValueButtonName="Arquivar"
        >
    </tabela-generica>
    <modal-cadastro-unidade ref="modal" :unidade="unidade" @modalFechada="fecharModal()"></modal-cadastro-unidade>
  </div>
</template>

<script>
import TabelaGenerica from '../tables/TabelaGenerica';
import ModalCadastroUnidade from '../modals/ModalCadastroUnidade.vue';
import AppLoading from '../partials/app-loading/app-loading.vue';

export default {
  components: {
    'modal-cadastro-unidade': ModalCadastroUnidade,
    'app-loading': AppLoading,
    TabelaGenerica,
  },

  data() {
    return {
      unidade: {},
      unidades: [],
      filtro: '',
      isLoading: false,
      campos: [ 
          {key: 'nome', label : 'Nome', sortable : true}, 
          {key: 'first', label: 'Editar', sortable : false, class : "colunaMenor"},
          {key: 'second', label: 'Arquivar', sortable : false, class : "colunaMenor"},
        ],
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
            this.$toast.error('Erro ao buscar as unidades' + erro.body.message);
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

    arquivar(unidade){
      this.isLoading = true;
      this.$http
        .put(process.env.VUE_APP_BASE_URI + `unidade/arquivar/${unidade.idUnidade}`)
        .then(resposta => resposta.json())
        .then(unidade => {
          this.isLoading = false;
          this.buscarUnidades();
        }, erro => {
           this.$toast.error('Erro ao arquivar a unidade' + erro.body.message);
           this.isLoading = false;
        })
    },
  },
};
</script>


<style scoped>
</style>



