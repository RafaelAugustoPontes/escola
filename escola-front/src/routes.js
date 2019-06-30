import Home from "./components/pages/Home.vue";
import Pessoas from "./components/pages/Pessoas.vue"
import Unidade from "./components/pages/Unidade.vue"
import Curso from "./components/pages/Curso.vue"

export const routes = [
  {
    path: "/",
    component: Home,
    titulo: "Home"
  },
  {
    path: "/pessoas",
    component: Pessoas,
    titulo: "Pessoas"
  },
  {
    path: "/unidades",
    component: Unidade,
    titulo: "Unidades"
  },
  {
    path: "/cursos",
    component: Curso,
    titulo: "Cursos"
  }
];
