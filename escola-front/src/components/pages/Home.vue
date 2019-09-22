<template>
  <div>
    <loading :active.sync="isLoading" :can-cancel="false" :is-full-page="true"></loading>
    <h1 class="display-4">Dashboard</h1>
    <div v-if="perfil === 'ADMINISTRADOR'">
      <b-card-group deck>
        <b-card header="Gráfico 1" header-tag="header" title="Pessoas por turma">
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
  </div>
</template>

<script>
import Loading from 'vue-loading-overlay';
import 'vue-loading-overlay/dist/vue-loading.css';
export default {
  data() {
    return {
      series: [44, 55, 41, 17, 15],
      chartOptions: {
        labels: ['Apple', 'Mango', 'Orange', 'Watermelon'],
      },
      graficoTurma: {},
      isLoading: true,
      perfil: {},
    };
  },

  created() {
    setTimeout(() => {
      this.buscarPessoasPorTurma();
      this.perfil = sessionStorage.getItem('perfil');
      console.log(this.perfil);
    }, 1000);
  },

  methods: {
    buscarPessoasPorTurma() {
      this.isLoading = true;
      this.$http
        .get(process.env.VUE_APP_BASE_URI + 'grafico/pessoas-turma')
        .then(resposta => resposta.json())
        .then(
          graficoTurma => {
            this.graficoTurma = graficoTurma;
            this.isLoading = false;
          },
          erro => {
            this.isLoading = false;
            this.$bvToast.toast(
              'Erro ao buscar as turmas' + erro.body.message,
              this.$toastErro
            );
          }
        );
    },
  },

  components: {
    Loading,
  },
};
</script>

<style scoped>
</style>

