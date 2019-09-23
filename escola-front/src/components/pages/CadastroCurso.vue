<template>
  <div>
    <app-loading :isLoading="isLoading"></app-loading>
    <h1>Cadastro de cursos</h1>
    <b-form-group>
      <b-form-input type="search" placeholder="Digite um nome para pesquisar" v-model="filtro"></b-form-input>
    </b-form-group>
    <b-button class="btn btn-success float-right" v-b-modal.modal-cadastro-curso>Novo</b-button>
    <tabela-cursos :cursos="dadosComFiltro()" @editar="editar"></tabela-cursos>
    <modal-cadastro-curso ref="modal" :curso="curso" @modalFechada="fecharModal()"></modal-cadastro-curso>
  </div>
</template>

<script>
import ModalCadastroCurso from '../modals/ModalCadastroCurso.vue';
import TabelaCursos from '../tables/TabelaCursos.vue';
import AppLoading from '../partials/app-loading/app-loading.vue';

export default {
  components: {
    'modal-cadastro-curso': ModalCadastroCurso,
    'tabela-cursos': TabelaCursos,
    'app-loading': AppLoading,
  },

  data() {
    return {
      curso: {},
      cursos: [],
      filtro: '',
      isLoading: false,
    };
  },

  created() {
    this.buscarCursos();
  },

  methods: {
    dadosComFiltro() {
      if (this.filtro) {
        let expressao = new RegExp(this.filtro.trim(), 'i');
        return this.cursos.filter(curso => expressao.test(curso.nome));
      } else {
        return this.cursos;
      }
    },

    buscarCursos() {
      this.isLoading = true;
      this.$http
        .get(process.env.VUE_APP_BASE_URI + 'curso')
        .then(resposta => resposta.json())
        .then(
          cursos => {
            this.cursos = cursos;
            this.isLoading = false;
          },
          erro => {
            this.$bvToast.toast(
              'Erro ao buscar os cursos' + erro.body.message,
              this.$toastErro
            );
            this.isLoading = false;
          }
        );
    },

    fecharModal() {
      this.curso = {};
      this.buscarCursos();
      this.$refs.modal.fechar();
    },

    editar(curso) {
      this.curso = curso;
      this.$refs.modal.abrir();
    },
  },
};
</script>


<style scoped>
</style>



