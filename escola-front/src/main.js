import Vue from 'vue';
import App from './App.vue';
import BootstrapVue from 'bootstrap-vue';
import VueResource from 'vue-resource';
import VueRouter from 'vue-router';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue/dist/bootstrap-vue.css';
import { routes } from './routes';
import VueApexCharts from 'vue-apexcharts';
import VueToast from 'vue-toast-notification';
import 'vue-toast-notification/dist/index.css';

Vue.component('apexchart', VueApexCharts);
Vue.use(BootstrapVue);
Vue.use(VueResource);
Vue.use(VueRouter);
Vue.use(VueToast, {
  position: 'top-right',
  duration: 5000,
  dismissible: true,
});

let router = new VueRouter({ routes });
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

Vue.http.interceptors.push((request, next) => {
  if (sessionStorage.getItem('jwt'))
    Vue.http.headers.common['Authorization'] = sessionStorage.getItem('jwt');
  else Vue.http.headers.common['Authorization'] = '';
  next(response => {
    if (response && (response.status === 403 || response.status === 401)) {
      sessionStorage.clear();
      router.push('/login');
    }
  });
});

new Vue({
  render: h => h(App),
  router: router,
}).$mount('#app');
