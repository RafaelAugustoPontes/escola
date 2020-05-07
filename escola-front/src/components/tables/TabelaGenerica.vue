<template>
  <div>
    <b-pagination
      v-model="paginaAtual"
      :total-rows="quantidade"
      :per-page="10"
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
      @row-dblclicked="selecionar"
      small
    >
       <template slot="actions" slot-scope="row">
          <b-button size="sm" variant="info" @click="editar(row.item)" class="mr-1">
            Editar
          </b-button>
          <b-button size="sm" variant="success" @click="arquivar(row.item)" v-if="row.item.arquivado">
            Desarquivar
          </b-button>
          <b-button size="sm" variant="danger" @click="arquivar(row.item)" v-else>
            Arquivar
          </b-button>
      </template>
    </b-table>
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
      console.log(item)
      this.$emit('editar', item);
    },
    selecionar(item, index) {
      this.$emit('selecionar', item, index);
    },
    arquivar(item){
      this.$emit('arquivar', item);
    },
  },
};
</script>

<style scoped>
</style>
