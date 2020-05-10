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
      @row-dblclicked="selecionar"
      small
    >
       <template slot="first" slot-scope="row">
          <b-button size="sm" variant="info" @click="editar(row.item)" class="mr-1">
            <span v-if="editButtonName">{{editButtonName}}</span>
            <span v-else>Editar</span>
          </b-button>
      </template>
      <template slot="second" slot-scope="row">
           <b-button size="sm" variant="success" @click="arquivar(row.item)" v-if="row.item.arquivado">
              <span v-if="sucessValueButtonName">{{sucessValueButtonName}}</span>
              <span v-else>Desarquivar</span>
            </b-button>
            <b-button size="sm" variant="danger" @click="arquivar(row.item)" v-else>
              <span v-if="dangerValueButtonName">{{dangerValueButtonName}}</span>
              <span v-else>Arquivar</span>
            </b-button>
      </template>
    </b-table>
  </div>
</template>

<script>
export default {
  props: ['itens', 'campos', 'sucessValueButtonName', 'dangerValueButtonName', 'editButtonName'],

  data() {
    return {
      itensPorPagina: 20,
      paginaAtual: 1,
      quantidade: this.itens.length,
    };
  },

  methods: {
    editar(item) {
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
