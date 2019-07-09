<template>
  <div>
    <b-card no-body>
      <b-tabs card>
        <b-card-text>Cadastro de pessoas</b-card-text>
        <b-button v-b-modal.modal-cadastro-pessoa>Nova</b-button>
        <tabela-pessoas :pessoas="pessoas" @editar="editar"></tabela-pessoas>
        <modal-cadastro-pessoa ref="modal" :pessoa="pessoa" @modalFechada="fecharModal()"></modal-cadastro-pessoa>
      </b-tabs>
    </b-card>
  </div>
</template>

<script>
import ModalCadastroPessoa from '../modals/ModalCadastroPessoa.vue';
import TabelaPessoas from '../tables/TabelaPessoas.vue';

export default {
  components: {
    'modal-cadastro-pessoa': ModalCadastroPessoa,
    'tabela-pessoas': TabelaPessoas,
  },

  data() {
    return {
      pessoa: {},
      pessoas: [],
    };
  },

  created() {
    this.buscarPessoas();
  },

  methods: {
    buscarPessoas() {
      this.$http
        .get(process.env.VUE_APP_BASE_URI + 'pessoa')
        .then(resposta => resposta.json())
        .then(
          pessoas => (this.pessoas = pessoas),
          erro =>
            this.$bvToast.toast(
              'Erro ao buscar as pessoas' + erro.body.message,
              this.$toastErro
            )
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
  },
};
</script>

<style scoped>
</style>


