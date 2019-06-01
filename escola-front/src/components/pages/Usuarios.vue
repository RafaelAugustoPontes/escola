<template>
  <div>
    <b-form>
      <b-row>
        <b-col cols="6">
          <b-form-group
            id="input-group-1"
            label="Nome:"
            label-for="input-1"
            @submit="pesquisarUsuarios"
          >
            <b-form-input
              id="input-1"
              v-model="nome"
              type="text"
              required
              placeholder="Insira um nome.Ex: João"
            ></b-form-input>
          </b-form-group>
          <b-button type="submit" variant="primary">Pesquisar</b-button>
          <b-button v-b-modal.modal-1>Novol</b-button>
        </b-col>
      </b-row>
    </b-form>
    <b-modal id="modal-1" title="BootstrapVue" @ok="teste()">
        <form ref="form" @submit.stop.prevent="handleSubmit">
        <b-form-group
          :state="nameState"
          label="Nome"
          label-for="name-input"
          invalid-feedback="Name is required"
        >
          <b-form-input
            id="name-input"
            v-model="name"
            :state="nameState"
            required
          ></b-form-input>
        </b-form-group>
      </form>
    </b-modal>
  </div>
</template>

<script>
export default {
  data() {
    return {
      nome: ""
    };
  },
  methods :{
      teste(){
          alert("asdasd");
      }
  }
};
</script>

<style scoped>
</style>


