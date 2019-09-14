<template>
  <div id="app">
    <header id="header">
      <Menu :routes="routes" v-if="!hide" />
    </header>
    <main id="content">
      <router-view></router-view>
    </main>
  </div>
</template>

<script>
import Menu from './components/partials/menu/Menu.vue';
import { routes } from './routes.js';

export default {
  name: 'app',
  components: {
    Menu,
  },

  computed: {
    hide() {
      return this.$route.path === '/login';
    },
  },
  data() {
    return {
      routes,
    };
  },
};
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

#header {
  padding: 30px;
  text-align: center;
  font-size: 20px;
}

#content {
  -webkit-flex: 3;
  -ms-flex: 3;
  flex: 3;
  padding: 10px;
  width: 78%;
  margin: 0 auto;
}
</style>
