<template>
  <div>
    <app-loading :isLoading="isLoading"></app-loading>
    <h1>Cadastro de cursos</h1>
    <b-form-group>
      <b-form-input type="search" placeholder="Digite um nome para pesquisar" v-model="filtro"></b-form-input>
    </b-form-group>
    <b-button class="btn btn-success float-right" v-b-modal.modal-cadastro-curso>Novo</b-button>
     <tabela-generica 
        :itens="dadosComFiltro()" 
        :campos="campos" 
        @editar="editar" 
        @arquivar="excluir"
        dangerValueButtonName="Excluir"
        >
    </tabela-generica>
    <modal-cadastro-curso ref="modal" :curso="curso" @modalFechada="fecharModal()"></modal-cadastro-curso>
  </div>
</template>

<script>
import ModalCadastroCurso from '../modals/ModalCadastroCurso.vue';
import AppLoading from '../partials/app-loading/app-loading.vue';
import TabelaGenerica from '../tables/TabelaGenerica';

export default {
  components: {
    'modal-cadastro-curso': ModalCadastroCurso,
    'app-loading': AppLoading,
    TabelaGenerica,
  },

  data() {
    return {
      curso: {},
      cursos: [],
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
            this.$toast.error('Erro ao buscar os cursos' + erro.body.message);
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


     excluir(curso){
      this.isLoading = true;
      this.$http
        .delete(process.env.VUE_APP_BASE_URI + `curso/${curso.idCurso}`)
        .then(() => {
          this.isLoading = false;
          this.buscarCursos();
        }, erro => {
           this.$toast.error('Erro ao excluir o curso. Verifique se está em uso e tente novamente.');
           this.isLoading = false;
        })
    },

  },
};
</script>


<style scoped>
</style>



