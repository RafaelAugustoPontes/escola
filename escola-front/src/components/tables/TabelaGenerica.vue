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
      :items="itens"
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
  props: ['itens', 'campos'],

  data() {
    return {
      itensPorPagina: 10,
      paginaAtual: 1,
      quantidade: this.itens.length,
    };
  },

  methods: {
    editar(item) {
      this.$emit('editar', item);
    },
  },
};
</script>

<style scoped>
</style>
