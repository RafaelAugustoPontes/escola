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
      :items="unidades"
      :fields="campos"
      :per-page="itensPorPagina"
      :current-page="paginaAtual"
      :striped="true"
      :bordered="true"
      @row-dblclicked="editar"
      small
    ></b-table>
  </div>
</template>

<script>
export default {
  props: ['unidades'],
  data() {
    return {
      campos: ['nome'],
      itensPorPagina: 10,
      paginaAtual: 1,
      quantidade: this.unidades.length,
    };
  },

  methods: {
    editar(unidade) {
      this.$emit('editar', unidade);
    },
  },
};
</script>

<style scoped>
</style>
