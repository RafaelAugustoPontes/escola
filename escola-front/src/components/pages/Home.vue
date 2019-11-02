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
            :options="graficoUnidade.options"
            :series="graficoUnidade.series"
          ></apexchart>
        </b-card>
      </b-card-group>
      <b-card-group deck>
        <b-card header="Gráfico 3" header-tag="header" title="Top 10 alunos com mais faltas">
          <apexchart
            width="380"
            type="bar"
            :options="graficoFaltas.options"
            :series="graficoFaltas.series"
          ></apexchart>
        </b-card>
        <b-card
          header="Gráfico 4"
          header-tag="header"
          title="Top 10 turmas com a pior média de nota"
        >
          <apexchart
            width="380"
            type="bar"
            :options="graficoMedia.options"
            :series="graficoMedia.series"
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
      graficoFaltas: {},
      graficoMedia: {},
      isLoading: true,
      perfil: {},
    };
  },

  created() {
    this.isLoading = true;
    setTimeout(() => {
      this.perfil = sessionStorage.getItem('perfil');
    }, 1000);
    this.buscarPessoasPorTurma();
    this.buscarTurmasPorUnidade();
    this.buscarAlunosFaltantes();
    this.buscarMediasTurma();
    this.isLoading = false;
  },

  methods: {
    buscarPessoasPorTurma() {
      this.$http
        .get(process.env.VUE_APP_BASE_URI + 'grafico/pessoas-turma')
        .then(resposta => resposta.json())
        .then(
          graficoTurma => {
            this.graficoTurma = graficoTurma;
          },
          erro => {
            this.$toast.error('Erro ao buscar as turmas' + erro.body.message);
          }
        );
    },
    buscarTurmasPorUnidade() {
      this.$http
        .get(process.env.VUE_APP_BASE_URI + 'grafico/turma-unidade')
        .then(resposta => resposta.json())
        .then(
          graficoUnidade => {
            this.graficoUnidade = graficoUnidade;
          },
          erro => {
            this.$toast.error('Erro ao buscar as turmas' + erro.body.message);
          }
        );
    },
    buscarAlunosFaltantes() {
      this.$http
        .get(process.env.VUE_APP_BASE_URI + 'grafico/aluno-falta')
        .then(resposta => resposta.json())
        .then(
          graficoFaltas => {
            this.graficoFaltas = graficoFaltas;
          },
          erro => {
            this.$toast.error('Erro ao buscar o gráfico' + erro.body.message);
          }
        );
    },
    buscarMediasTurma() {
      this.$http
        .get(process.env.VUE_APP_BASE_URI + 'grafico/turma-media')
        .then(resposta => resposta.json())
        .then(
          graficoMedia => {
            this.graficoMedia = graficoMedia;
          },
          erro => {
            this.$toast.error('Erro ao buscar o gráfico' + erro.body.message);
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

