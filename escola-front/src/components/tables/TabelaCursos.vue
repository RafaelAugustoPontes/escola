<template>
  <div>
    <b-pagination
      v-model="paginaAtual"
      :total-rows="quantidade"
      :per-page="itensPorPagina"
      aria-controls="my-table"
    ></b-pagination>

    <b-table
      id="my-table"
      :items="cursos"
      :fields="campos"
      :per-page="itensPorPagina"
      :current-page="paginaAtual"
      :striped="true"
      :bordered="true"
      @row-clicked="editar"
      small
    ></b-table>
  </div>
</template>

<script>
export default {
  props: ['cursos'],

  data() {
    return {
      campos: ['nome'],
      itensPorPagina: 10,
      paginaAtual: 1,
      quantidade: this.cursos.length,
    };
  },

  methods: {
    editar(curso) {
      this.$emit('editar', curso);
    },
  },
};
</script>

<style scoped>
</style>
