import Vue from 'vue';
import App from './App.vue';
import BootstrapVue from 'bootstrap-vue';
import VueResource from 'vue-resource';
import VueRouter from 'vue-router';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';
import { routes } from './routes';
import VueApexCharts from 'vue-apexcharts';
import VueSession from 'vue-session';

Vue.component('apexchart', VueApexCharts);
Vue.use(BootstrapVue);
Vue.use(VueResource);
Vue.use(VueRouter);
Vue.use(VueSession);
Vue.config.productionTip = false;
Vue.prototype.$toastInfo = {
  title: 'Mensagem',
  autoHideDelay: 5000,
  appendToast: false,
  variant: 'info',
};
Vue.prototype.$toastErro = {
  title: 'Erro',
  autoHideDelay: 5000,
  appendToast: false,
  variant: 'danger',
};

Vue.http.headers.common['Content-Type'] = 'application/json';
Vue.http.headers.common['Access-Control-Allow-Origin'] = '*';
Vue.http.headers.common['Accept'] = 'application/json, text/plain, */*';
Vue.http.headers.common['Access-Control-Allow-Headers'] =
  'Origin, Accept, Content-Type, Authorization, Access-Control-Allow-Origin';

const router = new VueRouter({ routes });

new Vue({
  render: h => h(App),
  router: router,
}).$mount('#app');
