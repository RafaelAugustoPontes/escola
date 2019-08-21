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
      :items="estagios"
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
  props: ['estagios'],

  data() {
    return {
      campos: ['nome'],
      itensPorPagina: 10,
      paginaAtual: 1,
      quantidade: this.estagios.length,
    };
  },

  methods: {
    editar(estagios) {
      this.$emit('editar', estagios);
    },
  },
};
</script>

<style scoped>
</style>
