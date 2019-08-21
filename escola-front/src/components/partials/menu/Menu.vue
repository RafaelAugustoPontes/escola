<template>
  <div>
    <b-row>
      <b-col cols="12">
        <b-navbar class="header" toggleable="lg" type="dark" variant="dark">
          <b-navbar-brand href="#">E-Escola</b-navbar-brand>

          <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

          <b-collapse id="nav-collapse" is-nav>
            <b-navbar-nav>
              <b-dropdown id="dropdown-left" text="Cadastro" variant="primary" class="m-3">
                <b-nav-item v-for="rota in routes" :value="rota.titulo" :key="rota.titulo">
                  <div v-if="!rota.oculto && rota.classe ==='cadastro'">
                    <router-link :to="rota.path ? rota.path  :  '/'">{{rota.titulo}}</router-link>
                  </div>
                </b-nav-item>
              </b-dropdown>
            </b-navbar-nav>

            <b-navbar-nav class="ml-auto">
              <b-nav-item-dropdown right>
                <template slot="button-content">
                  <em>Rafael</em>
                </template>
                <b-dropdown-item href="#">Perfil</b-dropdown-item>
                <b-dropdown-item href="#">Sign Out</b-dropdown-item>
              </b-nav-item-dropdown>
            </b-navbar-nav>
          </b-collapse>
        </b-navbar>
      </b-col>
    </b-row>
    <transition name="painel-fade">
      <slot></slot>
    </transition>
  </div>
</template>

<script>
export default {
  props: ['routes'],
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

.painel-fade-enter,
.painel-fade-leave-active {
  opacity: 0;
}

.painel-fade-enter-active,
.painel-fade-leave-active {
  transition: opacity 0.2s;
}
</style>

