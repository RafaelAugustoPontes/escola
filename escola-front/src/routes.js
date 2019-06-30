import Home from "./components/pages/Home.vue";
import Pessoas from "./components/pages/Pessoas.vue"
import Unidade from "./components/pages/Unidade.vue"
import Curso from "./components/pages/Curso.vue"
import Estagio from "./components/pages/Estagio.vue"

export const routes = [
  {
    path: "/",
    component: Home,
    titulo: "Home"
  },
  {
    path: "/pessoas",
    component: Pessoas,
    titulo: "Pessoa"
  },
  {
    path: "/unidades",
    component: Unidade,
    titulo: "Unidade"
  },
  {
    path: "/cursos",
    component: Curso,
    titulo: "Curso"
  },
  {
    path: "/estagios",
    component: Estagio,
    titulo: "Estágio"
  },
];
