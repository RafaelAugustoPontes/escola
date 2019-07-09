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
      :items="pessoas"
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
  props: ['pessoas'],

  data() {
    return {
      campos: ['nome', 'cpf', 'matricula', 'perfilDescricao'],
      itensPorPagina: 10,
      paginaAtual: 1,
      quantidade: this.pessoas.length,
    };
  },

  methods: {
    editar(pessoas) {
      this.$emit('editar', pessoas);
    },
  },
};
</script>

<style scoped>
</style>
