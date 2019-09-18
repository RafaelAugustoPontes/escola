<template>
  <div>
    <b-row>
      <b-col cols="12">
        <b-navbar class="header" toggleable="lg" type="dark" variant="dark">
          <b-navbar-brand>
            <router-link to="home">E-Escola</router-link>
          </b-navbar-brand>

          <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

          <b-collapse id="nav-collapse" is-nav>
            <b-navbar-nav>
              <b-dropdown id="dropdown-left" text="Cadastro" variant="primary" class="m-3">
                <div v-for="rota in routes" :value="rota.titulo" :key="rota.titulo">
                  <b-nav-item v-if="!rota.oculto && rota.classe ==='cadastro'">
                    <router-link :to="rota.path ? rota.path  :  '/'">{{rota.titulo}}</router-link>
                  </b-nav-item>
                </div>
              </b-dropdown>
            </b-navbar-nav>
            <b-navbar-nav>
              <b-dropdown id="dropdown-left" text="Lançamentos" variant="primary" class="m-3">
                <div v-for="rota in routes" :value="rota.titulo" :key="rota.titulo">
                  <b-nav-item v-if="!rota.oculto && rota.classe ==='lancamento'">
                    <router-link :to="rota.path ? rota.path  :  '/'">{{rota.titulo}}</router-link>
                  </b-nav-item>
                </div>
              </b-dropdown>
            </b-navbar-nav>
            <b-navbar-nav>
              <b-dropdown id="dropdown-left" text="Consultas" variant="primary" class="m-3">
                <div v-for="rota in routes" :value="rota.titulo" :key="rota.titulo">
                  <b-nav-item v-if="!rota.oculto && rota.classe ==='consulta'">
                    <router-link :to="rota.path ? rota.path  :  '/'">{{rota.titulo}}</router-link>
                  </b-nav-item>
                </div>
              </b-dropdown>
            </b-navbar-nav>

            <b-navbar-nav class="ml-auto">
              <b-nav-item-dropdown right>
                <template slot="button-content">
                  <em>{{nomeUsuario}}</em>
                </template>
                <b-dropdown-item @click="alterarSenha()">Alterar senha</b-dropdown-item>
                <b-dropdown-item @click="logout()">Sair</b-dropdown-item>
              </b-nav-item-dropdown>
            </b-navbar-nav>
          </b-collapse>
        </b-navbar>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import Vue from 'vue';

export default {
  data() {
    return {
      nomeUsuario: '',
    };
  },
  beforeMount() {
    this.$http.get(process.env.VUE_APP_BASE_URI + 'login/usuario').then(data =>
      (this.nomeUsuario = data.json()).then(
        data => {
          console.log(data);
          this.nomeUsuario = data.username;
        },
        err => console.log(err)
      )
    );
  },
  props: ['routes'],
  methods: {
    logout: function() {
      sessionStorage.clear();
      Vue.http.headers.common['Authorization'] = undefined;
      this.$router.push('/login');
      location.reload();
    },

    alterarSenha() {
      this.$emit('alterarSenha');
    },
  },
};
</script>

<style scoped>
.header {
  position: fixed;
  left: 0;
  top: 0;
  width: 100vw;
  z-index: 200;
}
</style>

