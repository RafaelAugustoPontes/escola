import Login from "./components/pages/Login.vue";
import Home from "./components/pages/Home.vue";

export const routes = [
  {
    path: "/",
    component: Login,
    titulo: "Login"
  },
  {
    path: "/home",
    component: Home,
    titulo: "Home"
  }
];
