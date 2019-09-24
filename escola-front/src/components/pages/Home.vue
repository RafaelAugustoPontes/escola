<template>
  <div>
    <loading :active.sync="isLoading" :can-cancel="false" :is-full-page="true"></loading>
    <div v-if="perfil === 'ADMINISTRADOR'">
      <h1 class="display-4">Dashboard</h1>
      <b-card-group deck>
        <b-card header="Gráfico 1" header-tag="header" title="Pessoas por turma">
          <apexchart
            width="380"
            type="donut"
            :options="graficoTurma.chartOptions"
            :series="graficoTurma.series"
          ></apexchart>
        </b-card>
        <b-card header="Gráfico 2" header-tag="header" title="Turmas por unidade">
          <apexchart
            width="380"
            type="bar"
            :options="graficoUnidade.chartOptions"
            :series="graficoUnidade.series"
          ></apexchart>
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
      graficoTurma: {},
      graficoUnidade: {},
      isLoading: true,
      perfil: {},
    };
  },

  created() {
    setTimeout(() => {
      this.perfil = sessionStorage.getItem('perfil');
      console.log(this.perfil);
    }, 1000);
    this.buscarPessoasPorTurma();
    this.buscarTurmasPorUnidade();
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
    buscarTurmasPorUnidade() {
      this.isLoading = true;
      this.$http
        .get(process.env.VUE_APP_BASE_URI + 'grafico/turma-unidade')
        .then(resposta => resposta.json())
        .then(
          graficoUnidade => {
            this.graficoUnidade = graficoUnidade;
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

