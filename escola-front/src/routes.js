import Home from "./components/pages/Home.vue";
import Usuarios from "./components/pages/Usuarios.vue"

export const routes = [
  {
    path: "/",
    component: Home,
    titulo: "Home"
  },
  {
    path: "/usuarios",
    component: Usuarios,
    titulo: "Usuários"
  }
];
