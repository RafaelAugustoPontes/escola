<template>
  <div>
    <h1 class="display-4">Dashboard</h1>
    <b-card-group deck>
      <b-card header="Gráfico 2" header-tag="header" title="Pessoas por turma">
        <apexchart
          width="380"
          type="donut"
          :options="graficoTurma.chartOptions"
          :series="graficoTurma.series"
        ></apexchart>
      </b-card>
      <b-card header="Gráfico 2" header-tag="header" title="Title">
        <apexchart width="380" type="donut" :options="chartOptions" :series="series"></apexchart>
      </b-card>
    </b-card-group>
  </div>
</template>

<script>
export default {
  data() {
    return {
      series: [44, 55, 41, 17, 15],
      chartOptions: {
        labels: ['Apple', 'Mango', 'Orange', 'Watermelon'],
      },
      graficoTurma: {},
    };
  },

  created() {
    this.buscarPessoasPorTurma();
  },

  methods: {
    buscarPessoasPorTurma() {
      this.$http
        .get(process.env.VUE_APP_BASE_URI + 'grafico/pessoas-turma')
        .then(resposta => resposta.json())
        .then(
          graficoTurma => {
            this.graficoTurma = graficoTurma;
            console.log(graficoTurma);
          },
          erro =>
            this.$bvToast.toast(
              'Erro ao buscar as turmas' + erro.body.message,
              this.$toastErro
            )
        );
    },
  },
};
</script>

<style scoped>
</style>

