<template>
  <div>
    <b-form @submit="onSubmit" @reset="onReset">
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
          <b-button type="submit" variant="primary">Submit</b-button>
          <b-button type="reset" variant="danger">Reset</b-button>
        </b-button-group>
      </b-card>
    </b-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {
        login: "",
        senha: ""
      }
    };
  },
  methods: {
    onSubmit(evt) {
      evt.preventDefault();
      alert(JSON.stringify(this.form));
    },
    onReset(evt) {
      evt.preventDefault();
      this.form.login = "";
      this.form.senha = "";
    }
  }
};
</script>

<style scoped>
</style>
