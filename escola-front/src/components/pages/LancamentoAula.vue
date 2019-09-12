<template>
  <div>
    <h1>Lançamento de aulas</h1>
    <b-form-group label="Turma" label-for="select-turma">
      <b-form-select
        id="select-turma"
        @change="onChange($event)"
        :options="opcoesTurma"
        v-model="idTurmaSelecionada"
      ></b-form-select>
    </b-form-group>
    <div v-if="idTurmaSelecionada">
      <b-button class="btn btn-success float-right" v-b-modal.modal-cadastro-aula>Nova</b-button>
      <tabela-generica
        :itens="aulas"
        :campos="campos"
        @editar="editar"
        v-if="aulas && aulas.length > 0"
      ></tabela-generica>
    </div>
    <modal-cadastro-aula ref="modal" :aula="aula" @modalFechada="fecharModal()"></modal-cadastro-aula>
  </div>
</template>

<script>
import TabelaGenerica from '../tables/TabelaGenerica.vue';
import ModalCadastroAula from '../modals/ModalCadastroAula.vue';
export default {
  data() {
    return {
      aulas: [],
      aula: {
        alunos: [],
        turma: {
          unidade: {},
          curso: {},
          estagio: {},
          alunos: [],
        },
      },
      opcoesTurma: [],
      idTurmaSelecionada: null,
      campos: ['dataAula', 'diarioResumido'],
    };
  },
  created() {
    this.buscarTurmas();
  },

  methods: {
    onChange(event) {
      this.idTurmaSelecionada = event;
      this.$http
        .get(process.env.VUE_APP_BASE_URI + 'aula/' + this.idTurmaSelecionada)
        .then(resposta => resposta.json())
        .then(
          aulas => (this.aulas = aulas),
          erro =>
            this.$bvToast.toast(
              'Erro ao buscar as aulas' + erro.body.message,
              this.$toastErro
            )
        );
      this.$http
        .get(process.env.VUE_APP_BASE_URI + 'turma/' + this.idTurmaSelecionada)
        .then(resposta => resposta.json())
        .then(
          turmaSelecionada => (this.aula.turma = turmaSelecionada),
          erro =>
            this.$bvToast.toast(
              'Erro ao buscar as turmas' + erro.body.message,
              this.$toastErro
            )
        );
    },

    buscarTurmas() {
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

    fecharModal() {
      this.onChange(this.idTurmaSelecionada);
      this.aula = {
        alunos: [],
        turma: {
          unidade: {},
          curso: {},
          estagio: {},
          alunos: [],
        },
      };
      console.log(this.aula);
      this.$refs.modal.fechar();
    },

    editar(aula) {
      this.aula = aula;
      console.log(aula);
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