export default {
  props: ['itens', 'campos'],

  mounted() {
    this.campos.unshift({ key: 'selected', label: '', sortable: false });
  },

  data() {
    return {
      itensPorPagina: 10,
      paginaAtual: 1,
      quantidade: this.itens.length,
      isAlgumSelecionado: false,
    };
  },

  methods: {},
};
