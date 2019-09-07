<template>
  <div>
    <h1>Lançamento de aulas</h1>
    <b-form-group label="Turma" label-for="select-turma">
      <b-form-select
        id="select-turma"
        @change="buscarAulas()"
        :options="opcoesTurma"
        v-model="idTurmaSelecionada"
      ></b-form-select>
    </b-form-group>
    <div v-if="idTurmaSelecionada">
      <b-button class="btn btn-success float-right" v-b-modal.modal-cadastro-aula>Nova</b-button>
      <tabela-generica :itens="aulas" :campos="campos" @editar="editar"></tabela-generica>
      <modal-cadastro-aula :turma="turmaSelecionada" :aula="aula"></modal-cadastro-aula>
    </div>
  </div>
</template>

<script>
import TabelaGenerica from '../tables/TabelaGenerica.vue';
import ModalCadastroAula from '../modals/ModalCadastroAula.vue';
export default {
  data() {
    return {
      aulas: [],
      aula: {},
      opcoesTurma: [],
      idTurmaSelecionada: undefined,
      turmaSelecionada: {},
      campos: ['nome'],
    };
  },
  created() {
    this.buscarTurma();
  },

  methods: {
    buscarAulas() {
      this.buscarTurmaPorId();
    },

    buscarTurma() {
      this.$http
        .get(process.env.VUE_APP_BASE_URI + 'turma')
        .then(resposta => resposta.json())
        .then(
          opcoesTurma => (this.opcoesTurma = opcoesTurma),
          erro =>
            this.$bvToast.toast(
              'Erro ao buscar as turmas' + erro.body.message,
              this.$toastErro
            )
        );
    },

    buscarTurmaPorId() {
      this.$http
        .get(process.env.VUE_APP_BASE_URI + 'turma/' + this.idTurmaSelecionada)
        .then(resposta => resposta.json())
        .then(
          turmaSelecionada => (this.turmaSelecionada = turmaSelecionada),
          erro =>
            this.$bvToast.toast(
              'Erro ao buscar as turmas' + erro.body.message,
              this.$toastErro
            )
        );
    },
    editar(aula) {
      this.aula = aula;
      this.$refs.modal.abrir();
    },
  },

  components: {
    'tabela-generica': TabelaGenerica,
    ModalCadastroAula,
  },
};
</script>


<style scoped>
</style>