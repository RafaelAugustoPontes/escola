<template>
  <div>
    <app-loading :isLoading="isLoading"></app-loading>
    <h1>Cadastro de pessoas</h1>
    <b-form-group>
      <b-form-input type="search" placeholder="Digite um nome para pesquisar" v-model="filtro"></b-form-input>
    </b-form-group>
    <b-button class="btn btn-success float-right" v-b-modal.modal-cadastro-pessoa>Nova</b-button>
    <tabela-generica :itens="dadosComFiltro()" :campos="campos" @editar="editar" @arquivar="arquivar"></tabela-generica>
    <modal-cadastro-pessoa ref="modal" :pessoa="pessoa" @modalFechada="fecharModal()"></modal-cadastro-pessoa>
  </div>

</template>

<script>
import ModalCadastroPessoa from '../modals/ModalCadastroPessoa.vue';
import AppLoading from '../partials/app-loading/app-loading.vue';
import TabelaGenerica from '../tables/TabelaGenerica';

export default {
  components: {
    'modal-cadastro-pessoa': ModalCadastroPessoa,
    'app-loading': AppLoading,
    TabelaGenerica,
  },

  computed: {},

  data() {
    return {
      teste : [{'nome' : 'Rafael'}],
       fields: [
          { key: 'nome', label: 'Person Full name', sortable: true, sortDirection: 'desc' },
          { key: 'actions', label: 'Actions' }
       ],
      pessoa: {},
      pessoas: [],
      filtro: '',
      isLoading: false,
      campos: [ 
          { key: 'nome', label : 'Nome', sortable : true}, 
          {key: 'matricula', label: 'Matrícula', sortable : true}, 
          {key: 'cpf', label: 'CPF', sortable : false},
          { key: 'actions', label: 'Ações', sortable : false}
        ],
    };
  },

  created() {
    this.buscarPessoas();
  },

  methods: {
    dadosComFiltro() {
      if (this.filtro) {
        let expressao = new RegExp(this.filtro.trim(), 'i');
        return this.pessoas.filter(pessoa => expressao.test(pessoa.nome));
      } else {
        return this.pessoas;
      }
    },
    buscarPessoas() {
      this.isLoading = true;
      this.$http
        .get(process.env.VUE_APP_BASE_URI + 'pessoa')
        .then(resposta => resposta.json())
        .then(
          pessoas => {
            this.pessoas = pessoas;
            this.isLoading = false;
          },
          erro => {
            this.$toast.error('Erro ao buscar as pessoas' + erro.body.message);
            this.isLoading = false;
          }
        );
    },

    fecharModal() {
      this.pessoa = {};
      this.buscarPessoas();
      this.$refs.modal.fechar();
    },

    editar(pessoa) {
      this.pessoa = pessoa;
      this.$refs.modal.abrir();
    },

    arquivar(pessoa){
      this.isLoading = true;
      this.$http
        .put(process.env.VUE_APP_BASE_URI + `pessoa/arquivo/${pessoa.idPessoa}`)
        .then(resposta => resposta.json())
        .then(
          () => {
            this.$toast.success('Operação realizada com sucesso');
            this.buscarPessoas()
          },
          erro => {
            this.$toast.error('Erro ao buscar ao arquivar a pessoa' + erro.body.message);
            this.isLoading = false;
          }
        );
    },

  },
};
</script>

<style scoped>
</style>


