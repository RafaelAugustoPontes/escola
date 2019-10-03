<template>
  <div class="container">
    <app-loading :isLoading="isLoading"></app-loading>
    <div class="d-flex justify-content-center h-100">
      <div class="card">
        <div class="card-header">
          <h3>E-Escola</h3>
        </div>
        <div class="card-body">
          <form ref="form" @submit.stop.prevent="onSubmit">
            <div class="input-group form-group">
              <div class="input-group-prepend">
                <span class="input-group-text">
                  <i class="fas fa-user"></i>
                </span>
              </div>
              <b-form-input
                id="cpf"
                v-model="form.username"
                required
                maxlength="14"
                placeholder="Usuário"
              ></b-form-input>
            </div>
            <div class="input-group form-group">
              <div class="input-group-prepend">
                <span class="input-group-text">
                  <i class="fas fa-key"></i>
                </span>
              </div>
              <b-form-input
                id="senha"
                type="password"
                placeholder="Senha"
                v-model="form.password"
                required
                maxlength="45"
              ></b-form-input>
            </div>
            <div class="form-group">
              <b-button type="submit" class="btn float-right login_btn" variant="primary">Entrar</b-button>
            </div>
          </form>
        </div>
        <div class="card-footer">
          <div class="d-flex justify-content-center">
            <a href="#" @click="esqueceuSenha()">Esqueceu a senha?</a>
          </div>
        </div>
      </div>
    </div>
    <modal-reset-senha ref="modal"></modal-reset-senha>
  </div>
</template>

<script>
import Vue from 'vue';
import AppLoading from '../partials/app-loading/app-loading.vue';
import ModalResetSenha from '../modals/modal-reset-senha/modal-reset-senha.vue';

export default {
  components: { AppLoading, ModalResetSenha },
  created() {
    this.isLoading = true;
    this.$toast.info('Por favor, efetue o login');
    this.isLoading = false;
  },
  data() {
    return {
      form: {
        username: '',
        password: '',
      },
      isLoading: false,
    };
  },
  methods: {
    onSubmit(evt) {
      evt.preventDefault();
      this.isLoading = true;
      this.$http.post(process.env.VUE_APP_BASE_URI + 'login', this.form).then(
        response => {
          if (response.status === 200) {
            sessionStorage.setItem('jwt', response.body);
            Vue.http.headers.common['Authorization'] = response.body;
            this.$router.push('/home');
          }
          this.isLoading = false;
        },
        erro => {
          this.$toast.error('Login ou senha inválido' + erro);
          this.isLoading = false;
        }
      );
    },

    esqueceuSenha() {
      this.$refs.modal.abrir();
    },
  },
};
</script>

<style scoped>
html,
body {
  background-size: cover;
  background-repeat: no-repeat;
  height: 100%;
  font-family: 'Numans', sans-serif;
}

.container {
  height: 100%;
  align-content: center;
}

.card {
  height: 370px;
  margin-top: auto;
  margin-bottom: auto;
  width: 400px;
  background-color: #2c3e50 !important;
}

.social_icon span {
  font-size: 60px;
  margin-left: 10px;
  color: #ffc312;
}

.social_icon span:hover {
  color: white;
  cursor: pointer;
}

.card-header h3 {
  color: white;
}

.social_icon {
  position: absolute;
  right: 20px;
  top: -45px;
}

.input-group-prepend span {
  width: 50px;
  background-color: #ffc312;
  color: black;
  border: 0 !important;
}

input:focus {
  outline: 0 0 0 0 !important;
  box-shadow: 0 0 0 0 !important;
}

.remember {
  color: white;
}

.remember input {
  width: 20px;
  height: 20px;
  margin-left: 15px;
  margin-right: 5px;
}

.links {
  color: white;
}

.links a {
  margin-left: 4px;
}
</style>
