<template>
  <div id="app">
    <header id="header">
      <Menu :routes="routes" v-if="!hide" @alterarSenha="alterarSenha()" />
    </header>
    <main id="content">
      <router-view></router-view>
      <modal-alteracao-senha ref="modal"></modal-alteracao-senha>
    </main>
  </div>
</template>

<script>
import Menu from './components/partials/menu/Menu.vue';
import { routes } from './routes.js';
import ModalAlteracaoSenha from './components/modals/modal-alteracao-senha/modal-alteracao-senha.vue';

export default {
  name: 'app',
  components: {
    Menu,
    ModalAlteracaoSenha,
  },

  methods: {
    alterarSenha() {
      this.$refs.modal.abrir();
    },
  },

  computed: {
    hide() {
      return this.$route.path === '/login' || this.$route.path === '/';
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

.colunaMenor{
  max-width: 30px;
}

</style>
