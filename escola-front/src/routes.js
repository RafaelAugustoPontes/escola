import Home from './components/pages/Home.vue';
import CadastroPessoas from './components/pages/CadastroPessoas.vue';
import CadastroUnidade from './components/pages/CadastroUnidade.vue';
import CadastroCurso from './components/pages/CadastroCurso.vue';
import CadastroEstagio from './components/pages/CadastroEstagio.vue';

export const routes = [
  {
    path: '/',
    component: Home,
    titulo: 'Home',
  },
  {
    path: '/pessoas',
    component: CadastroPessoas,
    titulo: 'Pessoa',
  },
  {
    path: '/unidades',
    component: CadastroUnidade,
    titulo: 'Unidade',
  },
  {
    path: '/cursos',
    component: CadastroCurso,
    titulo: 'Curso',
  },
  {
    path: '/estagios',
    component: CadastroEstagio,
    titulo: 'Estágio',
  },
];
