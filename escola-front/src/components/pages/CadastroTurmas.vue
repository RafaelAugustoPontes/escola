<template>
  <div>
    <b-card no-body>
      <b-tabs card>
        <b-card-text>Cadastro de turmas</b-card-text>
        <b-button v-b-modal.modal-cadastro-turma>Nova</b-button>
        <tabela-generica :itens="turmas" :campos="campos" @editar="editar"></tabela-generica>
        <modal-cadastro-turma ref="modal" :turma="turma" @modalFechada="fecharModal()"></modal-cadastro-turma>
      </b-tabs>
    </b-card>
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
      turma: {},
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

    editar() {},
  },
};
</script>


<style scoped>
</style>



