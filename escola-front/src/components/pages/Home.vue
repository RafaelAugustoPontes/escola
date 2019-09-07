<template>
  <div>
    <h1 class="display-4">Dashboard</h1>
    <b-card-group deck>
      <b-card header="Gráfico 1" header-tag="header" title="Title">
        <apexchart width="500" type="bar" :options="options" :series="series"></apexchart>
      </b-card>
      <b-card header="Gráfico 2" header-tag="header" title="Title">
        <apexchart width="380" type="donut" :options="options2" :series="series2"></apexchart>
      </b-card>
    </b-card-group>
    <b-card-group deck>
      <b-card header="Gráfico 1" header-tag="header" title="Title">
        <apexchart width="500" type="bar" :options="options" :series="series"></apexchart>
      </b-card>
      <b-card header="Gráfico 2" header-tag="header" title="Title">
        <apexchart width="500" type="bar" :options="options" :series="series"></apexchart>
      </b-card>
    </b-card-group>
  </div>
</template>

<script>
export default {
  data() {
    return {
      options2: {},
      series2: [44, 55, 41, 17, 15],
      options: {
        chart: {
          id: 'vuechart-example',
        },
        xaxis: {
          categories: [1991, 1992, 1993, 1994, 1995, 1996, 1997, 1998],
        },
      },
      series: [
        {
          name: 'series-1',
          data: [30, 40, 45, 50, 49, 60, 70, 91],
        },
      ],
    };
  },
};
</script>

<style scoped>
</style>

