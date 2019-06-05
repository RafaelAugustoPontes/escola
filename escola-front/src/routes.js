import Home from "./components/pages/Home.vue";
import Pessoas from "./components/pages/Pessoas.vue"

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
  }
];
