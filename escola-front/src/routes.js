import Vue from 'vue';
import Router from 'vue-router';
import { isSignedIn } from './auth/auth';

export const routes = [
  {
    path: '/',
    component: () => import('./components/pages/Home.vue').then(m => m.default),
    titulo: 'Home',
    beforeEnter(_, __, next) {
      // Impede usuários não assinados
      if (isSignedIn()) {
        // de acessar a página Home.
        next();
        return;
      }
      next('/login');
    },
  },
  {
    path: '/pessoas',
    component: () =>
      import('./components/pages/CadastroPessoas.vue').then(m => m.default),
    titulo: 'Pessoa',
    beforeEnter(_, __, next) {
      if (isSignedIn()) {
        next();
        return;
      }
      next('/login');
    },
  },
  {
    path: '/unidades',
    component: () =>
      import('./components/pages/CadastroUnidade.vue').then(m => m.default),
    titulo: 'Unidade',
  },
  {
    path: '/cursos',
    component: () =>
      import('./components/pages/CadastroCurso.vue').then(m => m.default),
    titulo: 'Curso',
  },
  {
    path: '/estagios',
    component: () =>
      import('./components/pages/CadastroEstagio.vue').then(m => m.default),
    titulo: 'Estágio',
  },
  {
    path: '/login',
    component: () =>
      import('./components/pages/Login.vue').then(m => m.default),
    titulo: 'Login',
    beforeEnter(_, __, next) {
      // Impede usuários assinados de
      if (!isSignedIn()) {
        // acessar a página de login.
        next();
        return;
      }
      next('/home');
    },
  },
];
