<template>
  <div>
    <form>
      <b-button v-b-modal.modal-prevent-closing>Nova</b-button>
    </form>
    <table class="table">
      <thead>
        <tr>
          <th scope="col" v-for="coluna in colunas" :value="coluna" :key="coluna">{{coluna}}</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="pessoa in pessoas" :value="pessoa" :key="pessoa">
          <td>{{pessoa.nome}}</td>
        </tr>
      </tbody>
    </table>
    <b-modal
      id="modal-prevent-closing"
      ref="modal"
      title="Nova Pessoa"
      @show="resetModal"
      @hidden="resetModal"
      @ok="handleOk"
    >
      <form ref="form" @submit.stop.prevent="handleSubmit">
        <b-form-group label="Nome" label-for="nome">
          <b-form-input id="nome" v-model="pessoa.nome" required maxlength="45"></b-form-input>
        </b-form-group>
        <b-form-group label="CPF" label-for="cpf">
          <b-form-input id="cpf" v-model="pessoa.cpf" required maxlength="12"></b-form-input>
        </b-form-group>
        <b-form-group label="Matrícula" label-for="matricula">
          <b-form-input id="matricula" v-model="pessoa.matricula" required maxlength="5"></b-form-input>
        </b-form-group>
        <b-form-group label="Data de nascimento" label-for="dataNascimento">
          <b-form-input id="dataNascimento" type="date" v-model="pessoa.dataNascimento" required></b-form-input>
        </b-form-group>
      </form>
    </b-modal>
  </div>
</template>

<script>
export default {
  data() {
    return {
      colunas: ["Nome"],
      pessoa: {},
      pessoas: []
    };
  },
  created() {
    this.$http
      .get("http://localhost:8080/pessoa")
      .then(resposta => resposta.json())
      .then(
        pessoas => (this.pessoas = pessoas),
        erro => alert("erro ao buscar as pessoas" + erro)
      );
  },
  methods: {
    checkFormValidity() {
      const valid = this.$refs.form.checkValidity();
      this.nameState = valid ? "valid" : "invalid";
      return valid;
    },
    resetModal() {
      this.pessoa = {};
    },
    handleOk(bvModalEvt) {
      bvModalEvt.preventDefault();
      this.handleSubmit();
    },
    handleSubmit() {
      if (!this.checkFormValidity()) {
        return;
      }

      var data = new FormData();
      data.append("nome", this.pessoa.nome );

      let header = { "Content-Type": "application/json" };

      this.$http
        .post("http://localhost:8080/pessoa", data, header)
        .then(alert("Inserido com sucesso"), alert("Erro ao inserir"));

      this.$nextTick(() => {
        this.$refs.modal.hide();
      });
    }
  }
};
</script>

<style scoped>
</style>


