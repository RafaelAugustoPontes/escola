import { isSignedIn } from './auth/auth';

export const routes = [
  {
    path: '/home',
    component: () => import('./components/pages/Home.vue').then(m => m.default),
    titulo: 'Home',
    oculto: false,
  },
  {
    path: '/pessoas',
    component: () =>
      import('./components/pages/CadastroPessoas.vue').then(m => m.default),
    titulo: 'Pessoas',
    oculto: false,
    classe: 'cadastro',
  },
  {
    path: '/unidades',
    component: () =>
      import('./components/pages/CadastroUnidade.vue').then(m => m.default),
    titulo: 'Unidade',
    oculto: false,
    classe: 'cadastro',
  },
  {
    path: '/cursos',
    component: () =>
      import('./components/pages/CadastroCurso.vue').then(m => m.default),
    titulo: 'Curso',
    oculto: false,
    classe: 'cadastro',
  },
  {
    path: '/estagios',
    component: () =>
      import('./components/pages/CadastroEstagio.vue').then(m => m.default),
    titulo: 'Estágio',
    oculto: false,
    classe: 'cadastro',
  },
  {
    path: '/turmas',
    component: () =>
      import('./components/pages/CadastroTurmas.vue').then(m => m.default),
    titulo: 'Turma',
    oculto: false,
    classe: 'cadastro',
  },
  {
    path: '/login',
    component: () =>
      import('./components/pages/Login.vue').then(m => m.default),
    titulo: 'Login',
    oculto: true,
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
