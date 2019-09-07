<template>
  <div>
    <h1>Cadastro de cursos</h1>
    <b-form-group label="Pesquisa por nome" label-for="a">
      <b-form-input id="a"></b-form-input>
    </b-form-group>
    <b-button class="btn btn-success float-right" v-b-modal.modal-cadastro-curso>Novo</b-button>
    <tabela-cursos :cursos="cursos" @editar="editar"></tabela-cursos>
    <modal-cadastro-curso ref="modal" :curso="curso" @modalFechada="fecharModal()"></modal-cadastro-curso>
  </div>
</template>

<script>
import ModalCadastroCurso from '../modals/ModalCadastroCurso.vue';
import TabelaCursos from '../tables/TabelaCursos.vue';
export default {
  components: {
    'modal-cadastro-curso': ModalCadastroCurso,
    'tabela-cursos': TabelaCursos,
  },

  data() {
    return {
      curso: {},
      cursos: [],
    };
  },

  created() {
    this.buscarCursos();
  },

  methods: {
    buscarCursos() {
      this.$http
        .get(process.env.VUE_APP_BASE_URI + 'curso')
        .then(resposta => resposta.json())
        .then(
          cursos => (this.cursos = cursos),
          erro =>
            this.$bvToast.toast(
              'Erro ao buscar os cursos' + erro.body.message,
              this.$toastErro
            )
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



