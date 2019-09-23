const _ADMINISTRADOR = 'ADMINISTRADOR';
const _PROFESSOR = 'PROFESSOR';

export const routes = [
  {
    path: '/',
    component: () =>
      import('./components/pages/Login.vue').then(m => m.default),
    titulo: 'vazio',
    oculto: true,
  },
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
    beforeEnter: (to, from, next) => {
      let perfil = sessionStorage.getItem('perfil');
      if (perfil === _ADMINISTRADOR) next(to.path.path);
      else next('/home');
    },
  },
  {
    path: '/unidades',
    component: () =>
      import('./components/pages/CadastroUnidade.vue').then(m => m.default),
    titulo: 'Unidade',
    oculto: false,
    classe: 'cadastro',
    beforeEnter: (to, from, next) => {
      let perfil = sessionStorage.getItem('perfil');
      if (perfil === _ADMINISTRADOR) next(to.path.path);
      else next('/home');
    },
  },
  {
    path: '/cursos',
    component: () =>
      import('./components/pages/CadastroCurso.vue').then(m => m.default),
    titulo: 'Curso',
    oculto: false,
    classe: 'cadastro',
    beforeEnter: (to, from, next) => {
      let perfil = sessionStorage.getItem('perfil');
      if (perfil === _ADMINISTRADOR) next(to.path.path);
      else next('/home');
    },
  },
  {
    path: '/estagios',
    component: () =>
      import('./components/pages/CadastroEstagio.vue').then(m => m.default),
    titulo: 'Estágio',
    oculto: false,
    classe: 'cadastro',
    beforeEnter: (to, from, next) => {
      let perfil = sessionStorage.getItem('perfil');
      if (perfil === _ADMINISTRADOR) next(to.path.path);
      else next('/home');
    },
  },
  {
    path: '/turmas',
    component: () =>
      import('./components/pages/CadastroTurmas.vue').then(m => m.default),
    titulo: 'Turma',
    oculto: false,
    classe: 'cadastro',
    beforeEnter: (to, from, next) => {
      let perfil = sessionStorage.getItem('perfil');
      if (perfil === _ADMINISTRADOR) next(to.path.path);
      else next('/home');
    },
  },
  {
    path: '/aulas',
    component: () =>
      import('./components/pages/LancamentoAula.vue').then(m => m.default),
    titulo: 'Aula',
    oculto: false,
    classe: 'lancamento',
    beforeEnter: (to, from, next) => {
      let perfil = sessionStorage.getItem('perfil');
      if (perfil === _ADMINISTRADOR || perfil === _PROFESSOR)
        next(to.path.path);
      else next('/home');
    },
  },
  {
    path: '/notas',
    component: () =>
      import('./components/pages/lancamento-nota/lancamento-nota.vue').then(
        m => m.default
      ),
    titulo: 'Nota',
    oculto: false,
    classe: 'lancamento',
    beforeEnter: (to, from, next) => {
      let perfil = sessionStorage.getItem('perfil');
      if (perfil === _ADMINISTRADOR || perfil === _PROFESSOR)
        next(to.path.path);
      else next('/home');
    },
  },
  {
    path: '/consulta-aluno',
    component: () =>
      import('./components/pages/consulta-aluno/consulta-aluno.vue').then(
        m => m.default
      ),
    titulo: 'Alunos',
    oculto: false,
    classe: 'consulta',
    beforeEnter: (to, from, next) => {
      let perfil = sessionStorage.getItem('perfil');
      if (perfil === _ADMINISTRADOR || perfil === _PROFESSOR)
        next(to.path.path);
      else next('/home');
    },
  },
  {
    path: '/consulta-aluno-detalhe/:id',
    component: () =>
      import(
        './components/pages/consulta-aluno-detalhe/consulta-aluno-detalhe.vue'
      ).then(m => m.default),
    titulo: '',
    oculto: true,
    classe: '',
    beforeEnter: (to, from, next) => {
      let perfil = sessionStorage.getItem('perfil');
      if (perfil === _ADMINISTRADOR || perfil === _PROFESSOR)
        next(to.path.path);
      else next('/home');
    },
  },
  {
    path: '/consulta-aluno-detalhe',
    component: () =>
      import(
        './components/pages/consulta-aluno-detalhe/consulta-aluno-detalhe.vue'
      ).then(m => m.default),
    titulo: 'Notas',
    oculto: false,
    classe: 'aluno',
    beforeEnter: (to, from, next) => {
      let perfil = sessionStorage.getItem('perfil');
      if (perfil === 'ALUNO') next(to.path.path);
      else next('/home');
    },
  },
  {
    path: '/consulta-turma',
    component: () =>
      import('./components/pages/consulta-turma/consulta-turma.vue').then(
        m => m.default
      ),
    titulo: 'Turmas',
    oculto: false,
    classe: 'consulta',
    beforeEnter: (to, from, next) => {
      let perfil = sessionStorage.getItem('perfil');
      if (perfil === _ADMINISTRADOR || perfil === _PROFESSOR)
        next(to.path.path);
      else next('/home');
    },
  },
  {
    path: '/consulta-turma-detalhe/:id',
    component: () =>
      import(
        './components/pages/consulta-turma-detalhe/consulta-turma-detalhe.vue'
      ).then(m => m.default),
    titulo: '1',
    oculto: true,
    classe: 'consulta',
    beforeEnter: (to, from, next) => {
      let perfil = sessionStorage.getItem('perfil');
      if (perfil === _ADMINISTRADOR || perfil === _PROFESSOR)
        next(to.path.path);
      else next('/home');
    },
  },
  {
    path: '/login',
    component: () =>
      import('./components/pages/Login.vue').then(m => m.default),
    titulo: 'Login',
    oculto: true,
    classe: '',
  },
];
