<template>
  <div>
    <h1>Lançamento de notas</h1>
    <b-form-group>
      <b-form-input type="search" placeholder="Digite um nome para pesquisar" v-model="filtro"></b-form-input>
    </b-form-group>
    <tabela-generica :itens="dadosComFiltro()" :campos="campos" @editar="editar"></tabela-generica>
  </div>
</template>

<script>
export default {
  data() {
    return {
      turmas: [],
      filtro: '',
      campos: 'nome',
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
  },
};
</script>

<style scoped>
</style>