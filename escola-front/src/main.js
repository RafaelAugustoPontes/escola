import Vue from "vue";
import App from "./App.vue";
import BootstrapVue from "bootstrap-vue";
import VueResource from "vue-resource";
import VueRouter from "vue-router";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
import { routes } from "./routes";

Vue.use(BootstrapVue);
Vue.use(VueResource);
Vue.use(VueRouter);
Vue.config.productionTip = false;

const router = new VueRouter({ routes });

new Vue({
  render: h => h(App),
  router : router
}).$mount("#app");
