<template>
  <div>
    <h1>Cadastro de turmas</h1>
    <b-form-group label="Pesquisa por nome" label-for="a">
      <b-form-input id="a"></b-form-input>
    </b-form-group>
    <b-button class="btn btn-success float-right" v-b-modal.modal-cadastro-turma>Nova</b-button>
    <tabela-generica :itens="turmas" :campos="campos" @editar="editar"></tabela-generica>
    <modal-cadastro-turma ref="modal" :turma="turma" @modalFechada="fecharModal()"></modal-cadastro-turma>
  </div>
</template>

<script>
import TabelaGenerica from '../tables/TabelaGenerica.vue';
import ModalCadastroTurma from '../modals/ModalCadastroTurma.vue';

export default {
  components: {
    'tabela-generica': TabelaGenerica,
    'modal-cadastro-turma': ModalCadastroTurma,
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
    };
  },

  created() {
    this.buscar();
  },

  methods: {
    buscar() {
      this.$http
        .get(process.env.VUE_APP_BASE_URI + 'turma')
        .then(resposta => resposta.json())
        .then(
          turmas => (this.turmas = turmas),
          erro =>
            this.$bvToast.toast(
              'Erro ao buscar as turmas' + erro.body.message,
              this.$toastErro
            )
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



