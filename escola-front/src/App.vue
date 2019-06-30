<template>
  <div id="app">
    <Menu :routes="routes"/>
    <main id="content">
      <router-view></router-view>
    </main>
  </div>
</template>

<script>
import Menu from "./components/partials/menu/Menu.vue";
import { routes } from "./routes.js";

export default {
  name: "app",
  components: {
    Menu
  },
  data() {
    return {
      routes
    };
  }
};
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

#content {
    width: 80%;
    margin: 0 auto;
}
</style>
