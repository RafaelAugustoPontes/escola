<template>
  <div>
    <b-form @submit="onSubmit">
      <b-card class="mt-3" header="SISTEMA ESCOLAR">
        <b-container class="bv-example-row">
          <b-row>
            <b-col></b-col>
            <b-col colspan="3">
              <b-form-group id="input-group-1" label="Login:" label-for="input-1">
                <b-form-input
                  id="input-1"
                  v-model="form.login"
                  type="text"
                  required
                  placeholder="Digite o seu login"
                ></b-form-input>
              </b-form-group>
            </b-col>
            <b-col></b-col>
          </b-row>
          <b-row>
            <b-col></b-col>
            <b-col>
              <b-form-group id="input-group-2" label="Senha:" label-for="input-2">
                <b-form-input
                  id="input-2"
                  v-model="form.senha"
                  type="password"
                  required
                  placeholder="Senha"
                ></b-form-input>
              </b-form-group>
            </b-col>
            <b-col></b-col>
          </b-row>
        </b-container>
        <b-button-group>
          <b-button type="submit" variant="primary">Entrar</b-button>
          <router-link to="/home">Home</router-link>
        </b-button-group>
      </b-card>
    </b-form>
    {{ pessoas }}
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {
        login: "",
        senha: ""
      },
      pessoas: []
    };
  },
  methods: {
    onSubmit(evt) {
      evt.preventDefault();
      this.$router.push(this.$route.query.redirect || '/home');
    },
  },

  created() {
    this.$http
      .get("http://localhost:8080/pessoas")
      .then(resposta => resposta.json())
      .then(
        pessoas => (this.pessoas = pessoas),
        erro =>
          this.$bvToast.toast('Servidor não encontrado', {
            title: "Mensagem",
            autoHideDelay: 5000,
            appendToast: false
          })
      );
  }
};
</script>

<style scoped>

.card-header{
  background-color: darkred;
  color : black
}

</style>
