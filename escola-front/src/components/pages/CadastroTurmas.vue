<template>
  <div>
    <app-loading :isLoading="isLoading"></app-loading>
    <h1>Cadastro de turmas</h1>
    <b-form-group>
      <b-form-input type="search" placeholder="Digite um nome para pesquisar" v-model="filtro"></b-form-input>
    </b-form-group>
    <b-button class="btn btn-success float-right" v-b-modal.modal-cadastro-turma>Nova</b-button>
    <tabela-generica :itens="dadosComFiltro()" :campos="campos" @editar="editar"></tabela-generica>
    <modal-cadastro-turma ref="modal" :turma="turma" @modalFechada="fecharModal()"></modal-cadastro-turma>
  </div>
</template>

<script>
import TabelaGenerica from '../tables/TabelaGenerica.vue';
import ModalCadastroTurma from '../modals/modal-cadastro-turma/modal-cadastro-turma.vue';
import AppLoading from '../partials/app-loading/app-loading.vue';

export default {
  components: {
    'tabela-generica': TabelaGenerica,
    'modal-cadastro-turma': ModalCadastroTurma,
    'app-loading': AppLoading,
  },

  data() {
    return {
      turmas: [],
      turma: {
        unidade: {},
        curso: {},
        estagio: {},
        professor: {},
        alunos: [],
      },
      campos: ['nome'],
      filtro: '',
      isLoading: false,
    };
  },

  created() {
    this.buscar();
  },

  methods: {
    dadosComFiltro() {
      if (this.filtro) {
        let expressao = new RegExp(this.filtro.trim(), 'i');
        return this.turmas.filter(turma => expressao.test(turma.nome));
      } else {
        return this.turmas;
      }
    },
    buscar() {
      this.isLoading = true;
      this.$http
        .get(process.env.VUE_APP_BASE_URI + 'turma')
        .then(resposta => resposta.json())
        .then(
          turmas => {
            this.turmas = turmas;
            this.isLoading = false;
          },
          erro => {
            this.$toast.error('Erro ao buscar as turmas' + erro.body.message);
            this.isLoading = false;
          }
        );
    },

    fecharModal() {
      this.turma = {
        unidade: {},
        curso: {},
        estagio: {},
        professor: {},
        alunos: [],
      };
      this.buscar();
      this.$refs.modal.fechar();
    },

    selecionar(item) {
      alert(item);
    },

    editar(turma) {
      this.turma = turma;
      this.$refs.modal.abrir();
    },
  },
};
</script>


<style scoped>
</style>



