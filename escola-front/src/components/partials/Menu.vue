<template>
  <div>
    <b-row>
      <b-col cols="12">
      <b-navbar class="header" toggleable="lg" type="dark" variant="dark">
        <b-navbar-brand href="#">Escola online</b-navbar-brand>

        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

        <b-collapse id="nav-collapse" is-nav>
          <b-navbar-nav> 
            <b-nav-item v-for="rota in routes" :value="rota.titulo" :key="rota.titulo">
              <router-link :to="rota.path ? rota.path  :  '/'">{{rota.titulo}}</router-link>
            </b-nav-item>
          </b-navbar-nav>

          <!-- Right aligned nav items -->
          <b-navbar-nav class="ml-auto">
            <b-nav-item-dropdown right>
              <!-- Using 'button-content' slot -->
              <template slot="button-content"><em>Rafael</em></template>
              <b-dropdown-item href="#">Profile</b-dropdown-item>
              <b-dropdown-item href="#">Sign Out</b-dropdown-item>
            </b-nav-item-dropdown>
          </b-navbar-nav>
        </b-collapse>
      </b-navbar>
      </b-col>
    </b-row>
      <slot></slot>
</div>
</template>

<script>
export default {
  props : ['routes']
}
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

