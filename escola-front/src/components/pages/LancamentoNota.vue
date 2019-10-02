<template>
  <div>
    <h1>Lançamento de notas</h1>
    <b-form-group label="Turma">
      <b-form-input type="search" placeholder="Digite um nome para pesquisar" v-model="filtro"></b-form-input>
    </b-form-group>
    <tabela-generica :itens="dadosComFiltro()" :campos="campos" @editar="editar"></tabela-generica>
  </div>
</template>

<script>
import TabelaGenerica from '../tables/TabelaGenerica.vue';

export default {
  created() {
    this.buscar();
  },

  data() {
    return {
      turmas: [],
      filtro: '',
      campos: ['nome'],
    };
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

    editar(turma) {
      this.turma = turma;
      // this.$refs.modal.abrir();
    },

    buscar() {
      this.$http
        .get(process.env.VUE_APP_BASE_URI + 'turma')
        .then(resposta => resposta.json())
        .then(
          turmas => (this.turmas = turmas),
          erro =>
            this.$toast.error('Erro ao buscar as turmas' + erro.body.message)
        );
    },
  },
  components: {
    'tabela-generica': TabelaGenerica,
  },
};
</script>

<style scoped>
</style>