<template>
  <div id="app">
    <Menu :routes="routes">
      <router-view></router-view>
    </Menu>
  </div>
</template>

<script>
import Menu from './components/partials/Menu'
import {routes} from './routes.js'

export default {
  name: 'app',
  components: {
    Menu
  },
  data(){
    return{
      routes
    }
  }
}
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
</style>
