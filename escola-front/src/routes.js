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
    path: '/aulas',
    component: () =>
      import('./components/pages/LancamentoAula.vue').then(m => m.default),
    titulo: 'Aula',
    oculto: false,
    classe: 'lancamento',
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
  },
  {
    path: '/login',
    component: () =>
      import('./components/pages/Login.vue').then(m => m.default),
    titulo: 'Login',
    oculto: true,
    classe: '',
  },
  // {
  //   path: '/login',
  //   component: () =>
  //     import('./components/pages/Login.vue').then(m => m.default),
  //   titulo: 'Login',
  //   oculto: true,
  //   beforeEnter(_, __, next) {
  //     // Impede usuários assinados de
  //     if (!isSignedIn()) {
  //       // acessar a página de login.
  //       next();
  //       return;
  //     }
  //     next('/home');
  //   },
  // },
];
